package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/recipe-ingredients")
@SecurityRequirement(name="bearerAuth")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(
            RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @PostMapping
    public ResponseEntity<RecipeIngredient> addIngredient(
            @RequestBody RecipeIngredient recipeIngredient) {

        return new ResponseEntity<>(
                recipeIngredientService.addIngredientToMenuItem(recipeIngredient),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeIngredient> updateQuantity(
            @PathVariable Long id,
            @RequestBody Double quantity) {

        return ResponseEntity.ok(
                recipeIngredientService.updateRecipeIngredient(id, quantity));
    }

    @GetMapping("/menu-item/{menuItemId}")
    public ResponseEntity<List<RecipeIngredient>> getIngredientsByMenuItem(
            @PathVariable Long menuItemId) {

        return ResponseEntity.ok(
                recipeIngredientService.getIngredientsByMenuItem(menuItemId));
    }

    @DeleteMapping("/{id}")
    public void removeIngredient(@PathVariable Long id) {
        recipeIngredientService.removeIngredientFromRecipe(id);
    }

    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public ResponseEntity<Double> getTotalQuantity(
            @PathVariable Long ingredientId) {

        return ResponseEntity.ok(
                recipeIngredientService.getTotalQuantityOfIngredient(ingredientId));
    }
}
