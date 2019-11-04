package com.kamisarau.shopsimulation.model;

import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class AbstractRectangularShape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    int x, y;
    int width;
    int height;
    @ManyToOne(targetEntity = Shelf.class, fetch = FetchType.EAGER)
    Shelf shelf;

    public void rotate() {
        int temp = width;
        width = height;
        height = temp;
    }
}
