package com.kamisarau.shopsimulation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoLogsFound extends RuntimeException {
    public NoLogsFound() {
        super();
    }

    public NoLogsFound(String message) {
        super(message);
    }
}
