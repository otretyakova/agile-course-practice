package ru.unn.agile.BookSale.ViewModel.legacy;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
