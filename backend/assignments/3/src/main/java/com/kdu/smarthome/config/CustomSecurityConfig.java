package com.kdu.smarthome.config;

import com.kdu.smarthome.filter.TokenValidatorFilter;
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

    /**
     * Defines a custom security filter chain.
     *
     * @param http The HttpSecurity instance to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
            .csrf().disable().httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Provides a BCrypt password encoder bean.
     *
     * @return The BCryptPasswordEncoder bean.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
