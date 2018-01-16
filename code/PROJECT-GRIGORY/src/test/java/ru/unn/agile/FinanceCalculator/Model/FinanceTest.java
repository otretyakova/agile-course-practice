package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FinanceTest {

    @Parameterized.Parameters
    public static Collection<FinanceType> data() {
        return Arrays.asList(
            FinanceType.EatingOut, FinanceType.Products,
            FinanceType.UnreasonableWaste, FinanceType.Services,
            FinanceType.Transport, FinanceType.Entertainment);
    }

    public FinanceTest(final FinanceType type) {
        myType = type;
    }

    @Test
    public void canAddCostsForValidDate() throws Exception {
        Finance finance = new Finance();
        Calendar date = new GregorianCalendar(2001, 1, 9);
        finance.add(45325.0, date, myType);
        assertEquals(finance.get(date, myType), 45325.0, tolerance);
    }

    @Test
    public void canAddCostsMultipleTimesForOneValidDate() throws Exception {
        Finance finance = new Finance();
        Calendar date = new GregorianCalendar(2005, 2, 9);
        finance.add(10.9, date, myType);
        date.setTimeInMillis(date.getTimeInMillis() + 6000);
        finance.add(10.0, date, myType);
        assertEquals(finance.get(date, myType), 20.9, tolerance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextMonth() throws Exception {
        Finance finance = new Finance();
        Calendar date = getFutureDate(31);
        finance.add(999.0, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForNextWeek() throws Exception {
        Finance finance = new Finance();
        Calendar date = getFutureDate(7);
        finance.add(9889.99, date, myType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddCostForTomorrowDate() throws Exception {
        Finance finance = new Finance();
        Calendar date = getFutureDate(1);
        finance.add(10.0, date, myType);
    }

    private Calendar getFutureDate(final int numDaysFromToday) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, numDaysFromToday * 24);
        return date;
    }
    private FinanceType myType;
    private double tolerance = 0.005;

}

