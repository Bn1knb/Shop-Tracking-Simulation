package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.service.impl.MerchandiseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MerchandiseServiceImplTest {
    @Autowired
    MerchandiseServiceImpl merchandiseService;

    @Test
    void testIsProductBiggerThanShelf() {
        WrappedProduct testProduct = new WrappedProduct().setHeight(10).setWidth(10);
        Shelf shelf = new Shelf().setWidth(1).setHeight(1);

        boolean actual = merchandiseService.isProductBiggerThanShelf(testProduct, shelf);

        assertTrue(actual);
    }

    @Test
    void testStoreOnShelf() {
    }

    @Test
    void testRemoveFromShelf() {
    }

    @Test
    void testSetPrice() {
    }

    @Test
    void testSetCategory() {
    }

    @Test
    void testGetProductFromStorage() {
    }

    @Test
    void testBringProductToStorage() {
    }

    @Test
    void testPrepareProduct() {
    }

    @Test
    void testPrepareWrappedProduct() {
    }
}