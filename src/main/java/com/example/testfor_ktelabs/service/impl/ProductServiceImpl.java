package com.example.testfor_ktelabs.service.impl;

import com.example.testfor_ktelabs.dto.ProductDto;
import com.example.testfor_ktelabs.dto.ProductExtendInfo;
import com.example.testfor_ktelabs.entity.Product;
import com.example.testfor_ktelabs.entity.Rating;
import com.example.testfor_ktelabs.repositories.ProductRepository;
import com.example.testfor_ktelabs.repositories.RatingRepository;
import com.example.testfor_ktelabs.service.ProductService;
import com.example.testfor_ktelabs.utils.HelperWorkFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> list = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            list.add(new ProductDto(product.getId(), product.getTitle(), product.getPrice()));
        });
        return list;
    }

    @Override
    public ProductExtendInfo getExtendProductInfo(Long id, Long userId) {
        AtomicReference<ProductExtendInfo> extendInfo = new AtomicReference<>(new ProductExtendInfo());
        productRepository.findById(id).ifPresent(product -> {
            ProductExtendInfo info = extendInfo.get();
            info.setDescription(product.getDescription());
            info.setAvgRating(HelperWorkFlow.getAvgRating(product));
            info.setUserRating(HelperWorkFlow.getUserRating(product, userId));
            info.setGrades(HelperWorkFlow.getGrades(product));
        });

        return extendInfo.get();
    }

    @Override
    public void gradeProduct(Long userId, Long productId, Integer grade) {
        productRepository.findById(productId).ifPresent(product -> {
            if (Objects.isNull(grade)) {
                ratingRepository.findByUserIdAndProduct(userId, product).ifPresent(ratingRepository::delete);
            } else {
                ratingRepository.findByUserIdAndProduct(userId, product).ifPresent(rating -> {
                    updateRating(userId, product, grade, rating);
                });
                ratingRepository.findByUserIdAndProduct(userId,product).orElseGet(() -> {
                    Rating rating = new Rating();
                    updateRating(userId, product, grade, rating);
                    return rating;
                });
            }
        });
    }


    private void updateRating(Long userId, Product product, int grade, Rating rating) {
        rating.setValue(grade);
        rating.setProduct(product);
        rating.setUserId(userId);
        ratingRepository.save(rating);
    }
}
