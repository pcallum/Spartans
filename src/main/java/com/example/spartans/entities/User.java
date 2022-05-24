package com.example.spartans.entities;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    public String id;

    @NonNull
    public String email;

    @NonNull
    public String password;

    public byte[] apiKey;

    @NonNull
    public String role;

    public User() {
    }

    public User(String id, String email, String password, byte[] apiKey, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.apiKey = apiKey;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getApiKey() {
        return apiKey;
    }

    public void setApiKey(byte[] apiKey) {
        this.apiKey = apiKey;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", apiKey='" + getApiKey() + "'" +
                ", role='" + getRole() + "'" +
                "}";
    }
}
