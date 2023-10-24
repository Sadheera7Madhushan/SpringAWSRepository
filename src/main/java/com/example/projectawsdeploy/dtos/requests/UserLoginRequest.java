package com.example.projectawsdeploy.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequest {

    private Long id;

    private String username;

    private String password;

}
