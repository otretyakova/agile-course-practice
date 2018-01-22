package ru.unn.agile.StringCalculator.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class AbstractLogger {
    public static String prepareLogMessage(final String logTag, final String message) {
        String resultMessage = now() + " > ";
        if (!logTag.isEmpty()) {
            resultMessage = resultMessage + logTag + ": "
                    + message + System.lineSeparator();
        } else {
            resultMessage = resultMessage + message + System.lineSeparator();
        }

        return resultMessage;
    }

    private AbstractLogger() {
    }

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }
}
