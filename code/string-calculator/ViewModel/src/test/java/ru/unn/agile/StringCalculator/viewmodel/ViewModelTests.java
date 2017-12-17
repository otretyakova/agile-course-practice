package ru.unn.agile.StringCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ViewModelTests {
    private ViewModel viewModel;

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
        assertEquals("", viewModel.getInputString());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        viewModel.inputStringProperty().set("2,2");

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatWhenIncorrectInput() {
        viewModel.inputStringProperty().set("2,a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledWhenIncorrectInput() {
        viewModel.inputStringProperty().set("2,a");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void canReportBadFormatWhenNegativeNumberInInput() {
        viewModel.inputStringProperty().set("2,-2");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledWhenNegativeNumberInInput() {
        viewModel.inputStringProperty().set("2,-2");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void statusIsReadyWhenCorrectInput() {
        viewModel.inputStringProperty().set("2,2");

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsEnabledWhenCorrectInput() {
        viewModel.inputStringProperty().set("2,2");

        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void correctResultWhenCalculate() {
        viewModel.inputStringProperty().set("2,2");

        viewModel.calculate();

        assertEquals("4", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.inputStringProperty().set("2,2");

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }
}
