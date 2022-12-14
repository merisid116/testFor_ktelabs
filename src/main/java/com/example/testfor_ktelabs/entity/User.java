package com.example.testfor_ktelabs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private double firstDiscount;
    private double secondDiscount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User aUser = (User) o;
        return Double.compare(aUser.firstDiscount, firstDiscount) == 0 && Double.compare(aUser.secondDiscount, secondDiscount) == 0 && Objects.equals(userId, aUser.userId) && Objects.equals(name, aUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, firstDiscount, secondDiscount);
    }
}
