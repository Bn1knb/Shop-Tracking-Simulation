package com.kamisarau.shopsimulation.model;

import java.util.List;

public interface Container {
    void store(Storable item);

    int getHeight();

    int getWidth();

    void setHeight(int height);

    void setWidth(int width);

    List<Storable> getItems();

    void setItems(List<Storable> items);
}
