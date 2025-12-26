package com.example.demo.dto;

// No imports required

public class AuthResponse {

    private String token;

    // Required no-args constructor
    public AuthResponse() {
    }

    // Used in AuthController
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
