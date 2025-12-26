package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import com.example.demo.service.*;
import java.util.*;

public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepo;
    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final ProfitCalculationRecordRepository recordRepo;

    public ProfitCalculationServiceImpl(
            MenuItemRepository m,
            RecipeIngredientRepository r,
            IngredientRepository i,
            ProfitCalculationRecordRepository p) {
        this.menuItemRepo = m;
        this.recipeRepo = r;
        this.ingredientRepo = i;
        this.recordRepo = p;
    }

    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {
        return recordRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public List<ProfitCalculationRecord> findRecordsWithMarginBetween(Double min, Double max) {
        return Collections.emptyList();
    }

    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return recordRepo.findAll();
    }

    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long id) {
        return recordRepo.findByMenuItemId(id);
    }

    @Override
    public void calculateProfit(Long menuItemId) {
        if (recipeRepo.findByMenuItemId(menuItemId).isEmpty()) {
            throw new BadRequestException("No ingredients");
        }
    }
}
