package ru.unn.agile.FinanceCalculator.Model;

import java.util.Calendar;
import java.util.Date;

public final class Finance {
    public Finance() {
        storage = new FinanceStorage();
    }

    public double getCost(final Calendar date, final FinanceType type) {
        DayFinance finance = storage.get(date);
        return finance.get(type);
    }

    public void addCost(final double cost,
                        final Calendar date,
                        final FinanceType type) throws IllegalArgumentException {
        checkDate(date);
        DayFinance finance = storage.get(date);
        finance.add(type, cost);
        storage.put(date, finance);
    }

    private void checkDate(final Calendar date) throws IllegalArgumentException {
        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        Date inputDate = date.getTime();
        if (inputDate.after(today)) {
           throw new IllegalArgumentException("date can't be after today");
        }
    }

    private FinanceStorage storage;
}
