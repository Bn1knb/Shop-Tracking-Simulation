package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.model.Shelf;
import com.kamisarau.shopsimulation.service.Randomiser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Start simulation here")
public class StartEmulation {
    private static final Random RANDOM = new Random();
    private Randomiser randomiser;
    private Shelf shelf = new Shelf().setHeight(RANDOM.nextInt(100)).setWidth(RANDOM.nextInt(100));

    @Autowired
    public StartEmulation(Randomiser randomiser) {
        this.randomiser = randomiser;
    }

    @GetMapping
    public ResponseEntity start(
            @ApiParam(name = "number", value = "number of random operations (default is 10)")
            @RequestParam(defaultValue = "10", required = false) int number) {


        randomiser.populateStorageWithProducts();
        randomiser.doRandomOperations(number, shelf);
        return new ResponseEntity(HttpStatus.OK);
    }
}
