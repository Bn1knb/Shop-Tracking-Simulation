package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularShape;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.Storable;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MerchandiseServiceTest {

    @Test
    void testStoringItemDoesntFit() {
        Shelf testShelf = new Shelf()
                .setHeight(20)
                .setHeight(20);

        Storable productThatDoesntFit = new Product(5, 5);

        List<Storable> storedItems = Stream.of(
                new Product(4, 2),
                new Product(7, 5),
                new Product(9, 10),
                new Product(16, 9)
        )
                .collect(Collectors.toList());

        testShelf.setProducts(storedItems);
        int expectedSize = storedItems.size();

        MerchandiseService service = new MerchandiseService(testShelf);
        List<Storable> actual = service.storeOnShelf(productThatDoesntFit);

        assertEquals(expectedSize, actual.size());
    }

    @Test
    void testProductBiggerThenShelf() {
        Shelf testShelf = new Shelf()
                .setHeight(20)
                .setHeight(20);

        Product productBiggerThanShelf = (Product) new Product(21, 21);

        MerchandiseService service = new MerchandiseService(testShelf);
        service.storeOnShelf(productBiggerThanShelf);

        assertTrue(testShelf.getProducts().isEmpty());
    }
}