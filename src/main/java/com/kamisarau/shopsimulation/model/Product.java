package com.kamisarau.shopsimulation.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Product extends RectangularShape implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String name;
    private double price;
    @ManyToOne
    private Shelf shelf;
}
