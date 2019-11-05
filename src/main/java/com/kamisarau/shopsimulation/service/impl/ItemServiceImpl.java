package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.repository.ItemRepository;
import com.kamisarau.shopsimulation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void bringBack(AbstractRectangularItem item) {
        itemRepository.save(item);
    }

    @Override
    public AbstractRectangularItem getItemById(Long id) {
        return null;
    }

    @Override
    public List<AbstractRectangularItem> getItemsWithCategory(Category category) {
        return null;
    }
}
