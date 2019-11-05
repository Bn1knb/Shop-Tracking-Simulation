package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class AbstractRectangularItem extends Item {

    private int x, y;
    private int width;
    private int height;

    @ManyToOne(targetEntity = Shelf.class, fetch = FetchType.EAGER)
    private Shelf shelf;
    @ManyToOne(targetEntity = Storage.class, fetch = FetchType.EAGER)
    private Storage storage;

    public void rotate() {
        int temp = width;
        width = height;
        height = temp;
    }
}
