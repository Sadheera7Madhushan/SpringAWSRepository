package com.example.projectawsdeploy.controllers;

import com.example.projectawsdeploy.configurations.UserService;
import com.example.projectawsdeploy.dto.request.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("register")
    public RegisterDto registerDto() {
        return new RegisterDto();
    }

    @GetMapping("/register")
    public String register() {
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("register") RegisterDto registerDto, RedirectAttributes redirectAttributes) {
        try {
            ResponseEntity<?> response = userService.saveUser(registerDto);
            redirectAttributes.addFlashAttribute("successMessage", Objects.requireNonNull(response.getBody()).toString());
            return "redirect:/login?successMessage";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register?errorMessage";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "welcome");
        return "home";
    }

}

