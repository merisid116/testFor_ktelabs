package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.ProductStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductStatRepository extends JpaRepository<ProductStats, Long> {
    Optional<ProductStats> findByProductId(Long productId);

    void deleteAllByProductId(Long productId);
}
