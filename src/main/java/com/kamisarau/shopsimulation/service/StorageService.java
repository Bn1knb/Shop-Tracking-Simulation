package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.exception.NoProductFound;
import com.kamisarau.shopsimulation.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StorageService {
    Product storeProduct(Product product);

    Product storeProducts(Product product, int amount);

    Product removeProduct(String productName) throws NoProductFound;

    List<Product> removeProducts(String productName, int amount) throws NoProductFound;
}
