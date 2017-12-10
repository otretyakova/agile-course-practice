package ru.unn.agile.StatisticalValues.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class ViewModelTests {
    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("Statistic", viewModel.statisticProperty().get());

        assertEquals("0", viewModel.orderProperty().get());
        assertFalse(viewModel.orderVisibilityProperty().get());

        assertFalse(viewModel.isBiasedProperty().get());
        assertFalse(viewModel.isBiasedVisibilityProperty().get());

        assertEquals("0.0, 0.0", viewModel.valuesProperty().get());
        assertFalse(viewModel.valuesVisibilityProperty().get());

        assertEquals("", viewModel.resultProperty().get());
        assertFalse(viewModel.resultVisibilityProperty().get());

        assertFalse(viewModel.calculateVisibilityProperty().get());

        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitWhenStatisticHasNotBeenChosen() {
        viewModel.calculate();
        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void fieldsAreInvisibleWhenStatisticHasNotBeenChosen() {
        viewModel.calculate();
        assertFalse(viewModel.orderVisibilityProperty().get());
        assertFalse(viewModel.isBiasedVisibilityProperty().get());
        assertFalse(viewModel.valuesVisibilityProperty().get());
        assertFalse(viewModel.calculateVisibilityProperty().get());
        assertFalse(viewModel.resultVisibilityProperty().get());
    }

    @Test
    public void calculationIsInvisibleByUndefinedStatistic() {
        viewModel.statisticProperty().set("Undefined");

        assertFalse(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void calculationIsInvisibleByInvalidOrder() {
        viewModel.statisticProperty().set("Moment");

        viewModel.orderProperty().set("Invalid");

        assertFalse(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void calculationIsInvisibleByInvalidValues() {
        viewModel.statisticProperty().set("Mean");

        viewModel.valuesProperty().set("Invalid");

        assertFalse(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void calculationIsVisibleBySettingStatistic() {
        viewModel.statisticProperty().set("Mean");

        assertTrue(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void orderIsVisibleBySettingStatisticUsingOrder() {
        viewModel.statisticProperty().set("Moment");

        assertTrue(viewModel.orderVisibilityProperty().get());
    }

    @Test
    public void orderIsInvisibleBySettingStatisticWithoutOrder() {
        viewModel.statisticProperty().set("Std");

        assertFalse(viewModel.orderVisibilityProperty().get());
    }

    @Test
    public void orderIsHiddenBySettingStatisticWithoutOrder() {
        viewModel.statisticProperty().set("Moment");

        viewModel.statisticProperty().set("Mean");

        assertFalse(viewModel.orderVisibilityProperty().get());
    }

    @Test
    public void orderIsShownBySettingStatisticUsingOrder() {
        viewModel.statisticProperty().set("Mean");

        viewModel.statisticProperty().set("Moment");

        assertTrue(viewModel.orderVisibilityProperty().get());
    }

    @Test
    public void biasIsVisibleBySettingStatisticUsingBias() {
        viewModel.statisticProperty().set("Variance");

        assertTrue(viewModel.isBiasedVisibilityProperty().get());
    }

    @Test
    public void biasIsInvisibleBySettingStatisticWithoutBias() {
        viewModel.statisticProperty().set("Mean");

        assertFalse(viewModel.isBiasedVisibilityProperty().get());
    }

    @Test
    public void biasIsHiddenBySettingStatisticWithoutBias() {
        viewModel.statisticProperty().set("Variance");

        viewModel.statisticProperty().set("Mean");

        assertFalse(viewModel.isBiasedVisibilityProperty().get());
    }

    @Test
    public void biasIsShownBySettingStatisticUsingBias() {
        viewModel.statisticProperty().set("Mean");

        viewModel.statisticProperty().set("Variance");

        assertTrue(viewModel.isBiasedVisibilityProperty().get());
    }

    @Test
    public void resultIsInvisibleBeforeCalculate() {
        viewModel.statisticProperty().set("Mean");

        assertFalse(viewModel.resultVisibilityProperty().get());
    }

    @Test
    public void resultIsVisibleAfterCalculate() {
        viewModel.statisticProperty().set("Mean");

        viewModel.calculate();

        assertTrue(viewModel.resultVisibilityProperty().get());
    }

    @Test
    public void resultIsHiddenByInvalidInput() {
        viewModel.statisticProperty().set("Moment");
        viewModel.calculate();

        viewModel.orderProperty().set("");

        assertFalse(viewModel.resultVisibilityProperty().get());
    }

    @Test
    public void statusIsWaitByEmptyInput() {
        viewModel.statisticProperty().set("Mean");
        viewModel.calculate();

        viewModel.valuesProperty().set("");

        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyBySettingStatistic() {
        viewModel.statisticProperty().set("Mean");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsSuccessByCalculation() {
        viewModel.statisticProperty().set("Mean");

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateIsHiddenByNotNumbersInValues() {
        viewModel.statisticProperty().set("Mean");

        viewModel.valuesProperty().set("0.0,;");

        assertFalse(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void calculateIsHiddenByRealNumbersInValuesForMode() {
        viewModel.statisticProperty().set("Mode");

        viewModel.valuesProperty().set("0.0, 1.0");

        assertFalse(viewModel.calculateVisibilityProperty().get());
    }

    @Test
    public void resultIsHiddenByRealNumbersInValuesForMode() {
        viewModel.statisticProperty().set("Mode");

        viewModel.valuesProperty().set("0.0, 1.0");

        assertFalse(viewModel.resultVisibilityProperty().get());
    }

    @Test
    public void resultHasSeveralNumbersForMode() {
        viewModel.statisticProperty().set("Mode");
        viewModel.valuesProperty().set("1, 2, 3, 4, 5, 6");

        viewModel.calculate();

        String result = viewModel.resultProperty().get();
        assertTrue(0 < getNumberOfOccurrences(result, ','));
    }


    @Test
    public void meanIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Mean");
        viewModel.valuesProperty().set("1.0, 2.0, 3.0");

        viewModel.calculate();

        assertEquals("2.0", viewModel.resultProperty().get());
    }


    @Test
    public void varianceIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Variance");
        viewModel.valuesProperty().set("0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0");
        viewModel.isBiasedProperty().set(true);

        viewModel.calculate();

        assertEquals("8.25", viewModel.resultProperty().get());
    }

    @Test
    public void unbiasedVarianceIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Variance");
        viewModel.valuesProperty().set("0.0, 1.0, 2.0, 3.0, 4.0");

        viewModel.calculate();

        assertEquals("2.5", viewModel.resultProperty().get());
    }

    @Test
    public void modeIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Mode");
        viewModel.valuesProperty().set("4, 5, 3, 2, 1, 1");

        viewModel.calculate();

        assertEquals("1", viewModel.resultProperty().get());
    }

    @Test
    public void modeForBimodalSampleIsCalculatedCorrect() {
        viewModel.statisticProperty().set("Mode");
        viewModel.valuesProperty().set("3, 2, 2, 1, 4, 5, 4, 10");

        viewModel.calculate();

        assertEquals("2, 4", viewModel.resultProperty().get());
    }

    @Test
    public void momentForPositiveOrderIsCalculatedCorrect() {
        viewModel.statisticProperty().set("Moment");
        viewModel.valuesProperty().set("2.0, 1.0, 3.0, 4.0, 2.5");
        viewModel.orderProperty().set("5");

        viewModel.calculate();

        assertEquals("279.53125", viewModel.resultProperty().get());
    }


    @Test
    public void centralMomentForPositiveOrderIsCalculatedCorrect() {
        viewModel.statisticProperty().set("Central moment");
        viewModel.valuesProperty().set("2.0, 1.0, 3.0, 4.0, 4.0");
        viewModel.orderProperty().set("5");

        viewModel.calculate();

        assertEquals(-3.5616, Double.valueOf(viewModel.resultProperty().get()), 10e-6);
    }


    @Test
    public void biasedStandardDeviationIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Std");
        viewModel.valuesProperty().set("2.0, 4.0, 4.0, 5.0, 4.0, 5.0, 7.0, 9.0");
        viewModel.isBiasedProperty().set(true);

        viewModel.calculate();

        assertEquals(2.0, Double.valueOf(viewModel.resultProperty().get()), 10e-6);
    }

    @Test
    public void unBiasedStandardDeviationIsCalculatedCorrectly() {
        viewModel.statisticProperty().set("Std");
        viewModel.valuesProperty().set("2.0, 4.0, 4.0, 5.0, 4.0, 5.0, 7.0, 9.0");
        viewModel.isBiasedProperty().set(false);

        viewModel.calculate();

        assertEquals(2.1380899, Double.valueOf(viewModel.resultProperty().get()), 10e-6);
    }

    private int getNumberOfOccurrences(final String src, final char target) {
        int numberOfOccurrences = 0;
        for (int position = 0; position < src.length(); position++) {
            if (src.charAt(position) == target) {
                numberOfOccurrences++;
            }
        }

        return numberOfOccurrences;
    }

    private ViewModel viewModel;
}
