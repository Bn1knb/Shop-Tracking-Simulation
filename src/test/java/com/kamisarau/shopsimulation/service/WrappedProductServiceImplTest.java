package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.ShopSimulationApplication;
import com.kamisarau.shopsimulation.model.Category;
import com.kamisarau.shopsimulation.model.WrappedProduct;
import com.kamisarau.shopsimulation.repository.WrappedProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ShopSimulationApplication.class)
class WrappedProductServiceImplTest {
    @Autowired
    WrappedProductRepository wrappedProductRepository;
    @Autowired
    WrappedProductService wrappedProductService;

    @Test
    void setPriceTest() {
        WrappedProduct toSave = new WrappedProduct().setName("test product");

        WrappedProduct saved = wrappedProductService.setPrice(toSave, 11.1);

        assertEquals(saved.getPrice(), 11.1);
    }

    @Test
    void setPriceWithoutNameTest() {
        WrappedProduct toSave = new WrappedProduct();

        assertThrows(JpaSystemException.class, () -> wrappedProductService.setPrice(toSave, 11.1));
    }

    @Test
    void setCategoryTest() {
        WrappedProduct toSave = new WrappedProduct().setName("test product");

        WrappedProduct saved = wrappedProductService.setCategory(toSave, Category.FRUITS);

        assertEquals(saved.getCategory(), Category.FRUITS);
    }

    @Test
    void setCategoryWithoutNameTest() {
        WrappedProduct toSave = new WrappedProduct();

        assertThrows(JpaSystemException.class, () -> wrappedProductService.setCategory(toSave, Category.FRUITS));
    }
}