package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.Storable;
import com.kamisarau.shopsimulation.repository.ProductRepository;
import com.kamisarau.shopsimulation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void bringBack(Storable item) {
        productRepository.save(item);
    }
}
