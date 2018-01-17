package ru.unn.agile.FinanceCalculator.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

public class DayFinanceTest {

    @RunWith(Parameterized.class)
    public static class AddGetTest {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return FinanceType.arrayOfTypes();
        }

        public AddGetTest(final FinanceType type) {
                myType = type;
            }

            @Test
            public void isConstructorCorrect() {
                DayFinance input = new DayFinance();
                assertEquals(input.get(myType), 0.0, tolerance);
            }

            @Test
            public void canAddPositiveAmount() {
                DayFinance input = new DayFinance();
                input.add(myType, 6.0);
                assertEquals(input.get(myType), 6.0, tolerance);
            }

            @Test
            public void canAddMultiplePositiveAmount() {
                DayFinance input = new DayFinance();
                input.add(myType, 6.1);
                input.add(myType, 1.99);
                input.add(myType, 100.0);
                assertEquals(input.get(myType), 108.09, tolerance);
            }

            @Test(expected = IllegalArgumentException.class)
            public void canNotAddNegativeAmount() throws IllegalArgumentException {
                DayFinance input = new DayFinance();
                input.add(myType, -77.0);
            }

            @Test(expected = IllegalArgumentException.class)
            public void canNotAddNanAmount() throws IllegalArgumentException {
                DayFinance input = new DayFinance();
                input.add(myType, Double.NaN);
            }

            @Test(expected = IllegalArgumentException.class)
            public void canNotAddInfiniteAmount() throws IllegalArgumentException {
                DayFinance input = new DayFinance();
                input.add(myType, Double.POSITIVE_INFINITY);
            }

            private FinanceType myType;
            private final double tolerance = 0.005;
        }

        @Test
        public void canAddMultipleTypesPositiveAmounts() {
            DayFinance input = new DayFinance();
            input.add(FinanceType.UnreasonableWaste, 777.1);
            input.add(FinanceType.EatingOut, 1.99);
            input.add(FinanceType.EatingOut, 55.0);
            input.add(FinanceType.Products, 100.0);
            DayFinance actual = new DayFinance(56.99, 100.0, 777.1, 0.0, 0.0, 0.0);
            assertEquals(input, actual);
        }
}
