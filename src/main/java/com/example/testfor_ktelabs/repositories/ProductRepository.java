package com.example.testfor_ktelabs.repositories;

import com.example.testfor_ktelabs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.discount=0 where p.discount <> 0")
    void deleteSale();

    @Modifying
    @Query(value = "update products p set p.discount=:discount where p.id = (select p.id from products p order by RAND() limit 1)", nativeQuery = true)
    void setSale(@Param("discount")double discount);
}
