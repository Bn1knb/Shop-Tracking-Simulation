package com.kamisarau.shopsimulation.model;

public interface Storable {
    int getHeight();

    int getWidth();

    void setHeight(int height);

    void setWidth(int width);

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void rotate();
}
