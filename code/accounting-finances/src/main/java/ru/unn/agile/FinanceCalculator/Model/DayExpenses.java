package ru.unn.agile.FinanceCalculator.Model;

import ru.unn.agile.FinanceCalculator.Model.Expenses.FinanceType;
import java.util.HashMap;
import java.util.Map;

public class DayExpenses {
    public DayExpenses() {
        finances = new HashMap<FinanceType, Currency>();
        for (FinanceType type : FinanceType.values()) {
            finances.put(type, new Currency(0, 0));
        }
    }

    public DayExpenses(final Currency eatingOut, final Currency products,
                       final Currency unreasonableWaste, final Currency transport,
                       final Currency services, final Currency entertainment) {
        finances = new HashMap<FinanceType, Currency>();
        finances.put(FinanceType.EatingOut, eatingOut);
        finances.put(FinanceType.Products, products);
        finances.put(FinanceType.UnreasonableWaste, unreasonableWaste);
        finances.put(FinanceType.Transport, transport);
        finances.put(FinanceType.Services, services);
        finances.put(FinanceType.Entertainment, entertainment);
    }

    public void add(final FinanceType type, final Currency amount) throws IllegalArgumentException {
        Currency newAmount = new Currency(amount.getRuble() + finances.get(type).getRuble(),
                amount.getKopeck() + finances.get(type).getKopeck());
        finances.put(type, newAmount);
    }

    public Currency get(final FinanceType type) {
        return finances.get(type);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof DayExpenses) {
            DayExpenses finance = (DayExpenses) o;
            return finances.equals(finance.finances);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return finances.hashCode();
    }

    private Map<FinanceType, Currency> finances;
}
