package com.kamisarau.shopsimulation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoProductFound extends RuntimeException{
    public NoProductFound(String message) {
        super(message);
    }

    public NoProductFound() {
        super();
    }
}
