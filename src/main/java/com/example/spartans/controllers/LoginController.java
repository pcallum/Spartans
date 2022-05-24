package com.example.spartans.controllers;

import java.util.Optional;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class LoginController {
    @Autowired
    UserRepository userRepo;

    // this function will be used to get a res so that we can verify if
    // the user is authenticated
    public static ResponseEntity<String> handleLogin(ResponseEntity<String> res,
            LoginRequest loginRequest, UserRepository userRepo) {
        LoginController loginController = new LoginController();
        res = loginController.login(userRepo, loginRequest);
        return res;
    }

    public ResponseEntity<String> login(UserRepository userRepo, LoginRequest loginRequest) {
        // setting up headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        ResponseEntity<String> res = null;
        // finding user by email
        Optional<User> optionalUser = userRepo.findByEmail(loginRequest.getEmail());

        // if user is not found we throw an error message
        if (!optionalUser.isPresent()) {
            res = ResponseEntity.status(404).headers(headers).body(
                    "{\"message\": \"email" + loginRequest.getEmail() + " not found\"}");
        } else {
            try {
                User user = optionalUser.get();

                // if passwords match we send a res with the user inside
                // otherwise we throw an error message
                if (loginRequest.getPassword().equals(user.getPassword())) {
                    res = ResponseEntity.status(200).headers(headers).body(
                            "{\"user\": \"" + user + "\"}");
                } else {
                    res = ResponseEntity.status(401).headers(headers).body(
                            "{\"message\": \"password is wrong\"}");
                }
            } catch (Exception e) {
                res = ResponseEntity.status(500).headers(headers).body(
                        "{\"message\": \"something went wrong\"}");
            }
        }
        return res;
    }
}