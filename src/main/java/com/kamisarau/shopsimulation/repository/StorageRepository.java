package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM shop_storage WHERE product_name = :name")
    Optional<Storage> getByProductName(@Param("name") String name);
}
