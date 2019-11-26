package com.kamisarau.shopsimulation.service.impl;

import com.google.common.io.ByteStreams;
import com.kamisarau.shopsimulation.exception.NoLogsFound;
import com.kamisarau.shopsimulation.service.LogServiceStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static com.kamisarau.shopsimulation.util.DateLogFormatApplyer.apply;

@Slf4j
public class RandomiserLogServiceStrategy implements LogServiceStrategy {
    public static final String RANDOMISER_LOGS_FILE_LOCATION = "C:\\\\logs\\randomiser";
    public static final String LOG_FORMAT = ".log";

    @Override
    public byte[] getLogs(Date date) {
        try {
            InputStream in = new FileInputStream(RANDOMISER_LOGS_FILE_LOCATION + apply(date) + LOG_FORMAT);
            return ByteStreams.toByteArray(in);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new NoLogsFound();
        }
    }
}
