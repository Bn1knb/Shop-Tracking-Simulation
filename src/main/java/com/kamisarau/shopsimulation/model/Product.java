package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@DiscriminatorValue("product")
@NoArgsConstructor
public class Product extends AbstractRectangularShape implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String name;
    private double price;

    public Product(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}

