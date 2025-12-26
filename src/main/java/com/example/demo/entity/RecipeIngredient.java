package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RecipeIngredient {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private MenuItem menuItem;

    private Double quantity;

    // getters & setters
}
