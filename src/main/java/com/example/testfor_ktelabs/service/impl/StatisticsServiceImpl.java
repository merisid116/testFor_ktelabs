package com.example.testfor_ktelabs.service.impl;

import com.example.testfor_ktelabs.entity.BaseStats;
import com.example.testfor_ktelabs.entity.ProductStats;
import com.example.testfor_ktelabs.entity.UserStats;
import com.example.testfor_ktelabs.repositories.ProductStatRepository;
import com.example.testfor_ktelabs.repositories.UserStatsRepository;
import com.example.testfor_ktelabs.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final ProductStatRepository productStatRepository;
    private final UserStatsRepository userStatsRepository;

    @Override
    public BaseStats getStats(Long userId, Long productId) {
        if (Objects.nonNull(userId)) {
           return userStatsRepository.findByUserId(userId).orElse(new UserStats());
        }
        if (Objects.nonNull(productId)) {
            return productStatRepository.findByProductId(productId).orElse(new ProductStats());
        }
        return null;
    }
}
