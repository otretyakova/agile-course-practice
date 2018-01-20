package ru.unn.agile.FinanceCalculator.Model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ExpensesStorage {
    public ExpensesStorage() {
        myDaysFinances = new HashMap<Calendar, DayExpenses>();
    }

    public DayExpenses get(final Calendar calendarDate) {
        Calendar normalDate = computeKey(calendarDate);
        DayExpenses finance = myDaysFinances.get(normalDate);
        if (finance != null) {
            return finance;
        } else {
            return new DayExpenses();
        }
    }

    public void put(final Calendar calendarDate, final DayExpenses finance) {
        Calendar normalDate = computeKey(calendarDate);
        myDaysFinances.put(normalDate, finance);
    }

    private Calendar computeKey(final Calendar calendarDate) {
        Calendar keyDate = calendarDate;
        keyDate.set(Calendar.MILLISECOND, 0);
        keyDate.set(Calendar.SECOND, 0);
        keyDate.set(Calendar.MINUTE, 0);
        keyDate.set(Calendar.HOUR, 0);
        return keyDate;
    }

    private Map<Calendar, DayExpenses> myDaysFinances;
}
