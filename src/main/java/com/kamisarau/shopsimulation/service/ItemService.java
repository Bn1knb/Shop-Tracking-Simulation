package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    void bringBack(AbstractRectangularItem item);

    AbstractRectangularItem getItemById(Long id);

    List<AbstractRectangularItem> getItemsWithCategory(Category category);


}
