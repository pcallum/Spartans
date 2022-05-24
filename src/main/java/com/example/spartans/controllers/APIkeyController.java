package com.example.spartans.controllers;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
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
    private byte[] getApiKey(@PathVariable String id) {
        byte[] privateKey = null;
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            privateKey = user.getApiKey();
        }
        return privateKey;
    }

    @PostMapping("/set-api-key/{id}")
    private ResponseEntity<String> setApiKey(@PathVariable String id,
            @RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> res = null;
        LoginController loginController = new LoginController();
        res = loginController.login(userRepo, loginRequest);

        System.out.println("res " + res);

        if (res.getStatusCodeValue() == 404 || res.getStatusCodeValue() == 401) {
            return res;
        }

        try {
            Optional<User> optionalUser = userRepo.findById(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                if (user.getRole().equals("admin")) {
                    user.setApiKey(this.generateAPIkey());
                    userRepo.save(user);
                    res = new ResponseEntity<>(this.message + "api key was set\"}", HttpStatus.ACCEPTED);
                } else {
                    res = new ResponseEntity<>(this.message + "not authorized\"}", HttpStatus.FORBIDDEN);
                }
            }
        } catch (Exception e) {
            res = new ResponseEntity<>(this.message + "something went wrong\"}", HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        }
        return res;
    }
}