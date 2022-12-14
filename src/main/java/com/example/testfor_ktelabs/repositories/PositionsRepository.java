package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.Position;
import com.example.testfor_ktelabs.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionsRepository extends JpaRepository<Position, Long> {
    @Query(value = "select sum(p.finalPrice) from Position p where p.sale= :saleId")
    Double getTotalPrice(Sale saleId);
    List<Position> findAllByProductId(Long productId);
}
