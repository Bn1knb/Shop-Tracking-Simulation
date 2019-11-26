package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import org.springframework.stereotype.Service;

@Service
public interface WrappedProductService {
    WrappedProduct setPrice(WrappedProduct product, double price);

    WrappedProduct setCategory(WrappedProduct product, Category category);
}
