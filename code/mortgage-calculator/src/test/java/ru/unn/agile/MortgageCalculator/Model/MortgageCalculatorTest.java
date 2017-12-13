package ru.unn.agile.MortgageCalculator.Model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static ru.unn.agile.MortgageCalculator.Model.MortgageCalculator.PERIODTYPE.month;
import static ru.unn.agile.MortgageCalculator.Model.MortgageCalculator.PERIODTYPE.year;

public class MortgageCalculatorTest {
    @Test
    public void canCalculateDifferentiatedMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, (float) 0.05);
        assertEquals(Arrays.asList(338, 336, 335), calc.differentiatedPayment(month));
    }

    @Test
    public void canCalculateAnnuityMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, (float) 0.05);
        assertEquals(336, calc.annuityPayment(month));
    }

    @Test
    public void canCalculateDifferentiatedMortgageWithYearPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(100000, 1, (float) 0.05);
        assertEquals(Arrays.asList(8750, 8715, 8681, 8646, 8611, 8576, 8542, 8507,
                8472, 8437, 8403, 8368), calc.differentiatedPayment(year));
    }

    @Test
    public void canCalculateAnnuityMortgageWithYearPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(100000, 1, (float) 0.05);
        assertEquals(8560, calc.annuityPayment(year));
    }

    @Test
    public void canGetPaymentForDifferentiatedMortgage() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, (float) 0.05);
        assertEquals(336, calc.getPayments("differentiated", 1, month));
    }

    @Test
    public void canReturnZeroWhenCalculateDifferentiatedMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, (float) 0.05);
        assertEquals(Arrays.asList(0, 0, 0), calc.differentiatedPayment(month));
    }

    @Test
    public void canReturnZeroWhenCalculateAnnuityMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, (float) 0.05);
        assertEquals(0, calc.annuityPayment(month));
    }

    @Test
    public void canGetPaymentForDifferentiatedMortgageWithZeroAmount() {
        MortgageCalculator calc = new MortgageCalculator(0, 3, (float) 0.05);
        assertEquals(0, calc.getPayments("differentiated", 1, month));
    }

    @Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithZeroPeriod() {
        MortgageCalculator calc = new MortgageCalculator(1000, 0, (float) 0.05);
        calc.differentiatedPayment(month);
        assertEquals("Period is zero", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithZeroPeriod() {
        MortgageCalculator calc = new MortgageCalculator(1000, 0, (float) 0.05);
        calc.annuityPayment(month);
        assertEquals("Period is zero", calc.getError());
    }

    /*@Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithUndefinedPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "abcd", (float) 0.05);
        calc.differentiatedPayment(abcd);
        assertEquals("Type of period is undefined", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithUndefinedPeriodType() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, "abcd", (float) 0.05);
        calc.annuityPayment();
        assertEquals("Type of period is undefined", calc.getError());
    }*/

    @Test
    public void canCatchErrorWhenCalculateDifferentiatedMortgageWithZeroRate() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, 0);
        calc.differentiatedPayment(month);
        assertEquals("Rate is zero", calc.getError());
    }

    @Test
    public void canCatchErrorWhenCalculateAnnuityMortgageWithZeroRate() {
        MortgageCalculator calc = new MortgageCalculator(1000, 12, 0);
        calc.annuityPayment(month);
        assertEquals("Rate is zero", calc.getError());
    }

    @Test
    public void canGetPaymentForDifferentiatedMortgageWithUndefinedTypeOfPayment() {
        MortgageCalculator calc = new MortgageCalculator(1000, 3, (float) 0.05);
        calc.getPayments("abcde", 1, month);
        assertEquals("Can't print payment of undefined type of payment", calc.getError());
    }
}
