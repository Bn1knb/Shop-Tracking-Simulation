package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Container;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Storable;

import java.util.List;

public interface MerchandiseService {
    boolean canStore(Storable item, Container container);

    List<Storable> storeOnShelf(Storable item, Container container);

    Storable removeItem(int index, Container container);

    Product setPrice(Product product, double price);
}
