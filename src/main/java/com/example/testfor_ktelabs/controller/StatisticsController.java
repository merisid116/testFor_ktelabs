package com.example.testfor_ktelabs.controller;

import com.example.testfor_ktelabs.entity.BaseStats;
import com.example.testfor_ktelabs.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;
    @GetMapping
    public BaseStats getStats(@RequestParam(value = "userId", required = false) Long userId,
                              @RequestParam(value = "productId", required = false) Long productId) {
        return statisticsService.getStats(userId, productId);
    }
}
