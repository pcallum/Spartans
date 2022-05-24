package com.example.spartans.payload.request;

import com.mongodb.lang.NonNull;

public class LoginRequest {
    @NonNull
    private String email;
    
    @NonNull
    private String password;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}