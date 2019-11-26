package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.exception.NoProductFound;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelfService {
    WrappedProduct removeProduct(int index, Shelf shelf) throws NoProductFound;

    WrappedProduct removeProduct(String name, Shelf shelf) throws NoProductFound;

    WrappedProduct storeProduct(WrappedProduct product, Shelf shelf);

    List<WrappedProduct> storeProduct(List<WrappedProduct> products, Shelf shelf);
}
