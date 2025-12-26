package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class MenuItem {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private BigDecimal sellingPrice;
    private Boolean active = true;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    // getters & setters
}
