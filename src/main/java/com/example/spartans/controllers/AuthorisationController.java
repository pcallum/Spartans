package com.example.spartans.controllers;

import com.example.spartans.payload.request.LoginRequest;
import com.example.spartans.repositories.UserRepository;
import org.springframework.http.ResponseEntity;

public class AuthorisationController {
    public static ResponseEntity<String> checkAuthorisation(LoginRequest login, String apiKey,
            UserRepository userRepo) {
        APIkeyController apiController = new APIkeyController();

        ResponseEntity<String> res = null;

        // checking if credentials are right
        res = LoginController.handleLogin(res, login, userRepo);

        // if one of the credentials is wrong we throw an error message
        if (res.getStatusCodeValue() != 200) {
            return res;
        }

        // checking API key
        res = apiController.checkApiKey(login.getEmail(), userRepo, apiKey);

        return res;
    }
}
