package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.ShopSimulationApplication;
import com.kamisarau.shopsimulation.exception.NoProductFound;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Storage;
import com.kamisarau.shopsimulation.repository.ProductRepository;
import com.kamisarau.shopsimulation.repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ShopSimulationApplication.class)
@ActiveProfiles("test")
class StorageServiceImplTest {
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    StorageService storageService;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setup() {

    }

    @Test
    void testAmountWhenProductExists() {
        Storage testStorage = new Storage();
        Product existing = new Product().setName("apple").setHeight(10).setWidth(10);
        testStorage.setProduct(existing).setAmount(1);
        productRepository.save(existing);
        storageRepository.save(testStorage);

        storageService.storeProduct(existing);

        int expectedAmount = 2;
        int actualAmount = storageRepository.getByProductName("apple").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testAmountWhenProductNotExist() {
        Storage testStorage = new Storage();
        Product newProduct = new Product().setName("orange").setHeight(10).setWidth(10);
        productRepository.save(newProduct);
        storageRepository.save(testStorage);

        storageService.storeProduct(newProduct);

        int expectedAmount = 1;
        int actualAmount = storageRepository.getByProductName("orange").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testStoreProductsWhenExists() {
        Storage testStorage = new Storage();
        Product existing = new Product().setName("banana").setHeight(10).setWidth(10);
        testStorage.setProduct(existing).setAmount(1);
        productRepository.save(existing);
        storageRepository.save(testStorage);

        storageService.storeProducts(existing, 10);

        int expectedAmount = 11;
        int actualAmount = storageRepository.getByProductName("banana").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testStoreProductsWhenNotExists() {
        Storage testStorage = new Storage();
        Product newProduct = new Product().setName("strawberry").setHeight(10).setWidth(10);
        productRepository.save(newProduct);
        storageRepository.save(testStorage);

        storageService.storeProducts(newProduct, 5);

        int expectedAmount = 5;
        int actualAmount = storageRepository.getByProductName("strawberry").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testRemoveProduct() {
        Storage testStorage = new Storage();
        Product existing = new Product().setName("lemon").setHeight(10).setWidth(10);
        testStorage.setProduct(existing).setAmount(5);
        productRepository.save(existing);
        storageRepository.save(testStorage);

        storageService.removeProduct("lemon");

        int expectedAmount = 4;
        int actualAmount = storageRepository.getByProductName("lemon").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testRemoveProducts() {
        Storage testStorage = new Storage();
        Product existing = new Product().setName("blueberry").setHeight(10).setWidth(10);
        testStorage.setProduct(existing).setAmount(5);
        productRepository.save(existing);
        storageRepository.save(testStorage);

        storageService.removeProducts("blueberry", 5);

        int expectedAmount = 0;
        int actualAmount = storageRepository.getByProductName("blueberry").get().getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testRemoveProductsWhenNotExist() {
        Storage testStorage = new Storage();
        storageRepository.save(testStorage);

        assertThrows(NoProductFound.class, () -> storageService.removeProducts("blueberry", 5));
    }
}