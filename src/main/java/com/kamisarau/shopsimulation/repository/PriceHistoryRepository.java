package com.kamisarau.shopsimulation.repository;

import com.kamisarau.shopsimulation.model.PricesChangeHistory;
import org.springframework.data.repository.CrudRepository;

public interface PriceHistoryRepository extends CrudRepository<PricesChangeHistory, Long> {
}
