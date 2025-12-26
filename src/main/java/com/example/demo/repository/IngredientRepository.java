package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByNameIgnoreCase(String name);
}
