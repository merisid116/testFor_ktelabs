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
@Table(name = "user_statistic")
@XmlRootElement
public class UserStats extends BaseStats{
    @Id
    private Long id;
    private Long userId;
    private Long checkCount;
    private Double totalCost;
    private Double totalSale;
}
