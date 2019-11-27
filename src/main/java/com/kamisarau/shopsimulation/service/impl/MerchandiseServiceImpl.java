package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.service.MerchandiseService;
import com.kamisarau.shopsimulation.service.ShelfService;
import com.kamisarau.shopsimulation.service.StorageService;
import com.kamisarau.shopsimulation.service.WrappedProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MerchandiseServiceImpl implements MerchandiseService {
    private WrappedProductService wrappedProductService;
    private StorageService storageService;
    private ShelfService shelfService;


//TODO add try catch blocks to prevent NoProductFound crash
    @Autowired
    public MerchandiseServiceImpl(WrappedProductService wrappedProductService, StorageService storageService, ShelfService shelfService) {
        this.wrappedProductService = wrappedProductService;
        this.storageService = storageService;
        this.shelfService = shelfService;
    }

    @Override
    public boolean isProductBiggerThanShelf(WrappedProduct product, Shelf shelf) {
        log.info("Checking if product is bigger than shelf size: {}", shelf.getHeight() * shelf.getWidth() < product.getHeight() * product.getWidth());

        if (shelf.getHeight() * shelf.getWidth() < product.getHeight() * product.getWidth()) {
            return true;
        }
        return shelf.getWidth() < product.getWidth() || shelf.getHeight() < product.getHeight();
    }

    @Override
    public boolean storeOnShelf(WrappedProduct product, Shelf shelf) {

        if (product.getWidth() < product.getHeight()) {
            log.info("Rotating product: {} so the width: {} wiil be bigger than height: {}", product.getName(), product.getWidth(), product.getHeight());

            int temp = product.getWidth();
            product.setWidth(product.getHeight());
            product.setHeight(temp);
        }

        if (isProductBiggerThanShelf(product, shelf)) {
            log.warn("Given product is bigger than shelf size, exiting...");

            return false;
        }

        if (shelf.getProducts() == null) {
            shelf.setProducts(new ArrayList<>());
            shelf.store(product);

            return true;
        }

        List<WrappedProduct> copyWithNewItem = new ArrayList<>(shelf.getProducts());
        copyWithNewItem.add(product);
        log.debug("Creting the copy: {} of products on the shelf: {}", copyWithNewItem, shelf);

        int x = 1;
        int y = 1;
        int shelfLevel = 0;

        for (WrappedProduct storedItem : copyWithNewItem) {

            log.debug("Shelflevel is: {}", shelfLevel);

            if (shelf.getHeight() < shelfLevel + storedItem.getHeight()) {
                log.debug("Product: {} doesnt fit, exiting...", storedItem);
                return false;
            }

            if (shelf.getWidth() + 1 < x + storedItem.getWidth()) {
                log.debug("Moving position to the {}, {}", 1, shelfLevel);
                y = shelfLevel;
                x = 1;
            }

            if (storedItem.getHeight() > shelfLevel - y) {
                log.debug("Setting shelflevel to: {}", y + storedItem.getHeight());
                shelfLevel = y + storedItem.getHeight();
            }

            log.debug("Setting final position on: {}, {}", x, y);
            storedItem.setX(x);
            storedItem.setY(y);

            x += storedItem.getWidth();
        }

        shelfService.storeProduct(copyWithNewItem, shelf);
        log.info("Shelf: {} has: {} products", shelf, shelf.getProducts().size());
        log.info("Shelf index is: {}%", getShelfCapacityIndex(shelf));
        return true;
    }

    @Override
    public WrappedProduct removeFromShelf(WrappedProduct wrappedProduct, Shelf shelf) {
        return shelfService.removeProduct(wrappedProduct.getName(), shelf);
    }

    @Override
    public WrappedProduct removeFromShelf(String productName, Shelf shelf) {
        return shelfService.removeProduct(productName, shelf);
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

    private double getShelfCapacityIndex(Shelf shelf) {
        double sumOfProductsParams = shelf.getProducts().stream()
                .mapToInt(wrappedProduct -> wrappedProduct.getWidth() * wrappedProduct.getHeight()).sum();

        return sumOfProductsParams / (shelf.getHeight() * shelf.getWidth()) * 100;
    }
}
