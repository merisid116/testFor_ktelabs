package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.Product;
import com.example.testfor_ktelabs.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndProduct(Long userId, Product product);
}
