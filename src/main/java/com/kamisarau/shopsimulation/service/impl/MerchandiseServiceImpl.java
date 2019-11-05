package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.Container;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Storable;
import com.kamisarau.shopsimulation.service.MerchandiseService;
import com.kamisarau.shopsimulation.service.ProductService;
import com.kamisarau.shopsimulation.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    private ProductService productService;
    private ContainerService containerService;

    @Autowired
    public MerchandiseServiceImpl(ProductService productService, ContainerService containerService) {
        this.productService = productService;
        this.containerService = containerService;
    }

    @Override
    public boolean canStore(Storable item, Container container) {
        return container.getHeight() * container.getWidth() >= item.getHeight() * item.getWidth();
    }

    @Override
    public List<Storable> storeOnShelf(Storable item, Container container) {

        if (!canStore(item, container)) {
            productService.bringBack(item);
            return container.getItems();
        }

        if (item.getWidth() < item.getHeight()) {
            item.rotate();
        }

        List<Storable> withNewItem = new ArrayList<>(container.getItems()); //TODO think of caching this
        withNewItem.add(item);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;

        for (Storable storedItem : withNewItem) {
            if (container.getHeight() < shelfLevel + storedItem.getHeight()) {
                productService.bringBack(item);
                return container.getItems();
            }

            if (container.getWidth() + 1 < x + storedItem.getWidth()) {
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

        return container.getItems();
    }

    @Override
    public Storable removeItem(int index, Container container) {
        return container.getItems().remove(index);//TODO change similar to the service abstraction
    }

    @Override
    public Product setPrice(Product product, double price) {
        product.setPrice(price);
        //TODO move this to the price service and save the priceChangeHistory
        return product;
    }
}
