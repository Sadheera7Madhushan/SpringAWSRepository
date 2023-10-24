package com.example.projectawsdeploy.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class PasswordConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder as the password encoder
        return new BCryptPasswordEncoder();
    }
}
