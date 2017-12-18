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
    public void statusIsReadyWhenCorrectInput() {
        viewModel.setInputString("2,2");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsEnabledWhenCorrectInput() {
        viewModel.setInputString("2,2");
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canReportBadFormatWhenIncorrectInput() {
        viewModel.setInputString("2,a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledWhenIncorrectInput() {
        viewModel.setInputString("2,a");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void canReportBadFormatWhenNegativeNumberInInput() {
        viewModel.setInputString("2,-2");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledWhenNegativeNumberInInput() {
        viewModel.setInputString("2,-2");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void canReportBadFormatWhenDelimiterIsIncorrect() {
        viewModel.setInputString(";2,2,2");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledWhenDelimiterIsIncorrect() {
        viewModel.setInputString(";2,2,2");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateReturnsCorrectResultWhenCorrectInputString() {
        viewModel.setInputString("2,2");
        viewModel.calculate();
        assertEquals("4", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.setInputString("2,2");
        viewModel.calculate();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }
}
