package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.service.MerchandiseService;
import com.kamisarau.shopsimulation.service.ItemService;
import com.kamisarau.shopsimulation.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    private ItemService itemService;
    private ShelfService shelfService;

    @Autowired
    public MerchandiseServiceImpl(ItemService itemService, ShelfService shelfService) {
        this.itemService = itemService;
        this.shelfService = shelfService;
    }

    @Override
    public boolean canStore(AbstractRectangularItem item, Shelf shelf) {
        return shelf.getHeight() * shelf.getWidth() >= item.getHeight() * item.getWidth();
    }

    @Override
    public List<AbstractRectangularItem> storeOnShelf(AbstractRectangularItem item, Shelf shelf) {

        if (!canStore(item, shelf)) {
            itemService.bringBack(item);
            return shelf.getItems();
        }

        if (item.getWidth() < item.getHeight()) {
            item.rotate();
        }

        List<AbstractRectangularItem> withNewItem = new ArrayList<>(shelf.getItems()); //TODO think of caching this
        withNewItem.add(item);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;

        for (AbstractRectangularItem storedItem : withNewItem) {
            if (shelf.getHeight() < shelfLevel + storedItem.getHeight()) {
                itemService.bringBack(item);
                return shelf.getItems();
            }

            if (shelf.getWidth() + 1 < x + storedItem.getWidth()) {
                y = shelfLevel;
                x = 1;
            }

            if (storedItem.getHeight() > shelfLevel - y) {
                shelfLevel = y + storedItem.getHeight();
            }

            storedItem.setX(x);
            storedItem.setY(y);

            x += storedItem.getWidth();
        }

        return shelf.getItems();
    }

    @Override
    public AbstractRectangularItem removeItem(int index, Shelf shelf) {
        return shelf.getItems().remove(index);//TODO change similar to the service abstraction
    }

    @Override
    public Product setPrice(Product product, double price) {
        product.setPrice(price);
        //TODO move this to the price service and save the priceChangeHistory
        return product;
    }
}
