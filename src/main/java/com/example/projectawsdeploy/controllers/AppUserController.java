package com.example.projectawsdeploy.controllers;

import com.example.projectawsdeploy.configurations.CustomSecurityConfig;
import com.example.projectawsdeploy.configurations.AuthenticationEnable;
import com.example.projectawsdeploy.dtos.requests.UserLoginRequest;
import com.example.projectawsdeploy.dtos.requests.UserRegistrationRequest;
import com.example.projectawsdeploy.models.AppUser;
import com.example.projectawsdeploy.services.BluePrints.IAppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppUserController {
    private final IAppUser appUser;
    private final AuthenticationEnable authenticationEnable;

    public AppUserController(IAppUser appUser, CustomSecurityConfig customSecurityConfig, AuthenticationEnable authenticationEnable) {
        this.appUser = appUser;
        this.authenticationEnable = authenticationEnable;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String userRegistration() {
        return "user-register";
    }

    @PostMapping("/login")
    public ResponseEntity<?> processLogin(@RequestBody UserLoginRequest userLoginRequest) {
        AppUser userName = appUser.authenticate(userLoginRequest);
        if (userName != null) {
            authenticationEnable.changeAuthenticationStatus(true);
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.badRequest().body("Login failed. Please check your credentials again.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest registrationRequest) {
        if (isRegistrationValid(registrationRequest)) {
            if (appUser.register(registrationRequest)) {
                return ResponseEntity.ok("Registration successful!");
            }
        } else {
            return ResponseEntity.badRequest().body("Registration failed. Please check your information again.");
        }
        return null;
    }

    private boolean isRegistrationValid(UserRegistrationRequest registrationRequest) {
        return registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword());
    }
}
