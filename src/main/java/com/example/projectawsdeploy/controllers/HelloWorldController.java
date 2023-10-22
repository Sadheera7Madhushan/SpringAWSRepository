package com.example.projectawsdeploy.controllers;

import com.example.projectawsdeploy.models.Person;
import com.example.projectawsdeploy.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aws")
public class HelloWorldController {
    private final PersonRepository personRepository;

    public HelloWorldController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/run")
    public String run() {
        return "run";
    }

    @GetMapping("/persons")
    public List<Person> personList() {
        return personRepository.findAll();
    }

    @PostMapping("sum/{number1}")
    public String sum(@PathVariable("number1") int number1) {
        // You can do some processing with "number1" here.
        // For example, you can return it directly as a string:
        return String.valueOf(number1);

    }

}