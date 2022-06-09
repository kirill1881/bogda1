package com.example.bogdashka.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "buy")
public class BuyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "username")
    String username;

    @Column(name = "sum")
    String sum;
}
