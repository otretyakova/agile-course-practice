package ru.unn.agile.FinanceCalculator.Model;

import ru.unn.agile.FinanceCalculator.Model.Expenses.FinanceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ExpensesTest {
    @Parameterized.Parameters
   public static Collection<Object[]> data() {
       return FinanceType.arrayOfTypes();
   }
    public ExpensesTest(final FinanceType type) {
        myType = type;
    }

    @Test
    public void canAddCostsForValidDate() {
        Expenses expenses = new Expenses();
        Calendar date = new GregorianCalendar(2001, 1, 9);
        Currency cost = new Currency(45325, 55);
        expenses.addCost(cost, date, myType);
        assertEquals(expenses.getCost(date, myType), cost);
    }

    @Test
    public void canAddCostsMultipleTimesForOneValidDate() {
        Expenses expenses = new Expenses();
        Calendar date = new GregorianCalendar(2005, 2, 9);
        Currency firstCost = new Currency(45325, 55);
        expenses.addCost(firstCost, date, myType);
        date.setTimeInMillis(date.getTimeInMillis() + 6000);
        Currency secondCost = new Currency(20, 10);
        expenses.addCost(secondCost, date, myType);
        Currency resultCost = new Currency(45345, 65);
        assertEquals(expenses.getCost(date, myType), resultCost);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextMonth() throws IllegalArgumentException {
        Expenses expenses = new Expenses();
        Calendar date = getFutureDate(31);
        Currency cost = new Currency(999, 99);
        expenses.addCost(cost, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextWeek() throws IllegalArgumentException {
        Expenses expenses = new Expenses();
        Calendar date = getFutureDate(7);
        Currency cost = new Currency(888, 99);
        expenses.addCost(cost, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForTomorrowDate() throws IllegalArgumentException {
        Expenses expenses = new Expenses();
        Calendar date = getFutureDate(1);
        Currency cost = new Currency(87788, 99);
        expenses.addCost(cost, date, myType);
    }

    private Calendar getFutureDate(final int numDaysFromToday) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, numDaysFromToday * 24);
        return date;
    }

    private FinanceType myType;
}
