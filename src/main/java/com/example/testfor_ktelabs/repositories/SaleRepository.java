package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findAllByUserIdAndRegisterFalse(Long userId);
    List<Sale> findAllByUserIdAndRegisterTrue(Long userId);
    @Query(value = "select s.check_number from sales s where s.date_of_sale=CURRENT_DATE order by s.id limit 1", nativeQuery = true)
    Optional<Long> getLastSale();
}
