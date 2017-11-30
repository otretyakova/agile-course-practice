package ru.unn.agile.MortgageCalculator.Model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MortgageCalculatorTest {
    @Test
    public void canCalculateDifferentiatedMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, "month", (float) 0.05);
        assertEquals(Arrays.asList(337, 336, 335), calc.differentiatedPayment());
    }

    @Test
    public void canCalculateAnnuityMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, "month", (float) 0.05);
        assertEquals(336, calc.annuityPayment());
    }

    @Test
    public void canCalculateDifferentiatedMortgageWithYearPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(100000, 1, "year", (float) 0.05);
        assertEquals(Arrays.asList(8750, 8715, 8680, 8646, 8611, 8576, 8541, 8507,
                8473, 8438, 8404, 8372), calc.differentiatedPayment());
    }

    @Test
    public void canCalculateAnnuityMortgageWithYearPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(100000, 1, "year", (float) 0.05);
        assertEquals(8560, calc.annuityPayment());
    }

    @Test
    public void canPrintPaymentForDifferentiatedMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, "month", (float) 0.05);
        assertEquals(336, calc.printPayments("differentiated", 1));
    }

    @Test
    public void canReturnZeroWhenCalculateDifferentiatedMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, "month", (float) 0.05);
        assertEquals(Arrays.asList(0, 0, 0), calc.differentiatedPayment());
    }

    @Test
    public void canReturnZeroWhenCalculateAnnuityMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, "month", (float) 0.05);
        assertEquals(0, calc.annuityPayment());
    }

    @Test
    public void canPrintPaymentForDifferentiatedMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, "month", (float) 0.05);
        assertEquals(0, calc.printPayments("differentiated", 1));
    }

    @Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithZeroPeriod() {
        MortgageCalculator calc = new MortgageCalculator(1000, 0, "month", (float) 0.05);
        calc.differentiatedPayment();
        assertEquals("Period is zero", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithZeroPeriod() {
        MortgageCalculator calc = new MortgageCalculator(1000, 0, "month", (float) 0.05);
        calc.annuityPayment();
        assertEquals("Period is zero", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithUndefinedPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "abcd", (float) 0.05);
        calc.differentiatedPayment();
        assertEquals("Type of period is undefined", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithUndefinedPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "abcd", (float) 0.05);
        calc.annuityPayment();
        assertEquals("Type of period is undefined", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithZeroRate() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "month", 0);
        calc.differentiatedPayment();
        assertEquals("Rate is zero", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithZeroRate() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "month", 0);
        calc.annuityPayment();
        assertEquals("Rate is zero", calc.getError());
    }

    @Test
    public void canPrintPaymentForDifferentiatedMortgageWithUndefinedTypeOfPayment() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, "month", (float) 0.05);
        calc.printPayments("abcde", 1);
        assertEquals("Can't print payment of undefined type of payment", calc.getError());
    }
}
