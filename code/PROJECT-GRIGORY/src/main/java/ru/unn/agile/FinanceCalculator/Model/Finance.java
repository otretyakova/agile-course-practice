package ru.unn.agile.FinanceCalculator.Model;

import java.util.Calendar;
import java.util.Date;

public final class Finance {
    public Finance() {
        storage = new FinanceStorage();
    }

    public double get(final Calendar date, final FinanceType type) throws Exception {
        DayFinance finance = storage.get(date);
        return finance.get(type);
    }

    public void add(final double cost,
                    final Calendar date,
                    final FinanceType type) throws Exception {
        checkDate(date);
        DayFinance finance = storage.get(date);
        finance.add(type, cost);
        storage.put(date, finance);
    }
    public void checkDate(final Calendar date) throws Exception {
        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        Date inputDate = date.getTime();
        if (inputDate.after(today)) {
           throw new IllegalArgumentException("date can't be after today");
        }
    }
    private FinanceStorage storage;
}

