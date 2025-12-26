package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private Boolean active = true;

    @ManyToMany(mappedBy = "categories")
    private Set<MenuItem> menuItems = new HashSet<>();

    // getters & setters
}
