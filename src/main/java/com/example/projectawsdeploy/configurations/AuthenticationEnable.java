package com.example.projectawsdeploy.configurations;

import org.springframework.stereotype.Service;
@Service
public class AuthenticationEnable {
    private final CustomSecurityConfig customSecurityConfig;
    public AuthenticationEnable(CustomSecurityConfig customSecurityConfig) {
        this.customSecurityConfig = customSecurityConfig;
    }

    public void changeAuthenticationStatus(boolean isAuthenticated) {
        customSecurityConfig.setUserAuthenticated(isAuthenticated);
    }
}
