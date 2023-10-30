package com.example.projectawsdeploy.configurations;

import com.example.projectawsdeploy.dto.request.RegisterDto;
import com.example.projectawsdeploy.exceptions.ExpectationFailedException;
import com.example.projectawsdeploy.model.Users;
import com.example.projectawsdeploy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    public ResponseEntity<?> saveUser(@RequestBody RegisterDto registerDto) {

        if (Objects.equals(registerDto.getPassword(), registerDto.getConfirmPassword())) {
            String encrypted_password = passwordEncoder.encode(registerDto.getPassword());
            Users users = new Users();
            users.setEmail(registerDto.getEmail());
            users.setUsername(registerDto.getUsername());
            users.setPassword(encrypted_password);
            userRepository.save(users);
            return ResponseEntity.ok("User registered successfully");
        } else {
            throw new ExpectationFailedException("Passwords does not match");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users registeredUser = findUserByUsername(username);
            return new User(registeredUser.getUsername(), registeredUser.getPassword(), Collections.emptyList());
        } catch (Exception ex) {
            log.error("Request format error", ex);
            throw new UsernameNotFoundException("Request format Error");
        }
    }
}
