package com.kamisarau.shopsimulation.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLogFormatApplyer {

    private DateLogFormatApplyer() {
        throw new IllegalStateException("Utility class has been instantiated");
    }

    public static String apply(Date date) {
        String inflectionalEnding;

        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inflectionalEnding = "-" + dateFormat.format(date);
        } else {
            inflectionalEnding = "";
        }

        return inflectionalEnding;
    }
}
