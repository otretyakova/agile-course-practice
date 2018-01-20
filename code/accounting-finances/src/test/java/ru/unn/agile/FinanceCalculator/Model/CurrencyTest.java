package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CurrencyTest {
    @Test
    public void isConstructorCorrect() {
        Currency currency = new Currency(55, 99);
        assertEquals(currency.getKopeck(), 99);
        assertEquals(currency.getRuble(), 55);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateCurrencyWithNegativeKopeck() throws IllegalArgumentException {
        Currency currency = new Currency(699, -7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateCurrencyWithNegativeRuble() throws IllegalArgumentException {
        Currency currency = new Currency(-699, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateCurrencyWithInfRuble() throws IllegalArgumentException {
        Currency currency = new Currency(Integer.MAX_VALUE, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateCurrencyWithInfKopeck() throws IllegalArgumentException {
        Currency currency = new Currency(88, Integer.MAX_VALUE);
    }

    @Test
    public void areEqualCurrenciesEqual() {
        Currency currencyFirst = new Currency(55, 99);
        Currency currencySecond = new Currency(55, 99);
        assertTrue(currencyFirst.equals(currencySecond));
    }
}
