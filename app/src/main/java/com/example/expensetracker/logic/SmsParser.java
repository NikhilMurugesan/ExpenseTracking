package com.example.expensetracker.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsParser {
    
    private static final Pattern DEBIT_PATTERN = Pattern.compile("(?i)(?:Rs\\.?|INR)\\s*([\\d,]+\\.?\\d*)\\s*(?:has been)?\\s*debited");
    private static final Pattern CREDIT_PATTERN = Pattern.compile("(?i)(?:Rs\\.?|INR)\\s*([\\d,]+\\.?\\d*)\\s*(?:has been)?\\s*credited");
    private static final Pattern MERCHANT_PATTERN = Pattern.compile("(?i)(?:info(?=\\b)|at|to|from)\\s+([A-Za-z0-9\\s.@]+?)(?:\\s+on|\\s+ref|\\s+upi|\\.)");
    private static final Pattern REF_PATTERN = Pattern.compile("(?i)(?:ref|upi ref|txn no|utr)[^\\d]*(\\d{10,14})");
    private static final Pattern PAYMENT_METHOD_PATTERN = Pattern.compile("(?i)(UPI|Card|Netbanking|NEFT|RTGS|IMPS)");

    public static ParsedData parseTransaction(String smsBody) {
        String type = null;
        double amount = 0.0;
        
        Matcher debitMatcher = DEBIT_PATTERN.matcher(smsBody);
        Matcher creditMatcher = CREDIT_PATTERN.matcher(smsBody);
        
        if (debitMatcher.find()) {
            type = "DEBIT";
            amount = parseAmount(debitMatcher.group(1));
        } else if (creditMatcher.find()) {
            type = "CREDIT";
            amount = parseAmount(creditMatcher.group(1));
        } else {
            return null; // Not a recognized transaction
        }
        
        Matcher merchantMatcher = MERCHANT_PATTERN.matcher(smsBody);
        String merchant = merchantMatcher.find() ? merchantMatcher.group(1).trim() : "Unknown";
        
        Matcher refMatcher = REF_PATTERN.matcher(smsBody);
        String refId = refMatcher.find() ? refMatcher.group(1) : null;
        
        Matcher methodMatcher = PAYMENT_METHOD_PATTERN.matcher(smsBody);
        String method = methodMatcher.find() ? methodMatcher.group(1).toUpperCase() : "UNKNOWN";
        
        return new ParsedData(amount, type, merchant, refId, method);
    }
    
    private static double parseAmount(String amountStr) {
        try {
            return Double.parseDouble(amountStr.replace(",", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
