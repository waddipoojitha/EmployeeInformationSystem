package com.example.employeeInformationSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> 
                auth.anyRequest().authenticated()
            )
            .httpBasic(customizer -> {}) // Enable HTTP Basic Authentication
            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF
            .build();
    }
}
