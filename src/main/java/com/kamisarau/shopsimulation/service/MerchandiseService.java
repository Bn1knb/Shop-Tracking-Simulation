package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchandiseService {
    boolean canStore(AbstractRectangularItem item, Shelf shelf);

    List<AbstractRectangularItem> storeOnShelf(AbstractRectangularItem item, Shelf shelf);

    AbstractRectangularItem removeItem(int index, Shelf shelf);

    Product setPrice(Product product, double price);
}
