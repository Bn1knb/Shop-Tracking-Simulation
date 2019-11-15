package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.Shelf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends CrudRepository<Shelf, Long> {
}
