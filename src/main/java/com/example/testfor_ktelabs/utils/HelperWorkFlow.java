package com.example.testfor_ktelabs.utils;

import com.example.testfor_ktelabs.entity.Position;
import com.example.testfor_ktelabs.entity.Product;
import com.example.testfor_ktelabs.entity.Rating;
import com.example.testfor_ktelabs.entity.Sale;
import org.springframework.data.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class HelperWorkFlow {
    public static String getAvgRating(Product product) {
        double v = product.getRatings().stream()
                .map(Rating::getValue)
                .mapToInt(x -> x)
                .average().orElse(0.0);
        return String.format("%.2f", v);
    }

    public static HashMap<String, Integer> getGrades(Product product) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {{
            put("1", 0);
            put("2", 0);
            put("3", 0);
            put("4", 0);
            put("5", 0);
        }};
        product.getRatings().forEach(rating -> {
            String key = String.valueOf(rating.getValue());
            Integer value = hashMap.get(key);
            value += 1;
            hashMap.put(key, value);
        });
        return hashMap;
    }

    public static int getUserRating(Product product, Long userId) {
        return product.getRatings().stream().filter(rating -> Objects.equals(rating.getUserId(), userId))
                .map(Rating::getValue).findFirst().orElse(0);
    }

    public static Pair<Double, Double> getTotalCostAndSaleUser(List<Sale> sales) {
        Double totalCost = 0d;
        Double totalSale = 0d;
        for (Sale s : sales) {
            for (Position p : s.getPositions()) {
                totalCost += p.getOriginalPrice();
                totalSale += p.getOriginalPrice() - p.getFinalPrice();
            }
        }
        return Pair.of(totalCost, totalSale);
    }

    public static Pair<Double, Double> getTotalCostAndSaleProduct(List<Position> allByProductId) {
        Double totalCost = 0d;
        Double totalSale = 0d;
            for (Position p : allByProductId) {
                totalCost += p.getOriginalPrice();
                totalSale += p.getOriginalPrice() - p.getFinalPrice();
        }
        return Pair.of(totalCost, totalSale);
    }
}
