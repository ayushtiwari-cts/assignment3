package com.app.cryptocurrencyapp.config;

/**
 * This class represents the configuration for the application.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * Creates a new instance of RestTemplate.
     * 
     * @return the RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}