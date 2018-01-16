package ru.unn.agile.FinanceCalculator.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum FinanceType {
    EatingOut,
    Products,
    UnreasonableWaste,
    Transport,
    Services,
    Entertainment;

    public static Collection<Object[]> arrayOfTypes() {
        List<FinanceType> enumValuesList = Arrays.asList(FinanceType.values());
        List<Object[]> list = new ArrayList<>();
        for (FinanceType value: enumValuesList) {
            list.add(new Object[]{value});
        }
        return list;
    }
}
