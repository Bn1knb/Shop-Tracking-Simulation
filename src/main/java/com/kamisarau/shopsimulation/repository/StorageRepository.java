package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    //@Query(value = "select all from Storage where product.name = name") //TODO write query when tables are generated "select * from Storage where product.name = name"
    Optional<Storage> getByProductName(String name);
}
