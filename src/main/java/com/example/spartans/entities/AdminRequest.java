package com.example.spartans.entities;

import com.example.spartans.payload.request.LoginRequest;

public class AdminRequest {
    private LoginRequest loginRequest;
    private Spartan spartan;

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public Spartan getSpartan() {
        return spartan;
    }

    public void setSpartan(Spartan spartan) {
        this.spartan = spartan;
    }

    public AdminRequest(LoginRequest loginRequest, Spartan spartan) {
        this.loginRequest = loginRequest;
        this.spartan = spartan;
    }
}
