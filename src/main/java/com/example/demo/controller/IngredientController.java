package com.example.demo.controller;

import com.example.demo.service.*;
import com.example.demo.entity.*;
import org.springframework.http.*;

public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    public ResponseEntity<Ingredient> createIngredient(Ingredient ing) {
        return new ResponseEntity<>(service.createIngredient(ing), HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deactivateIngredient(Long id) {
        Ingredient ing = service.getIngredientById(id);
        ing.setActive(false);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<java.util.List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(service.getAllIngredients());
    }
}
