package com.example.projectawsdeploy.models;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@ToString
@Table(name = "app_user")
public class AppUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

}
