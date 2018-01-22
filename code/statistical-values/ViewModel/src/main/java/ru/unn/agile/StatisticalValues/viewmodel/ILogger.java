package ru.unn.agile.StatisticalValues.viewmodel;

import java.util.List;

public interface ILogger {
    void addLogText(String s);

    List<String> getLogText();
}
