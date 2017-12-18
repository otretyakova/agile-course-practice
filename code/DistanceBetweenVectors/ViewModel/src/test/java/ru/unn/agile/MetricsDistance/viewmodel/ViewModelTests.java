package ru.unn.agile.MetricsDistance.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import ru.unn.agile.MetricsDistance.Model.MetricsDistance.Metric;

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
        assertEquals("", viewModel.x1Property().get());
        assertEquals("", viewModel.y1Property().get());
        assertEquals("", viewModel.x2Property().get());
        assertEquals("", viewModel.y2Property().get());
        assertEquals(Metric.Chebyshev, viewModel.metricProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.x1Property().set("a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.x1Property().set("1");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData("1", "2", "3", "4");
        viewModel.x1Property().set("trash");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.x1Property().set("1");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData("1", "2", "3", "4");
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canSetMinkowskiMetric() {
        viewModel.metricProperty().set(Metric.Minkowski);
        assertEquals(Metric.Minkowski, viewModel.getMetric());
    }

    @Test
    public void canSetChebyshevMetric() {
        viewModel.metricProperty().set(Metric.Chebyshev);
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevIsDefaultOperation() {
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevMetricHasCorrectResult() {
        setInputData("2", "2", "1", "3");
        viewModel.metricProperty().set(Metric.Chebyshev);
        viewModel.calculate();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData("1", "2", "3", "4");
        viewModel.calculate();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.x1Property().set("#selfie");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData("1", "2", "3", "4");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void minkowskiMetircHasCorrectResult() {
        setInputData("3", "3", "1", "3");
        viewModel.metricProperty().set(Metric.Minkowski);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void chebyshevMetricWithNegativeNumbersHasCorrectResult() {
        setInputData("1.2", "2.3", "-1.4", "-2.5");
        viewModel.metricProperty().set(Metric.Chebyshev);
        viewModel.calculate();
        assertEquals("4.8", viewModel.resultProperty().get());
    }
    private ViewModel viewModel;

    private void setInputData(final String x1, final String y1, final String x2, final String y2) {
        viewModel.x1Property().set(x1);
        viewModel.y1Property().set(y1);
        viewModel.x2Property().set(x2);
        viewModel.y2Property().set(y2);
    }

}
