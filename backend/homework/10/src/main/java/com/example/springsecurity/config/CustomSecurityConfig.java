package com.example.springsecurity.config;

import com.example.springsecurity.exception.GlobalExceptionHandler;
import com.example.springsecurity.filter.TokenGeneratorFilter;
import com.example.springsecurity.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@ComponentScan(basePackages = "com.example")
public class CustomSecurityConfig {

    @Bean
    GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/person/login").permitAll()
                        .requestMatchers("/userList", "/getUser").hasAnyRole("BASIC","ADMIN")
                        .requestMatchers("/newUser").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(globalExceptionHandler());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
