package com.example.projectawsdeploy.util;

import com.example.projectawsdeploy.models.Person;
import com.example.projectawsdeploy.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PreLoader {
    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository) {
        return args -> {

            Person person = new Person();
            person.setFirstName("Person one");
            person.setLastName("Person Last");
            person.setAge(40);
            personRepository.save(person);
            log.info("Person one saved");

        };
    }
}