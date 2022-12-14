package com.example.testfor_ktelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    private Long userId;
    private Long checkNumber;
    private LocalDate dateOfSale;
    private boolean register;
    @OneToMany(mappedBy = "sale")
    private List<Position> positions;

    @PrePersist
    private void init(){
        dateOfSale = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id && Objects.equals(userId, sale.userId) && Objects.equals(checkNumber, sale.checkNumber) && Objects.equals(dateOfSale, sale.dateOfSale) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, checkNumber, dateOfSale);
    }
}
