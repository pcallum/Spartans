package com.example.spartans.entities;

import com.example.spartans.payload.request.LoginRequest;

public class AdminRequest {
    private LoginRequest loginRequest;
    private Spartan spartan;
    private String api;

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

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
