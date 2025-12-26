package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String unit;
    private BigDecimal costPerUnit;
    private Boolean active = true;

    // getters & setters
}
