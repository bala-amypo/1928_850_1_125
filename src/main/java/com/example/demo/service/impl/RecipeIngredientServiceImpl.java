package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final MenuItemRepository menuItemRepository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                       IngredientRepository ingredientRepository,
                                       MenuItemRepository menuItemRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public RecipeIngredient addIngredientToMenuItem(RecipeIngredient recipeIngredient) {

        if (recipeIngredient.getQuantity() <= 0) {
            throw new BadRequestException("Cost per unit");
        }

        ingredientRepository.findById(recipeIngredient.getIngredient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        menuItemRepository.findById(recipeIngredient.getMenuItem().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient existing = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        existing.setQuantity(quantity);
        return recipeIngredientRepository.save(existing);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeIngredientRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        recipeIngredientRepository.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeIngredientRepository.getTotalQuantityByIngredientId(ingredientId);
    }
}
