package com.example.projectawsdeploy.services.BluePrints;

import com.example.projectawsdeploy.dtos.requests.UserLoginRequest;
import com.example.projectawsdeploy.dtos.requests.UserRegistrationRequest;
import com.example.projectawsdeploy.models.AppUser;
import com.example.projectawsdeploy.models.UserRegistration;

public interface IAppUser {
    AppUser authenticate(UserLoginRequest userLoginRequest);
    boolean register(UserRegistrationRequest registrationRequest);

}
