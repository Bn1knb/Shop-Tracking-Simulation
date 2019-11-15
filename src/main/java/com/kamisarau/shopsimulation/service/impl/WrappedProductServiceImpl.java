package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.repository.WrappedProductRepository;
import com.kamisarau.shopsimulation.service.WrappedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WrappedProductServiceImpl implements WrappedProductService {
    private WrappedProductRepository wrappedProductRepository;

    @Autowired
    public WrappedProductServiceImpl(WrappedProductRepository wrappedProductRepository) {
        this.wrappedProductRepository = wrappedProductRepository;
    }

    @Override
    public WrappedProduct setPrice(WrappedProduct product, double price) {
        product.setPrice(price);
        return wrappedProductRepository.save(product);
    }

    @Override
    public WrappedProduct setCategory(WrappedProduct product, Category category) {
        product.setCategory(category);
        return wrappedProductRepository.save(product);
    }
}
