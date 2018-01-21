package ru.unn.agile.MetricsDistance.Infrastructure;

public final class AbstractLogger {

    public static String prepareMassageForLog(final String message, final String logTag) {
        String resultMessage = CurrentDate.getCurrentDate(DATE_FORMAT_NOW) + " > "
                + message + System.lineSeparator();
        if (!logTag.isEmpty()) {
            resultMessage = logTag + ": " + resultMessage;
        }
        return resultMessage;
    }

    private AbstractLogger() {
    }

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
}
