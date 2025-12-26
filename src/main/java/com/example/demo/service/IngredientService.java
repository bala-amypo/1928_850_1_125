package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ing);
    Ingredient updateIngredient(Long id, Ingredient ing);
    Ingredient getIngredientById(Long id);
    List<Ingredient> getAllIngredients();
}
