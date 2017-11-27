package ru.unn.agile.RatioCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RatioTest {
    @Test
    public void canCreateRatioWithoutInitialValues() {
        Ratio ratio = new Ratio();
        assertEquals(ratio.getNumerator(), 0);
    }

    @Test
    public void canCreateHalfRatio() {
        Ratio ratio = new Ratio(1, 2);
        assertEquals(ratio.getDenominator(), 2);
    }
}
