package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;


public class FinanceTest {

    @Test
    public void canAddEatingOutCostForDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2007, 1, 2);
        finance.addEatingOutCostForDate(10.0, date);
        assertEquals(finance.getEatingCostForDate(date), 10.0, tolerance);
    }

    @Test
    public void canAddProductCostForDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2014, 10, 24);
        finance.addProductsCostForDate(999.0, date);
        assertEquals(finance.getProductsCostForDate(date), 999.0, tolerance);
    }

    @Test
    public void canAddBuysCostForDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2099, 11, 9);
        finance.addBuyCostForDate(77.0, date);
        assertEquals(finance.getBuyCostForDate(date), 77.0, tolerance);
    }

    @Test
    public void canAddTransportCostForDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2001, 10, 9);
        finance.addTransportCost(645.0, date);
        assertEquals(finance.getTransportCost(date), 645.0, tolerance);
    }

    @Test
    public void canAddServicesCostForDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2031, 1, 9);
        finance.addServicesCost(45325.0, date);
        assertEquals(finance.getServicesCost(date), 45325.0, tolerance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddServicesCostForInvalidDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(2031, 1666, 9999);
        finance.addServicesCost(1.0, date);
    }


    @Test(expected = IllegalArgumentException.class)
    public void canNotAddEatingOutForInvalidDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(1945, 999, 90);
        finance.addEatingOutCostForDate(888.0, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddByusForInvalidDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(19999, 966699, 110);
        finance.addBuyCostForDate(555.0, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddTransportCostForInvalidDate() throws Exception {
        Finance finance = new Finance();
        GregorianCalendar date = new GregorianCalendar(11, 55, 50);
        finance.addTransportCost(1.0, date);
    }
    private double tolerance = 0.001;

}
