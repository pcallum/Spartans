package com.example.spartans.payload.request;

import com.mongodb.lang.NonNull;

public class SignupRequest {
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String role;

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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