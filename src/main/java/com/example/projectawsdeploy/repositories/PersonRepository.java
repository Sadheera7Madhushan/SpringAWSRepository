package com.example.projectawsdeploy.repositories;

import com.example.projectawsdeploy.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
