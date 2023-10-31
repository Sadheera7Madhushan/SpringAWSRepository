package com.example.projectawsdeploy.controllers;

import com.example.projectawsdeploy.configurations.UserService;
import com.example.projectawsdeploy.dto.request.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                String errorMessage = "User is not authenticated or has no UserDetails";
                log.error("User is not authenticated or has no UserDetails");
                return String.valueOf(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage));
            }
        } else {
            String errorMessage = "No authentication found";
            log.error("No authentication found");
            return String.valueOf(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage));
        }

        log.info("current user is: " + username);
        model.addAttribute("successMessage", "You have been logged successfully");
        model.addAttribute("user", username);
        return "home";
    }

    @GetMapping("/section1")
    public String section1(Model model) {
        return "section1";
    }

}

