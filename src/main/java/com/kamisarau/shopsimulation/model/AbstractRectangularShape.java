package com.kamisarau.shopsimulation.model;

import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true, fluent = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class AbstractRectangularShape implements Storable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int x, y;
    int width;
    int height;
    @ManyToOne(targetEntity = Shelf.class, fetch = FetchType.EAGER)
    Shelf shelf;
}
