package com.example.bogdashka.helper;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cont")
@Data
public class Cont {
    @Id
    private final Long id = Long.valueOf(1);

    @Column
    String count = "6000";
}
