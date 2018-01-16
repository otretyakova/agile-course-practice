package ru.unn.agile.FinanceCalculator.Model;

import java.util.HashMap;
import java.util.Map;

public class DayFinance {
    public DayFinance() {
        finances = new HashMap<FinanceType, Double>();
        for (FinanceType type : FinanceType.values()) {
            finances.put(type, 0.);
        }
    }

    public DayFinance(final double eatingOut, final double products,
                      final double unreasonableWaste, final double transport,
                      final double services, final double entertainment) {
        finances = new HashMap<FinanceType, Double>();
        finances.put(FinanceType.EatingOut, eatingOut);
        finances.put(FinanceType.Products, products);
        finances.put(FinanceType.UnreasonableWaste, unreasonableWaste);
        finances.put(FinanceType.Transport, transport);
        finances.put(FinanceType.Services, services);
        finances.put(FinanceType.Entertainment, entertainment);
    }

    public void add(final FinanceType type, final double amount) throws IllegalArgumentException {
        checkPrecondition(amount);
        double newAmount = amount + finances.get(type);
        checkPostcondition(newAmount);
        finances.put(type, newAmount);

    }

    public double get(final FinanceType type) {
        return finances.get(type);
    }

    private void checkPrecondition(final double amount) throws IllegalArgumentException {
        if (Double.isNaN(amount)) {
            throw new IllegalArgumentException("Amount can not be NaN!");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be non-negative!");
        }
        if (amount == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Amount must be finite");
        }
    }

    private void checkPostcondition(final double newAmount) throws IllegalArgumentException {
        if (newAmount == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Overflow");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof DayFinance) {

            DayFinance finance = (DayFinance) o;

            return finances.equals(finance.finances);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return finances.hashCode();
    }

    private Map<FinanceType, Double> finances;
}
