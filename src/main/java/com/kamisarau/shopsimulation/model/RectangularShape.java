package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class RectangularShape {
    int x,y;
    int width;
    int height;

    public void rotate() {
        int temp = width;
        width = height;
        height = temp;
    }
}
