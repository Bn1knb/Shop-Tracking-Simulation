package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class MerchandiseServiceImplTest {
    @Autowired
    private MerchandiseService merchandiseService;
    private Shelf testShelf;

    @BeforeEach
    void setup() {
        testShelf = new Shelf()
                .setWidth(20)
                .setHeight(20);
    }

    @Test
    void testStoringItemDoesntFit() {
        AbstractRectangularItem productThatDoesntFit = new Product();
        productThatDoesntFit.setHeight(5);
        productThatDoesntFit.setWidth(5);
        fullyPopulateShelf(testShelf);

        int expectedSize = testShelf.getItems().size();
        List<AbstractRectangularItem> actualSize = merchandiseService.storeOnShelf(productThatDoesntFit, testShelf);

        assertEquals(expectedSize, actualSize.size());
    }

    @Test
    void testProductBiggerThenShelf() {
        AbstractRectangularItem productBiggerThanShelf = new Product();
        productBiggerThanShelf.setWidth(21);
        productBiggerThanShelf.setHeight(21);

        assertFalse(merchandiseService.canStore(productBiggerThanShelf, testShelf));
    }

    void fullyPopulateShelf(Shelf shelf) {
        shelf.setItems(
                Stream.of(
                        new Product().setWidth(4).setHeight(2),
                        new Product().setWidth(7).setHeight(5),
                        new Product().setWidth(9).setHeight(10),
                        new Product().setWidth(16).setHeight(9)
                )
                        .collect(Collectors.toList())
        );
    }
}