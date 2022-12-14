package com.example.testfor_ktelabs.controller.cron;

import com.example.testfor_ktelabs.entity.Position;
import com.example.testfor_ktelabs.entity.ProductStats;
import com.example.testfor_ktelabs.entity.Sale;
import com.example.testfor_ktelabs.entity.UserStats;
import com.example.testfor_ktelabs.repositories.*;
import com.example.testfor_ktelabs.utils.HelperWorkFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class GenerateStatsAndSaleImpl implements GenerateStatsAndSale {
    private final ProductRepository productRepository;
    private final UserStatsRepository userStatsRepository;
    private final ProductStatRepository productStatRepository;
    private final PositionsRepository positionsRepository;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    @Override
    @Scheduled(cron ="* * * * * *")
    public void generateSale() {
        productRepository.deleteSale();
        Random random = new Random();
        double v = random.nextInt((10 - 5) + 1) + 5;
        productRepository.setSale(v);
        generateStats();
    }

    @Override
    public void generateStats() {
        userRepository.findAll().forEach(user -> {
            userStatsRepository.deleteAllByUserId(user.getUserId());
            List<Sale> userSales = saleRepository.findAllByUserIdAndRegisterTrue(user.getUserId());
            Pair<Double, Double> totalCostAndSaleUser = HelperWorkFlow.getTotalCostAndSaleUser(userSales);

            UserStats userStats = new UserStats();
            userStats.setCheckCount((long) userSales.size());
            userStats.setTotalCost(totalCostAndSaleUser.getFirst());
            userStats.setTotalSale(totalCostAndSaleUser.getSecond());
            userStatsRepository.save(userStats);
        });
        productRepository.findAll().forEach(product -> {
            productStatRepository.deleteAllByProductId(product.getId());
            List<Position> allByProductId = positionsRepository.findAllByProductId(product.getId());
            Pair<Double, Double> totalCostAndSaleProduct = HelperWorkFlow.getTotalCostAndSaleProduct(allByProductId);

            ProductStats productStats = new ProductStats();
            productStats.setProductId(productStats.getProductId());
            productStats.setCheckCount((long) allByProductId.size());
            productStats.setTotalCostPosition(totalCostAndSaleProduct.getFirst());
            productStats.setTotalCostSale(totalCostAndSaleProduct.getSecond());
            productStatRepository.save(productStats);
        });
    }
}
