package com.kamisarau.shopsimulation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private double price;
}
