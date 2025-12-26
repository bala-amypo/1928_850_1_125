package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String role;
    private Boolean active = true;

    // getters & setters
}
