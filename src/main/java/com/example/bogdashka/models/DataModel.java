package com.example.bogdashka.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data")
@Data
public class DataModel {
    @Id
    long id = 1;

    public long getId() {
        return id;
    }

    public String getFreeRobux() {
        return freeRobux;
    }

    public String getCourse() {
        return course;
    }

    @Column(name = "free_robux")
    String freeRobux;

    @Column(name = "course")
    String course;
}
