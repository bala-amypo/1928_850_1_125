package com.example.demo.repository;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    @Query("select sum(r.quantity) from RecipeIngredient r where r.ingredient.id = :ingredientId")
    Double getTotalQuantityByIngredientId(Long ingredientId);

    List<RecipeIngredient> findByMenuItemId(Long menuItemId);

    boolean existsByMenuItemId(Long menuItemId);
}
