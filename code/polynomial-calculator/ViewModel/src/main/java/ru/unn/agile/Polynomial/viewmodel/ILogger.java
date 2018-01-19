package ru.unn.agile.Polynomial.viewmodel;

import java.util.List;

public interface ILogger {
    void addInfo(String str);

    List<String> getLog();
}
