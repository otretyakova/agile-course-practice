package ru.unn.agile.MetricsDistance.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.MetricsDistance.Model.MetricsDistance.Metric;

public class ViewModelTests {
    private ViewModel viewModel;

    private void setInputData() {
        viewModel.x1Property().set("1");
        viewModel.y1Property().set("2");
        viewModel.x2Property().set("3");
        viewModel.y2Property().set("4");
    }

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
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.x1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.x1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.x1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.x1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetMinkowskiMetric() {
        viewModel.metricProperty().set(Metric.Minkowski);
        assertEquals(Metric.Minkowski, viewModel.metricProperty().get());
    }

    @Test
    public void canSetChebyshevMetric() {
        viewModel.metricProperty().set(Metric.Chebyshev);
        assertEquals(Metric.Chebyshev, viewModel.metricProperty().get());
    }

    @Test
    public void chebyshevIsDefaultOperation() {
        assertEquals(Metric.Chebyshev, viewModel.metricProperty().get());
    }

    @Test
    public void chebyshevMetricHasCorrectResult() {
        viewModel.x1Property().set("2");
        viewModel.y1Property().set("2");
        viewModel.x2Property().set("1");
        viewModel.y2Property().set("3");
        viewModel.metricProperty().set(Metric.Chebyshev);

        viewModel.calculate();

        assertEquals("1.0", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.x1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void minkowskiMetircHasCorrectResult() {
        viewModel.x1Property().set("3");
        viewModel.y1Property().set("3");
        viewModel.x2Property().set("1");
        viewModel.y2Property().set("3");
        viewModel.metricProperty().set(Metric.Minkowski);

        viewModel.calculate();

        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void chebyshevMetricWithNegativeNumbersHasCorrectResult() {
        viewModel.x1Property().set("1.2");
        viewModel.y1Property().set("2.3");
        viewModel.x2Property().set("-1.4");
        viewModel.y2Property().set("-2.5");
        viewModel.metricProperty().set(Metric.Chebyshev);

        viewModel.calculate();

        assertEquals("4.8", viewModel.resultProperty().get());
    }
}
