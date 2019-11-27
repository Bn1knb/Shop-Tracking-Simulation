package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.service.Randomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/start")
public class StartEmulation {
    private static final Random RANDOM = new Random();
    private Randomiser randomiser;

    @Autowired
    public StartEmulation(Randomiser randomiser) {
        this.randomiser = randomiser;
    }

    @GetMapping
    public ResponseEntity start(@RequestParam(defaultValue = "10", required = false) int number) {
        Shelf shelf = new Shelf().setHeight(RANDOM.nextInt(100)).setWidth(RANDOM.nextInt(100));

        randomiser.populateStorageWithProducts();
        randomiser.doRandomOperations(number, shelf);
        return new ResponseEntity(HttpStatus.OK);
    }
}
