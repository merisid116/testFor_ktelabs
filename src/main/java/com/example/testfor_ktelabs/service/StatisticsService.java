package com.example.testfor_ktelabs.service;

import com.example.testfor_ktelabs.entity.BaseStats;

public interface StatisticsService {
    BaseStats getStats(Long userId, Long productId);
}
