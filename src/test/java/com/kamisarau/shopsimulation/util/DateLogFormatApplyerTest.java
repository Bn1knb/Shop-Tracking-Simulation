package com.kamisarau.shopsimulation.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kamisarau.shopsimulation.util.DateLogFormatApplyer.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DateLogFormatApplyerTest {

    @Test
    void testApplySame() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2001-08-31";
        Date testDate = sdf.parse(dateInString);
        String expected = "-2001-08-31";

        String actual = apply(testDate);

        assertEquals(expected, actual);
    }

    @Test
    void testApplyNotSame() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "31-08-1982";
        Date testDate = sdf.parse(dateInString);
        String expected = "10-18-9982";

        String actual = apply(testDate);

        assertNotEquals(expected, actual);
    }
}