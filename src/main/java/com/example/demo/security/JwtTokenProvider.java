package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;

public class JwtTokenProvider {
    public String generateToken(Authentication auth, User user) {
        return "token";
    }
}
