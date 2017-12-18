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
        assertEquals("", viewModel.getX1());
        assertEquals("", viewModel.getY1());
        assertEquals("", viewModel.getX2());
        assertEquals("", viewModel.getY2());
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
        assertEquals("", viewModel.getResult());
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
        viewModel.setX1("trash");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.setX1("1");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData("1", "2", "3", "4");
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canSetMinkowskiL1Metric() {
        viewModel.setMetric(Metric.MinkowskiL1);
        assertEquals(Metric.MinkowskiL1, viewModel.getMetric());
    }

    @Test
    public void canSetMinkowskiL2Metric() {
        viewModel.setMetric(Metric.MinkowskiL2);
        assertEquals(Metric.MinkowskiL2, viewModel.getMetric());
    }

    @Test
    public void canSetMinkowskiL3Metric() {
        viewModel.setMetric(Metric.MinkowskiL3);
        assertEquals(Metric.MinkowskiL3, viewModel.getMetric());
    }

    @Test
    public void canSetChebyshevMetric() {
        viewModel.setMetric(Metric.Chebyshev);
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevIsDefaultOperation() {
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevMetricHasCorrectResult() {
        setInputData("2", "2", "1", "3");
        viewModel.setMetric(Metric.Chebyshev);
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
        viewModel.setX1("#selfie");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData("1", "2", "3", "4");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void minkowskiL1MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3");
        viewModel.setMetric(Metric.MinkowskiL1);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void minkowskiL2MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3");
        viewModel.setMetric(Metric.MinkowskiL2);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void minkowskiL3MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3");
        viewModel.setMetric(Metric.MinkowskiL3);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void chebyshevMetricWithNegativeNumbersHasCorrectResult() {
        setInputData("1.2", "2.3", "-1.4", "-2.5");
        viewModel.setMetric(Metric.Chebyshev);
        viewModel.calculate();
        assertEquals("4.8", viewModel.resultProperty().get());
    }
    private ViewModel viewModel;

    private void setInputData(final String x1, final String y1, final String x2, final String y2) {
        viewModel.setX1(x1);
        viewModel.setY1(y1);
        viewModel.setX2(x2);
        viewModel.setY2(y2);
    }

}
