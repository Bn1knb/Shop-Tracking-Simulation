package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.RectangularShape;
import com.kamisarau.shopsimulation.model.Shelf;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MerchandiseService {
    private Shelf shelf;

    private boolean canStore(RectangularShape item) {
        return shelf.getHeight() * shelf.getWidth() >= item.getHeight() * item.getWidth();
    }

    public void storeOnShelf(RectangularShape item) {
        boolean doesFit = true;

        if (!canStore(item)) {
            doesFit = false;
            //TODO return this item back
        }

        if (item.getWidth() < item.getHeight()) {
            item.rotate();
        }

        List<RectangularShape> withNewItem = shelf.getProducts();
        withNewItem.add(item);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;
        for (RectangularShape storedItem : withNewItem) {
            if (shelf.getHeight() < shelfLevel + storedItem.getHeight()) {
                doesFit = false;
                //TODO go back that new item doesnt fit
            }

            if (shelf.getWidth() < x + storedItem.getWidth()) {
                y = shelfLevel;
                x = 1;
            } else if (storedItem.getHeight() > shelfLevel) {
                shelfLevel += storedItem.getHeight();
            }

            storedItem
                    .setX(x)
                    .setY(y);

            x += storedItem.getWidth();
        }

        if (doesFit) {
            shelf.setProducts(withNewItem);
            //TODO service store in database
        } else {
            bringBack(item);
        }
    }

    public void bringBack(RectangularShape item) {
        //TODO back on sklad
    }
}
