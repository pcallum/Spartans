package com.example.spartans.controllers;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.example.spartans.entities.User;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/api")
public class APIkeyController {
    @Autowired
    UserRepository userRepo;

    private final String message = "{\"message\": \"";

    public byte[] generateAPIkey() {
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(512);
        byte[] privateKey = keyGen.generateKeyPair().getPrivate().getEncoded();
        return privateKey;
    }

    @PostMapping("/set-api-key/{id}")
    public ResponseEntity<String> setApiKey(@RequestParam String id) {
        ResponseEntity<String> res = null;
        try {
            Optional<User> optionalUser = userRepo.findById(id);

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();

                if(user.getRole().equals("admin")) {
                    user.setApiKey(generateAPIkey());
                    userRepo.save(user);
                    res = new ResponseEntity<>(this.message + "api key was set", HttpStatus.ACCEPTED);
                } else {
                    res = new ResponseEntity<>(this.message + "not authorized", HttpStatus.FORBIDDEN);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}