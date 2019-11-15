package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import org.springframework.stereotype.Service;

@Service
public interface MerchandiseService {
    boolean isProductBiggerThanShelf(WrappedProduct product, Shelf shelf);

    boolean storeOnShelf(WrappedProduct product, Shelf shelf);

    WrappedProduct removeFromShelf(WrappedProduct wrappedProduct, Shelf shelf);

    WrappedProduct setPrice(WrappedProduct product, double price);

    WrappedProduct setCategory(WrappedProduct wrappedProduct, Category category);

    WrappedProduct getProductFromStorage(String name);

    Product bringProductToStorage(WrappedProduct product);

    WrappedProduct prepare(Product product);

    Product prepare(WrappedProduct wrappedProduct);
}
