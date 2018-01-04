package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class DayFinanceTest {

    @Test
    public void canAddPositiveEatingOutCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEatingOutCost(6.0);
        assertEquals(input.getEatingCost(), 6.0, tolerance);
    }

    @Test
    public void canAddPositiveProductsCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addProductsCost(77.0);
        assertEquals(input.getProductsCost(), 77.0, tolerance);
    }

    @Test
    public void canAddPositiveBuysCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addBuyCost(55.0);
        assertEquals(input.getBuyCost(), 55.0, tolerance);
    }

    @Test
    public void canAddPositiveTransportCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addTransportCost(27.0);
        assertEquals(input.getTransportCost(), 27.0, tolerance);
    }

    @Test
    public void canAddPositiveServicesCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addServicesCost(99.0);
        assertEquals(input.getServicesCost(), 99.0, tolerance);
    }

    @Test
    public void canAddPositiveEntertamentCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEntertainmentCost(88.0);
        assertEquals(input.getEntertainmentCost(), 88.0, tolerance);
    }

    @Test
    public void addCostCorrectly() throws Exception {
        DayFinance input = new DayFinance();
        input.addEntertainmentCost(88.0);
        input.addEatingOutCost(456.0);
        input.addProductsCost(999.0);
        input.addBuyCost(77.0);
        input.addTransportCost(67.0);
        input.addServicesCost(8888.0);
        assertEquals(input.getEatingCost(), 456.0, tolerance);
        assertEquals(input.getProductsCost(), 999.0, tolerance);
        assertEquals(input.getProductsCost(), 999.0, tolerance);
        assertEquals(input.getBuyCost(), 77.0,  tolerance);
        assertEquals(input.getTransportCost(), 67.0, tolerance);
        assertEquals(input.getEntertainmentCost(), 88.0, tolerance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNegativeEatingOutCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEatingOutCost(-77.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNegativeProductsCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addProductsCost(-11111.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNegativeTransportCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addTransportCost(-999.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNegativeServicesCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addServicesCost(-334.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNegativeEntertaimentCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEntertainmentCost(-334.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanEatingOutCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEatingOutCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanProductsCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addProductsCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanBuysCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addBuyCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanTransportCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addTransportCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanServicesCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addServicesCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddNanEntertamentCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEntertainmentCost(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfEatingOutCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEatingOutCost(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfProductsCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addProductsCost(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfBuysCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addBuyCost(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfTransportCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addTransportCost(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfServicesCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addServicesCost(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canAddInfEntertamentCost() throws Exception {
        DayFinance input = new DayFinance();
        input.addEntertainmentCost(Double.NaN);
    }
    private double tolerance = 0.001;

}
