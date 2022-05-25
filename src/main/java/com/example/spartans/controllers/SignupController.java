
package com.example.spartans.controllers;

import java.util.Optional;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.SignupRequest;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")
public class SignupController {
    @Autowired
    private UserRepository userRepo;

    private final String message = "{\"message\": \"";

    @PutMapping("/signup")
    private ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        ResponseEntity<String> res = null;

        try {
            String email = signupRequest.getEmail();
            Optional<User> optionalUser = userRepo.findByEmail(email);

            if (optionalUser.isPresent()) {
                res = ResponseEntity.status(422).body(
                        this.message + "email " + email + " already exists\"");
                return res;
            }

            if (signupRequest.getPassword().length() < 5) {
                res = ResponseEntity.status(401).body(
                        this.message + " password needs to be at least 5 characters\"");
                return res;
            }

            if (!signupRequest.getRole().toLowerCase().equals("admin") ||
                    !signupRequest.getRole().toLowerCase().equals("user")) {
                res = ResponseEntity.status(401).body(
                        this.message + " the role needs to be either admin or user");
                return res;
            }

            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setRole(signupRequest.getRole().toLowerCase());
            userRepo.save(user);

            res = ResponseEntity.status(201).body(
                    this.message + " user " + email + " was created\"");
        } catch (Exception e) {
            res = ResponseEntity.status(500).body(
                    this.message + "something went wrong\"");
            e.printStackTrace();
        }
        return res;
    }
}