package ru.unn.agile.FinanceCalculator.Model;

import java.util.Objects;

public class Currency {
    Currency() {
        kopeck = 0;
    }

    Currency(final int theRuble, final int theKopeck) throws IllegalArgumentException {
        checkPrecondition(theKopeck, theRuble);
        kopeck = theKopeck + theRuble * NUMKOPECKINRUBLE;
    }

    public int getKopeck() {
        return kopeck - (kopeck / NUMKOPECKINRUBLE) * NUMKOPECKINRUBLE;
    }

    public int getRuble() {
        return kopeck / NUMKOPECKINRUBLE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Currency) {
            Currency currency = (Currency) o;
            return kopeck == currency.kopeck;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kopeck);
    }

    private void checkPrecondition(final int theKopeck,
                                   final int theRuble) throws IllegalArgumentException {
        if (theKopeck < 0) {
            throw new IllegalArgumentException("Kopeck must be non-negative!");
        }
        if (theRuble < 0) {
            throw new IllegalArgumentException("Ruble must be non-negative!");
        }
        long checkOverflow = (long) theKopeck * NUMKOPECKINRUBLE + theRuble;
        if (checkOverflow > Integer.MAX_VALUE || checkOverflow < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Overflow!");
        }
    }

    private static final int NUMKOPECKINRUBLE = 100;
    private int kopeck;
}
