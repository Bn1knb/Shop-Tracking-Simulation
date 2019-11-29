package com.kamisarau.shopsimulation.controller;

import com.kamisarau.shopsimulation.service.LogServiceStrategy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.kamisarau.shopsimulation.util.LogStrategyChooser.choose;

@RestController
@RequestMapping("/logs")
@Api(value = "Get logs here")
public class LogsController {

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getLogs(
            @ApiParam(name = "type", value = "type of logs (eg. shelf, merchandise, storage, product) " +
                    "default: all")
            @RequestParam(defaultValue = "all", required = false) String type,
            @ApiParam(name = "date", value = "get logs by requested date")
            @RequestParam(required = false) Date date) {

        LogServiceStrategy logServiceStrategy = choose(type);
        ByteArrayResource resource = new ByteArrayResource(logServiceStrategy.getLogs(date));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
