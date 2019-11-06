package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.AbstractRectangularItem;
import com.kamisarau.shopsimulation.model.Product;
import com.kamisarau.shopsimulation.model.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
    public void setup() {
        testShelf = new Shelf()
                .setWidth(20)
                .setHeight(20);

        testShelf.setItems(new ArrayList<>());
    }

    @Test
    public void testStoringItemDoesntFit() {
        AbstractRectangularItem productThatDoesntFit = new Product()
                .setWidth(5)
                .setHeight(5);

        fullyPopulateShelf(testShelf);
        int expectedSize = testShelf.getItems().size();

        List<AbstractRectangularItem> actual = merchandiseService.storeOnShelf(productThatDoesntFit, testShelf);

        assertEquals(expectedSize, actual.size());
    }

    @Test
    public void testStoringFits() {
        AbstractRectangularItem productThatFits = new Product()
                .setWidth(1)
                .setHeight(1);

        int expectedSize = testShelf.getItems().size();

        List<AbstractRectangularItem> actual = merchandiseService.storeOnShelf(productThatFits, testShelf);

        assertEquals(expectedSize, actual.size());
    }

    @Test
    public void testProductBiggerThenShelf() {
        AbstractRectangularItem productBiggerThanShelf = new Product()
                .setWidth(21)
                .setHeight(21);

        boolean actualResult = merchandiseService.canStore(productBiggerThanShelf, testShelf);

        assertFalse(actualResult);
    }

    private void fullyPopulateShelf(Shelf shelf) {
        shelf.setItems(
                Stream.of(
                        new Product().setWidth(10).setHeight(10),
                        new Product().setWidth(10).setHeight(10)
                )
                        .collect(Collectors.toList())
        );
    }
}