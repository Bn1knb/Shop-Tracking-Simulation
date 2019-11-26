package com.kamisarau.shopsimulation.service;

import com.google.common.io.ByteStreams;
import com.kamisarau.shopsimulation.exception.NoLogsFound;
import com.kamisarau.shopsimulation.service.impl.StorageLogServiceStrategy;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageLogServiceStrategyTest {
    @Test
    void testGetExistingLogs() throws IOException {
        InputStream inputStream = new FileInputStream("C:\\\\logs\\storage.log");

        assertArrayEquals(ByteStreams.toByteArray(inputStream), new StorageLogServiceStrategy().getLogs(null));
    }

    @Test
    void testGetNonExistingLogs() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "31-08-1982";
        Date date = sdf.parse(dateInString);

        assertThrows(NoLogsFound.class, () -> new StorageLogServiceStrategy().getLogs(date));
    }
}