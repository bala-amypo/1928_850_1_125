package com.example.demo.service;

import com.example.demo.entity.ProfitCalculationRecord;
import java.util.List;

public interface ProfitCalculationService {
    ProfitCalculationRecord calculateProfit(Long menuItemId);
    List<ProfitCalculationRecord> findRecordsWithMarginBetween(Double min, Double max);
}
