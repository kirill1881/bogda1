package com.example.bogdashka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment")
@Data
@EqualsAndHashCode

public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "sum")
    double sum;

    @Column(name = "state")
    boolean ifDone;

    public PaymentModel() {

    }

    public PaymentModel(String name, double sum, boolean ifDone) {
        this.name = name;
        this.sum = sum;
        this.ifDone = ifDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentModel that = (PaymentModel) o;
        return Double.compare(that.sum, sum) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sum, ifDone);
    }

    public boolean isIfDone() {
        return ifDone;
    }
}
