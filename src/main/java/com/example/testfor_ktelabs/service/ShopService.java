package com.example.testfor_ktelabs.service;

import com.example.testfor_ktelabs.dto.ProductBuyDto;
import com.example.testfor_ktelabs.dto.ProductRegisterSell;
import java.util.List;

public interface ShopService {
    long getTotalCost(List<ProductBuyDto> productBuyDtoList, Long userId);

    String registerSell(ProductRegisterSell productRegisterSell, Long userId);
}
