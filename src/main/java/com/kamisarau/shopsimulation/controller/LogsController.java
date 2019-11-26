package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.service.LogServiceStrategy;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.kamisarau.shopsimulation.util.LogStrategyChooser.choose;

@RestController("/logs")
public class LogsController {

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getLogs(@RequestParam(defaultValue = "all", required = false) String type,
                                            @RequestParam(required = false) Date date) {
        LogServiceStrategy logServiceStrategy = choose(type);
        ByteArrayResource resource = new ByteArrayResource(logServiceStrategy.getLogs(date));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
