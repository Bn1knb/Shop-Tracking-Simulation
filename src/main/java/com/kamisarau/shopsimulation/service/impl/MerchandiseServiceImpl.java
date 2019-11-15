package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.*;
import com.kamisarau.shopsimulation.service.MerchandiseService;
import com.kamisarau.shopsimulation.service.ShelfService;
import com.kamisarau.shopsimulation.service.StorageService;
import com.kamisarau.shopsimulation.service.WrappedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    private WrappedProductService wrappedProductService;
    private StorageService storageService;
    private ShelfService shelfService;

    @Autowired
    public MerchandiseServiceImpl(WrappedProductService wrappedProductService, StorageService storageService, ShelfService shelfService) {
        this.wrappedProductService = wrappedProductService;
        this.storageService = storageService;
        this.shelfService = shelfService;
    }

    @Override
    public boolean isProductBiggerThanShelf(WrappedProduct product, Shelf shelf) {
        return shelf.getHeight() * shelf.getWidth() < product.getHeight() * product.getWidth();
    }

    @Override
    public boolean storeOnShelf(WrappedProduct product, Shelf shelf) {

        if (isProductBiggerThanShelf(product, shelf)) {
            return false;
        }

        if (product.getWidth() < product.getHeight()) {
            //always rotate so the width is always bigger than height
            int temp = product.getWidth();
            product.setWidth(product.getHeight());
            product.setHeight(temp);
        }

        List<WrappedProduct> copyWithNewItem = new ArrayList<>(shelf.getProducts());
        copyWithNewItem.add(product);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;

        for (WrappedProduct storedItem : copyWithNewItem) {

            if (shelf.getHeight() < shelfLevel + storedItem.getHeight()) {
                return false;
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

        shelf.setProducts(copyWithNewItem);
        return true;
    }

    @Override
    public WrappedProduct removeFromShelf(WrappedProduct wrappedProduct, Shelf shelf) {
        return shelfService.removeProduct(wrappedProduct.getName(), shelf);
    }

    @Override
    public WrappedProduct setPrice(WrappedProduct product, double price) {
        return wrappedProductService.setPrice(product, price);
    }

    @Override
    public WrappedProduct setCategory(WrappedProduct wrappedProduct, Category category) {
        return wrappedProductService.setCategory(wrappedProduct, category);
    }

    @Override
    public WrappedProduct getProductFromStorage(String productName) {
        Product product = storageService.removeProduct(productName);
        return prepare(product);
    }

    @Override
    public Product bringProductToStorage(WrappedProduct product) {
        Product prepared = prepare(product);
        return storageService.storeProduct(prepared);
    }

    @Override
    public WrappedProduct prepare(Product product) {

        return new WrappedProduct()
                .setName(product.getName())
                .setHeight(product.getHeight())
                .setWidth(product.getWidth());
    }

    @Override
    public Product prepare(WrappedProduct wrappedProduct) {
        return new Product()
                .setName(wrappedProduct.getName())
                .setHeight(wrappedProduct.getHeight())
                .setWidth(wrappedProduct.getWidth());
    }
}
