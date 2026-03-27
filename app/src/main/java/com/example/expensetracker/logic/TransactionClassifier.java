package com.example.expensetracker.logic;

import com.example.expensetracker.data.entity.Category;

import java.util.List;

public class TransactionClassifier {
    
    public static Integer classify(String merchant, List<Category> allCategories) {
        if (merchant == null || merchant.equals("Unknown")) return null;
        
        String lowerMerch = merchant.toLowerCase();
        
        // Match merchant against keywords in categorise
        for (Category cat : allCategories) {
            if (cat.keywords != null) {
                String[] keywords = cat.keywords.split(",");
                for (String kw : keywords) {
                    if (lowerMerch.contains(kw.trim().toLowerCase())) {
                        return cat.id;
                    }
                }
            }
        }
        return null; // Unclassified
    }
}
