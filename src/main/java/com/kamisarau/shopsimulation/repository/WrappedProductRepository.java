package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.WrappedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WrappedProductRepository extends JpaRepository<WrappedProduct, Long> {
}
