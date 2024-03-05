package com.example.demo.config;

import com.example.demo.filter.TokenGeneratorFilter;
import com.example.demo.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(requests -> requests
            .requestMatchers("/api/v1/login","/api/v1/register").permitAll()
                    .requestMatchers("/api/v1/product/**").hasRole("STAFF")
                    .anyRequest().permitAll())
            .csrf().disable().httpBasic(withDefaults());
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
