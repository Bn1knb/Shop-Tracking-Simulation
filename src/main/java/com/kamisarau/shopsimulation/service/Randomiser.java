package com.kamisarau.shopsimulation.service;

import com.kamisarau.shopsimulation.model.Shelf;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public interface Randomiser {
    Random RANDOM = new Random();

    void populateStorageWithProducts();

    void doRandomOperations();

    void doRandomOperations(int n, Shelf shelf);
}
