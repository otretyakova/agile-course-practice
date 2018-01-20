package ru.unn.agile.FinanceCalculator.Model;

import ru.unn.agile.FinanceCalculator.Model.Expenses.FinanceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

public class DayExpensesTest {

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
                DayExpenses input = new DayExpenses();
                Currency currency = new Currency(0, 0);
                assertEquals(input.get(myType), currency);
            }

            @Test
            public void canAddPositiveAmount() {
                DayExpenses input = new DayExpenses();
                Currency currency = new Currency(60, 99);
                input.add(myType, currency);
                assertEquals(input.get(myType), currency);
            }

            @Test
            public void canAddMultiplePositiveAmount() throws Exception {
                DayExpenses input = new DayExpenses();
                Currency firstCurrency = new Currency(77, 55);
                Currency secondCurrency = new Currency(6, 99);
                Currency thirdCurrency = new Currency(699, 7);
                input.add(myType, firstCurrency);
                input.add(myType, secondCurrency);
                input.add(myType, thirdCurrency);
                Currency expectedCurrency = new Currency(783, 61);
                assertEquals(input.get(myType), expectedCurrency);
            }

            @Test(expected = IllegalArgumentException.class)
            public void canNotAddNegativeAmount() throws Exception {
                DayExpenses input = new DayExpenses();
                Currency thirdCurrency = new Currency(-699, -7);
                input.add(myType, thirdCurrency);
            }

            private FinanceType myType;
        }

        @Test
        public void canAddMultipleTypesPositiveAmounts() throws Exception {
            DayExpenses input = new DayExpenses();
            Currency nullCurrency = new Currency();

            Currency unreasonableWasteCurrency = new Currency(99, 7);
            input.add(FinanceType.UnreasonableWaste, unreasonableWasteCurrency);

            Currency eatingOutCurrency = new Currency(77, 88);
            input.add(FinanceType.EatingOut, eatingOutCurrency);

            Currency productsCurrency = new Currency(9999, 88);
            input.add(FinanceType.Products, productsCurrency);

            DayExpenses actual = new DayExpenses(eatingOutCurrency,
                    productsCurrency, unreasonableWasteCurrency,
                    nullCurrency, nullCurrency, nullCurrency);

            assertEquals(input, actual);
        }
}
