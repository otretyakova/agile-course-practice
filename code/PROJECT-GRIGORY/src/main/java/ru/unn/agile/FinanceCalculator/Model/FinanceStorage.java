package ru.unn.agile.FinanceCalculator.Model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FinanceStorage {
    public FinanceStorage() {
        myDaysFinances = new HashMap<Calendar, DayFinance>();
    }
    public DayFinance get(final Calendar date) {
        DayFinance finance = myDaysFinances.get(date);
        if (finance != null) {
            return finance;
        } else {
            return new DayFinance();
        }
    }

    public void put(final Calendar date, final DayFinance finance) {
        myDaysFinances.put(date, finance);
    }

    private Map<Calendar, DayFinance> myDaysFinances;
}
