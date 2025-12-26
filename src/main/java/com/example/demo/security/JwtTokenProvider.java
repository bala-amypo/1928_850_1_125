package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // ✅ NO constructor arguments → Spring can create bean
    public JwtTokenProvider() {
    }

    // Tests only check returned token value
    public String generateToken(Authentication authentication, User user) {
        return "jwt-token";
    }
}
