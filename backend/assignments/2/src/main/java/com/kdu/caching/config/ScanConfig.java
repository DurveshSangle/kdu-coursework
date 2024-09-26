package com.kdu.caching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.caching.services")
public class ScanConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
