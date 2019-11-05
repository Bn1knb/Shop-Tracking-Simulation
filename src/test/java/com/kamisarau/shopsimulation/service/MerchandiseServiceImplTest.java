package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Container;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.Storable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MerchandiseServiceImplTest {
    @Autowired
    private MerchandiseService merchandiseService;
    private Container testContainer;

    @BeforeAll
    void setup() {
        testContainer = new Shelf()
                .setHeight(20)
                .setHeight(20);
    }

    @Test
    void testStoringItemDoesntFit() {
        Storable productThatDoesntFit = new Product();
        productThatDoesntFit.setHeight(5);
        productThatDoesntFit.setWidth(5);
        fullyPopulateContainer(testContainer);

        int expectedSize = testContainer.getItems().size();
        List<Storable> actualSize = merchandiseService.storeOnShelf(productThatDoesntFit, testContainer);

        assertEquals(expectedSize, actualSize.size());
    }

    @Test
    void testProductBiggerThenShelf() {
        Storable productBiggerThanShelf = new Product();
        productBiggerThanShelf.setWidth(21);
        productBiggerThanShelf.setHeight(21);

        assertFalse(merchandiseService.canStore(productBiggerThanShelf, testContainer));
    }

    void fullyPopulateContainer(Container container) {
        container.setItems(
                Stream.of(
                        new Product(4, 2),
                        new Product(7, 5),
                        new Product(9, 10),
                        new Product(16, 9)
                )
                        .collect(Collectors.toList())
        );
    }
}