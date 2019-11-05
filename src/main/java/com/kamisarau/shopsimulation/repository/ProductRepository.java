package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
