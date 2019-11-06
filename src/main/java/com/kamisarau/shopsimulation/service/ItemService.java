package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    void bringBack(Product product);

    AbstractRectangularItem getItemById(Long id);
}
