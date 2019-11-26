package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.repository.ShelfRepository;
import com.kamisarau.shopsimulation.service.impl.ShelfServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO add bad cases
@ExtendWith(MockitoExtension.class)
class ShelfServiceImplTest {
    @Mock
    ShelfRepository shelfRepository;
    @InjectMocks
    ShelfServiceImpl shelfService;

    @Test
    void removeProductByIdTest() {
        Shelf testShelf = new Shelf();
        testShelf.setProducts(Stream.of(
                new WrappedProduct().setName("test product"),
                new WrappedProduct().setName("test product2")
        ).collect(Collectors.toList()));

        WrappedProduct removed = shelfService.removeProduct(1, testShelf);

        assertFalse(testShelf.getProducts().contains(removed));
    }

    @Test
    void removeProductByNameTest() {
        Shelf testShelf = new Shelf();
        testShelf.setProducts(Stream.of(
                new WrappedProduct().setName("toRemove")
        ).collect(Collectors.toList()));

        WrappedProduct removed = shelfService.removeProduct("toRemove", testShelf);

        assertFalse(testShelf.getProducts().contains(removed));
    }

    @Test
    void storeProductTest() {
        Shelf testShelf = new Shelf();
        WrappedProduct productToStore = new WrappedProduct().setName("test product");

        shelfService.storeProduct(productToStore, testShelf);

        assertTrue(testShelf.getProducts().contains(productToStore));
    }

    @Test
    void storeProductsTest() {
        Shelf testShelf = new Shelf();
        List<WrappedProduct> productsToStore = Stream.of(
                new WrappedProduct().setName("test product1"),
                new WrappedProduct().setName("test product2")
        ).collect(Collectors.toList());

        shelfService.storeProduct(productsToStore, testShelf);

        assertTrue(testShelf.getProducts().containsAll(productsToStore));
    }
}