package com.example.projectawsdeploy.util;

import com.example.projectawsdeploy.models.AppUser;
import com.example.projectawsdeploy.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class PreLoader {
    @Bean
    CommandLineRunner initDatabase(AppUserRepository appUserRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            AppUser appUser = new AppUser();
            appUser.setEmail("admin@gmail.com");
            appUser.setUsername("admin");
            appUser.setPassword(passwordEncoder.encode("admin"));
            appUserRepository.save(appUser);
            log.info("admin user saved");
        };
    }
}