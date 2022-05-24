package com.example.spartans.controllers;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import com.example.spartans.entities.User;
import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class APIkeyController {
    @Autowired
    UserRepository userRepo;

    private final String message = "{\"message\": \"";

    // this function will be used in set api key API
    private byte[] generateAPIkey() {
        KeyPairGenerator keyGen = null;

        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(2048);
        byte[] privateKey = keyGen.generateKeyPair().getPrivate().getEncoded();
        return privateKey;
    }

    @GetMapping("/get-api-key/{id}")
    private ResponseEntity<String> getApiKey(@PathVariable String id, @RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> res = null;

        // checking if credentials are right
        res = LoginController.handleLogin(res, loginRequest, userRepo);

        // if one of the credentials is wrong we throw an error message
        if (res.getStatusCodeValue() == 404 || res.getStatusCodeValue() == 401) {
            return res;
        }

        byte[] privateKey = null;
        // finding the user by id so that we can get the API key from db
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            privateKey = user.getApiKey();
            String encodedKey = Base64.getEncoder().encodeToString(privateKey);
            res = ResponseEntity.status(200).body(
                    "{\"apiKey\": " + encodedKey + "\"}");
        }
        return res;
    }

    @PostMapping("/set-api-key/{id}")
    private ResponseEntity<String> setApiKey(@PathVariable String id,
            @RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> res = null;

        // checking if credentials are right
        res = LoginController.handleLogin(res, loginRequest, userRepo);

        // if one of the credentials is wrong we throw an error message
        if (res.getStatusCodeValue() == 404 || res.getStatusCodeValue() == 401) {
            return res;
        }

        try {
            Optional<User> optionalUser = userRepo.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // if the user has a role of admin we generate the API key,
                // save it into the db and sending a res
                if (user.getRole().equals("admin")) {
                    user.setApiKey(this.generateAPIkey());
                    userRepo.save(user);
                    res = ResponseEntity.status(201).body(
                            this.message + "api key was set\"}");
                } else {
                    // if the user doesn't have a role of admin we throw
                    // an error message
                    res = ResponseEntity.status(401).body(
                            this.message + "not authorized\"}");
                }
            }
        } catch (Exception e) {
            res = ResponseEntity.status(500).body(
                    this.message + "something went wrong\"}");
            e.printStackTrace();
        }
        return res;
    }
}