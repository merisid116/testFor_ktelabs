package com.example.testfor_ktelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long amount;
    private double originalPrice;
    private double finalPrice;
    private double finalDiscount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sale_id", nullable=false)
    private Sale sale;
}