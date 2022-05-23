package com.example.spartans.controllers;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import com.example.spartans.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIkeyController {
    @Autowired
    UserRepository userRepo;

    @PostMapping("/generate-api-key")
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
}