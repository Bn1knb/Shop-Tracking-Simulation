package com.kamisarau.shopsimulation.util;

import com.kamisarau.shopsimulation.service.LogServiceStrategy;
import com.kamisarau.shopsimulation.service.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogStrategyChooserTest {

    @Test
    void testChooseShelf() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("shelf");

        assertTrue(serviceStrategy instanceof ShelfLogServiceStrategy);
    }

    @Test
    void testChooseProduct() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("product");

        assertTrue(serviceStrategy instanceof ProductLogServiceStrategy);
    }

    @Test
    void testChooseStorage() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("storage");

        assertTrue(serviceStrategy instanceof StorageLogServiceStrategy);
    }

    @Test
    void testChooseRandomiser() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("randomiser");

        assertTrue(serviceStrategy instanceof RandomiserLogServiceStrategy);
    }

    @Test
    void testChooseMerchandise() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("merchandise");

        assertTrue(serviceStrategy instanceof MerchandiseLogServiceStrategy);
    }

    @Test
    void testDefaultChoose() {
        LogServiceStrategy serviceStrategy;

        serviceStrategy = LogStrategyChooser.choose("all");

        assertTrue(serviceStrategy instanceof AllLogStrategy);
    }
}