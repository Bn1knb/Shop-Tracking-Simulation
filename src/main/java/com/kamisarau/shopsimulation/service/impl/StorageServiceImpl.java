package com.kamisarau.shopsimulation.service.impl;

import com.kamisarau.shopsimulation.exception.NoProductFound;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Storage;
import com.kamisarau.shopsimulation.repository.StorageRepository;
import com.kamisarau.shopsimulation.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class StorageServiceImpl implements StorageService {
    private StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public Product storeProduct(Product newProduct) {
        Storage storage = storageRepository.getByProductName(newProduct.getName())
                .orElse(
                        new Storage()
                                .setProduct(newProduct)
                                .setAmount(0)
                );
        storage.setAmount(storage.getAmount() + 1);

        storageRepository.save(storage);
        return storage.getProduct();
    }

    @Override
    public Product storeProduct(Product newProduct, int amount) {
        Storage storage = storageRepository.getByProductName(newProduct.getName())
                .orElse(
                        new Storage()
                                .setProduct(newProduct)
                                .setAmount(0)
                );
        storage.setAmount(storage.getAmount() + amount);

        storageRepository.save(storage);
        return storage.getProduct();
    }

    @Override
    public Product removeProduct(String productName) throws NoProductFound {
        Storage storage = storageRepository.getByProductName(productName)
                .orElseThrow(NoProductFound::new);

        if (storage.getAmount() == 0) throw new NoProductFound("The storage is empty");

        storage.setAmount(storage.getAmount() - 1);

        storageRepository.save(storage);
        return storage.getProduct();
    }

    @Override
    public List<Product> removeProduct(String productName, int amount) throws NoProductFound {
        Storage storage = storageRepository.getByProductName(productName)
                .orElseThrow(NoProductFound::new);

        if (storage.getAmount() == 0 || storage.getAmount() < amount) {
            throw new NoProductFound("The given amount of products is higher then is being stored");
        }

        List<Product> products = new ArrayList<>(amount);

        IntStream.rangeClosed(1, amount).forEach(
                i -> {
                    Product stored = storage.getProduct();
                    Product removed = new Product()
                            .setHeight(stored.getHeight())
                            .setWidth(stored.getWidth())
                            .setName(stored.getName());

                    products.add(removed);
                    storage.setAmount(storage.getAmount() - 1);
                }
        );

        storageRepository.save(storage);
        return products;
    }
}
