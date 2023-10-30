package com.example.projectawsdeploy.util;

import com.example.projectawsdeploy.model.Users;
import com.example.projectawsdeploy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class PreLoader {
    @Bean
    CommandLineRunner initDatabase(PasswordEncoder passwordEncoder,
                                   UserRepository userRepository) {
        return args -> {
            Users users = new Users();
            users.setEmail("user@gmail.com");
            users.setUsername("user");
            users.setPassword(passwordEncoder.encode("pass"));
            userRepository.save(users);
            log.info("admin user saved");
        };
    }
}