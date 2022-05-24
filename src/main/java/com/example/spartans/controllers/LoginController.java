package com.example.spartans.controllers;

import java.util.Optional;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> res = null;
        Optional<User> optionalUser =userRepo.findByEmail(loginRequest.getEmail());

        if(!optionalUser.isPresent()) {
            res = ResponseEntity.status(401).body(
                "{\"message\": \"email" + loginRequest.getEmail() + " not found\"}");
        } else {
            try {
                User user = optionalUser.get();

                if(loginRequest.getPassword().equals(user.getPassword())) {
                    res = ResponseEntity.status(200).body(
                        "{\"message\": \"successfull login\"}"
                    );
                } else {
                    res = ResponseEntity.status(401).body(
                        "{\"message\": \"password is wrong\"}"
                    );
                }
            } catch (Exception e) {
                res = ResponseEntity.status(500).body(
                    "{\"message\": \"passwords is wrong\"}"
                );
            }
        }
        return res;
    }
}