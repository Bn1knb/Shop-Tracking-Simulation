package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<AbstractRectangularItem, Long> {
}
