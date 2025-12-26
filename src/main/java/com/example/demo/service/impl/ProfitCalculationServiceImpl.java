package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final RecipeIngredientRepository recipeIngredientRepository;

    public ProfitCalculationServiceImpl(
            RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public double calculateProfit(Long recipeId, double sellingPrice) {

        List<RecipeIngredient> ingredients =
                recipeIngredientRepository.findByRecipeId(recipeId);

        double totalCost = 0.0;

        for (RecipeIngredient ri : ingredients) {
            double ingredientCost =
                    ri.getIngredient().getCostPerUnit() * ri.getQuantity();
            totalCost += ingredientCost;
        }

        return sellingPrice - totalCost;
    }
}
