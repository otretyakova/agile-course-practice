package ru.unn.agile.FinanceCalculator.Model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FinanceStorage {
    public FinanceStorage() {
        myDaysFinances = new HashMap<Calendar, DayFinance>();
    }

    public DayFinance get(final Calendar date) {
        Calendar normalDate = computeKey(date);
        DayFinance finance = myDaysFinances.get(normalDate);
        if (finance != null) {
            return finance;
        } else {
            return new DayFinance();
        }
    }

    public void put(final Calendar date, final DayFinance finance) {
        Calendar normalDate = computeKey(date);
        myDaysFinances.put(normalDate, finance);
    }

    private Calendar computeKey(final Calendar date) {
        Calendar keyDate = date;
        keyDate.set(Calendar.MILLISECOND, 0);
        keyDate.set(Calendar.SECOND, 0);
        keyDate.set(Calendar.MINUTE, 0);
        keyDate.set(Calendar.HOUR, 0);
        return keyDate;
    }

    private Map<Calendar, DayFinance> myDaysFinances;
}
