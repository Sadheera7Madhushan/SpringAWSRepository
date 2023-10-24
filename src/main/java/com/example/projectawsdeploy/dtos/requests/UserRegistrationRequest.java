package com.example.projectawsdeploy.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegistrationRequest {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String confirmPassword;

}
