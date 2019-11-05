package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.Storable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void bringBack(Storable item);

    Storable getProductById(Long id);

    List<Storable> getProductsWithCategory(Category category);


}
