package com.example.projectawsdeploy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {

    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
