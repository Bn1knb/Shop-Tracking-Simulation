package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.ShopSimulationApplication;
import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.service.impl.MerchandiseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ShopSimulationApplication.class)
@ActiveProfiles("test")
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
    void testCanStoreOnShelf() {
        Shelf testShelf = new Shelf().setWidth(10).setHeight(10);
        WrappedProduct testProduct = new WrappedProduct().setWidth(5).setHeight(5);

        boolean actual = merchandiseService.storeOnShelf(testProduct, testShelf);

        assertTrue(actual);
    }

    @Test
    void testCantStoreOnShelf() {
        Shelf testShelf = new Shelf().setWidth(10).setHeight(10);
        WrappedProduct testProduct = new WrappedProduct().setWidth(10).setHeight(10);
        merchandiseService.storeOnShelf(testProduct, testShelf);

        boolean actual = merchandiseService.storeOnShelf(testProduct, testShelf);

        assertFalse(actual);
    }
}