package com.example.testfor_ktelabs.controller;

import com.example.testfor_ktelabs.dto.ProductDto;
import com.example.testfor_ktelabs.dto.ProductExtendInfo;
import com.example.testfor_ktelabs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Max;
import java.util.List;
import static com.example.testfor_ktelabs.constants.Constants.Headers.USER_ID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductExtendInfo getProduct(@PathVariable(value = "id") Long id,
                                        @RequestHeader(value = USER_ID) Long userId) {
        return productService.getExtendProductInfo(id, userId);
    }

    @PutMapping("/grade")
    public void gradeProduct(@RequestHeader(USER_ID) Long userId,
                             @RequestParam(value = "productId") Long productId,
                             @RequestParam(value = "grade", required = false) @Max(5) Integer grade) {
        productService.gradeProduct(userId, productId, grade);
    }
}
