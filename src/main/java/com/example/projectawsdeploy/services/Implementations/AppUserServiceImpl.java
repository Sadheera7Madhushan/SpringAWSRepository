package com.example.projectawsdeploy.services.Implementations;

import com.example.projectawsdeploy.dtos.requests.UserLoginRequest;
import com.example.projectawsdeploy.dtos.requests.UserRegistrationRequest;
import com.example.projectawsdeploy.models.AppUser;
import com.example.projectawsdeploy.repositories.AppUserRepository;
import com.example.projectawsdeploy.services.BluePrints.IAppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements IAppUser {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser authenticate(UserLoginRequest userLoginRequest) {
        AppUser byUsername = appUserRepository.findByUsername(userLoginRequest.getUsername());
        if (byUsername != null && passwordEncoder.matches(userLoginRequest.getPassword(), byUsername.getPassword())) {
            return byUsername;
        } else {
            return null;
        }
    }

    @Override
    public boolean register(UserRegistrationRequest registrationRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registrationRequest.getUsername());
        appUser.setEmail(registrationRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        AppUser user = appUserRepository.save(appUser);
        return user.getId() != null;
    }
}
