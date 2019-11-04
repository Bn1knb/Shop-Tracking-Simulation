package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.Storable;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MerchandiseService {
    private Shelf shelf;

    private boolean canStore(Storable item) {
        return shelf.getHeight() * shelf.getWidth() >= item.getHeight() * item.getWidth();
    }

    public List<Storable> storeOnShelf(Storable item) {
        boolean doesFit = true;

        if (!canStore(item)) {
            doesFit = false;
            //TODO return this item back
        }

        if (item.getWidth() < item.getHeight()) {
            item.rotate();
        }

        List<Storable> withNewItem = shelf.getProducts(); //TODO think of caching this
        withNewItem.add(item);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;
        for (Storable storedItem : withNewItem) {
            if (shelf.getHeight() < shelfLevel + storedItem.getHeight()) {
                doesFit = false;
                //TODO go back that new item doesnt fit
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

        if (!doesFit) {
            shelf.getProducts().remove(shelf.getProducts().size() - 1);
            //TODO bring back to store
            //TODO service store in database
        }

        return shelf.getProducts();
    }

    public Storable removeItem(int index) {
        return shelf.getProducts().remove(index);
    }

    public Product setPrice(Product product, double price) {
        product.setPrice(price);
        //TODO move this to the price service and save the priceChangeHistory
        return product;
    }
}
