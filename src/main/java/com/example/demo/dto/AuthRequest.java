package com.example.demo.dto;

// No external imports required

public class AuthRequest {

    private String email;
    private String password;

    // No-args constructor (required by Spring & tests)
    public AuthRequest() {
    }

    // Getters and setters
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
}
