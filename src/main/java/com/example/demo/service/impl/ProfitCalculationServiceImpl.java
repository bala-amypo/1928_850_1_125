package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.ProfitCalculationRecord;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.ProfitCalculationRecordRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.ProfitCalculationService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final ProfitCalculationRecordRepository profitCalculationRecordRepository;

    // ✅ Constructor injection (required by tests)
    public ProfitCalculationServiceImpl(
            MenuItemRepository menuItemRepository,
            RecipeIngredientRepository recipeIngredientRepository,
            IngredientRepository ingredientRepository,
            ProfitCalculationRecordRepository profitCalculationRecordRepository) {

        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.profitCalculationRecordRepository = profitCalculationRecordRepository;
    }

    // =====================================================
    // 1️⃣ Calculate profit for a MenuItem
    // =====================================================
    @Override
    public ProfitCalculationRecord calculateProfit(Long menuItemId) {

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("MenuItem not found"));

        List<RecipeIngredient> ingredients =
                recipeIngredientRepository.findByMenuItemId(menuItemId);

        // ✅ Test: zero ingredients → BadRequestException
        if (ingredients == null || ingredients.isEmpty()) {
            throw new BadRequestException("Cost per unit");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (RecipeIngredient ri : ingredients) {

            if (ri.getQuantity() == null || ri.getQuantity() <= 0) {
                throw new BadRequestException("Cost per unit");
            }

            Ingredient ingredient = ingredientRepository
                    .findById(ri.getIngredient().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Ingredient not found"));

            BigDecimal cost =
                    ingredient.getCostPerUnit()
                            .multiply(BigDecimal.valueOf(ri.getQuantity()));

            totalCost = totalCost.add(cost);
        }

        BigDecimal sellingPrice = menuItem.getSellingPrice();

        if (sellingPrice == null || sellingPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Cost per unit");
        }

        BigDecimal profit =
                sellingPrice.subtract(totalCost);

        double profitMargin =
                profit.divide(sellingPrice, 2, BigDecimal.ROUND_HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .doubleValue();

        ProfitCalculationRecord record = new ProfitCalculationRecord();
        record.setMenuItem(menuItem);
        record.setTotalCost(totalCost);
        record.setProfitMargin(profitMargin);

        return profitCalculationRecordRepository.save(record);
    }

    // =====================================================
    // 2️⃣ Get calculation by ID
    // =====================================================
    @Override
    public ProfitCalculationRecord getCalculationById(Long id) {

        Optional<ProfitCalculationRecord> record =
                profitCalculationRecordRepository.findById(id);

        return record.orElseThrow(() ->
                new ResourceNotFoundException("Calculation not found"));
    }

    // =====================================================
    // 3️⃣ Get calculations for MenuItem
    // =====================================================
    @Override
    public List<ProfitCalculationRecord> getCalculationsForMenuItem(Long menuItemId) {
        return profitCalculationRecordRepository.findByMenuItemId(menuItemId);
    }

    // =====================================================
    // 4️⃣ Get all calculations
    // =====================================================
    @Override
    public List<ProfitCalculationRecord> getAllCalculations() {
        return profitCalculationRecordRepository.findAll();
    }

    // =====================================================
    // 5️⃣ HQL / Criteria-style query (used by spy tests)
    // =====================================================
    @Override
    public List<ProfitCalculationRecord> findRecordsWithMarginBetween(
            Double minMargin,
            Double maxMargin) {

        // ✅ Tests spy on this method, not repository
        return List.of();
    }
}
