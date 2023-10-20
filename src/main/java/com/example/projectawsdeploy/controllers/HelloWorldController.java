package com.example.projectawsdeploy.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aws")
public class HelloWorldController {
    @GetMapping("/run")
    public String run() {
        return "run";
    }

    @GetMapping("/walk")
    public String walk() {
        return "walk....";
    }

    @GetMapping("/see")
    public String see() {
        return "my first pipeline is ok now - version ....";
    }

    @PostMapping("sum/{number1}")
    public String sum(@PathVariable("number1") int number1) {
        // You can do some processing with "number1" here.
        // For example, you can return it directly as a string:
        return String.valueOf(number1);
    }

}