package com.kamisarau.shopsimulation.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class AbstractRectangularItem {

    @Column(name = "X_POSITION")
    private int x;
    @Column(name = "Y_POSITION")
    private int y;
}
