package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.repository.ProductRepository;
import com.kamisarau.shopsimulation.repository.WrappedProductRepository;
import com.kamisarau.shopsimulation.service.MerchandiseService;
import com.kamisarau.shopsimulation.service.Randomiser;
import com.kamisarau.shopsimulation.service.StorageService;
import com.kamisarau.shopsimulation.util.RandomProductNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class RandomiserImpl implements Randomiser {
    private ProductRepository productRepository;
    private StorageService storageService;
    private MerchandiseService merchandiseService;
    private WrappedProductRepository wrappedProductRepository;
    private List<String> storedProductNames;

    @Autowired
    public RandomiserImpl(ProductRepository productRepository, StorageService storageService,
                          MerchandiseService merchandiseService, WrappedProductRepository wrappedProductRepository) {
        this.productRepository = productRepository;
        this.storageService = storageService;
        this.merchandiseService = merchandiseService;
        this.wrappedProductRepository = wrappedProductRepository;
    }

    @Override
    public void populateStorageWithProducts() {
        int numberOfProducts = RANDOM.nextInt(50);
        storedProductNames = new ArrayList<>();

        ArrayList<Product> products = new ArrayList<>(numberOfProducts);

        IntStream.rangeClosed(1, numberOfProducts).forEach(i ->
                products.add(
                        new Product()
                                .setName(randomEnum(RandomProductNames.class).name())
                                .setWidth(RANDOM.nextInt(10) + 1)
                                .setHeight(RANDOM.nextInt(10) + 1)
                )
        );

        products.forEach(product -> {
            productRepository.save(product);
            storageService.storeProduct(product);
            storedProductNames.add(product.getName());
        });

        storedProductNames = storedProductNames.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void doRandomOperations(int n, Shelf shelf) {
        for (int i = 0; i < n; i++) {

            Product removedFromStorage = storageService.removeProduct(
                    storedProductNames.get(RANDOM.nextInt(storedProductNames.size()))
            );
            WrappedProduct prepared = merchandiseService.prepare(removedFromStorage);
            wrappedProductRepository.save(prepared);

            merchandiseService.setPrice(prepared, RANDOM.nextInt(10) + 0.99);

            if (!merchandiseService.storeOnShelf(prepared, shelf)) {

                merchandiseService.bringProductToStorage(prepared);

                String[] names = shelf.getProducts().stream().map(WrappedProduct::getName).toArray(String[]::new);
                Product removedFromShelf = merchandiseService.prepare(
                        merchandiseService.removeFromShelf(names[RANDOM.nextInt(names.length)], shelf)
                );

                storageService.storeProduct(removedFromShelf);
            }
        }
    }

    @Override
    public void doRandomOperations() {
        //TODO loop while the stop button won't be pressed
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
