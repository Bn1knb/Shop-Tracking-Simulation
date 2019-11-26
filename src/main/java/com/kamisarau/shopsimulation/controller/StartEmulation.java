package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.service.Randomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/start")
public class StartEmulation {
    private Randomiser randomiser;

    @Autowired
    public StartEmulation(Randomiser randomiser) {
        this.randomiser = randomiser;
    }

    @GetMapping
    public ResponseEntity start(@RequestParam int number) { //todo thinks of storing shelf in cache
        randomiser.populateStorageWithProducts();
        randomiser.doRandomOperations(number);
        return new ResponseEntity(HttpStatus.OK);
    }
}
