package com.example.testfor_ktelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_statistic")
@XmlRootElement
public class ProductStats extends BaseStats{
    @Id
    private Long id;
    private Long productId;
    private Long checkCount;
    private Double totalCostPosition;
    private Double totalCostSale;
}
