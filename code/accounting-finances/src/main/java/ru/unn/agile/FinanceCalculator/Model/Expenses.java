package ru.unn.agile.FinanceCalculator.Model;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public final class Expenses {
    public Expenses() {
        storage = new ExpensesStorage();
    }

    public Currency getCost(final Calendar calendarDate, final FinanceType type) {
        DayExpenses finance = storage.get(calendarDate);
        return finance.get(type);
    }

    public void addCost(final Currency cost,
                        final Calendar calendarDate,
                        final FinanceType type) throws IllegalArgumentException {
        checkDate(calendarDate);
        DayExpenses finance = storage.get(calendarDate);
        finance.add(type, cost);
        storage.put(calendarDate, finance);
    }

    private void checkDate(final Calendar calendarDate) throws IllegalArgumentException {
        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        Date inputDate = calendarDate.getTime();
        if (inputDate.after(today)) {
           throw new IllegalArgumentException("Date can't be after today");
        }
    }

    public enum FinanceType {
        EatingOut,
        Products,
        UnreasonableWaste,
        Transport,
        Services,
        Entertainment;

        public static Collection<Object[]> arrayOfTypes() {
            List<FinanceType> enumValuesList = Arrays.asList(FinanceType.values());
            List<Object[]> list = new ArrayList<>();
            for (FinanceType value: enumValuesList) {
                list.add(new Object[]{value});
            }
            return list;
        }
    }

    private ExpensesStorage storage;
}
