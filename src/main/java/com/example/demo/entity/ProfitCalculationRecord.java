package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ProfitCalculationRecord {

    @Id @GeneratedValue
    private Long id;

    private Double profitMargin;

    @ManyToOne
    private MenuItem menuItem;

    // getters & setters
}
