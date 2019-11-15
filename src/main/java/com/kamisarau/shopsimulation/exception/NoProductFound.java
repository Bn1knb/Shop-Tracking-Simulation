package com.kamisarau.shopsimulation.exception;

public class NoProductFound extends RuntimeException{
    public NoProductFound(String message) {
        super(message);
    }

    public NoProductFound() {
        super();
    }
}
