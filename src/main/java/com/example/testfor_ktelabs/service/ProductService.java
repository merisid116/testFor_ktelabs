package com.example.testfor_ktelabs.service;

import com.example.testfor_ktelabs.dto.ProductDto;
import com.example.testfor_ktelabs.dto.ProductExtendInfo;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductExtendInfo getExtendProductInfo(Long id, Long userId);
    void gradeProduct(Long userId, Long productId, Integer grade);
}
