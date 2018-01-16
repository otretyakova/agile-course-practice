package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FinanceTest {
    @Parameterized.Parameters
   public static Collection<Object[]> data() {
       return FinanceType.arrayOfTypes();
   }

    public FinanceTest(final FinanceType type) {
        myType = type;
    }

    @Test
    public void canAddCostsForValidDate() {
        Finance finance = new Finance();
        Calendar date = new GregorianCalendar(2001, 1, 9);
        finance.addCost(45325.0, date, myType);
        assertEquals(finance.getCost(date, myType), 45325.0, tolerance);
    }

    @Test
    public void canAddCostsMultipleTimesForOneValidDate() {
        Finance finance = new Finance();
        Calendar date = new GregorianCalendar(2005, 2, 9);
        finance.addCost(10.9, date, myType);
        date.setTimeInMillis(date.getTimeInMillis() + 6000);
        finance.addCost(10.0, date, myType);
        assertEquals(finance.getCost(date, myType), 20.9, tolerance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextMonth() throws IllegalArgumentException {
        Finance finance = new Finance();
        Calendar date = getFutureDate(31);
        finance.addCost(999.0, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextWeek() throws IllegalArgumentException {
        Finance finance = new Finance();
        Calendar date = getFutureDate(7);
        finance.addCost(9889.99, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForTomorrowDate() throws IllegalArgumentException {
        Finance finance = new Finance();
        Calendar date = getFutureDate(1);
        finance.addCost(10.0, date, myType);
    }

    private Calendar getFutureDate(final int numDaysFromToday) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, numDaysFromToday * 24);
        return date;
    }

    private FinanceType myType;
    private double tolerance = 0.005;
}
