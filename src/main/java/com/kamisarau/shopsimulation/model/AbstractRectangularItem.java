package com.kamisarau.shopsimulation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class AbstractRectangularItem {

    @Column(name = "X_POSITION")
    @JsonIgnore
    private int x;
    @Column(name = "Y_POSITION")
    @JsonIgnore
    private int y;
}
