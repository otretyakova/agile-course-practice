package ru.unn.agile.StatisticalValues.infrastructure;

import java.util.List;

import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

public abstract class AbstractLogger implements ILogger {

    public void addLogText(final String s) {
        this.addLogText(s, "");
    }
    public abstract void addLogText(String s, String logTag);
    public abstract List<String> getLogText();

    protected String prepareLogMessage(final String message, final String logTag) {
        String logMessage = CurrentTime.getCurrentTime(DATE_FORMAT_NOW) + " > "
                + message + System.lineSeparator();
        if (!logTag.isEmpty()) {
            logMessage = logTag + ": " + logMessage;
        }
        return logMessage;
    }

    protected static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
}
