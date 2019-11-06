package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.exception.NoProductFound;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.repository.ProductRepository;
import com.kamisarau.shopsimulation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ProductRepository productRepository;

    @Autowired
    public ItemServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void bringBack(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getItemById(Long id) {
        return productRepository.findById(id).orElseThrow(NoProductFound::new);
    }
}
