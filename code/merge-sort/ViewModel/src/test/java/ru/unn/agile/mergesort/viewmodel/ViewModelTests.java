package ru.unn.agile.mergesort.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertEquals("", getInput());
        assertEquals("", getOutput());
        assertEquals(Status.WAITING.toString(), getStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyInput() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), getStatus());
    }

    @Test
    public void statusIsWaitingWithInputWithSpacesOnly() {
        setInput("   ");

        assertEquals(Status.WAITING.toString(), getStatus());
    }

    @Test
    public void statusIsReadyWhenInputIsFilledByIntegers() {
        setInput("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), getStatus());
    }

    @Test
    public void statusIsWaitingAfterInputErase() {
        setInput("15 4 42 8 16 23");
        setInput("");

        assertEquals(Status.WAITING.toString(), getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputHasWrongFormat() {
        setInput("How this program works???");

        assertEquals(Status.BAD_FORMAT.toString(), getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputHasNotANumberWord() {
        setInput("15 4 42 Oops 8 16 23");

        assertEquals(Status.BAD_FORMAT.toString(), getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputHasTooBigNumbers() {
        setInput("15 4 42 853418926487349812743 8 16 23");

        assertEquals(Status.BAD_FORMAT.toString(), getStatus());
    }

    @Test
    public void statusIsReadyAfterBadFormatInputIsFixed() {
        setInput("How this program works???");
        setInput("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), getStatus());
    }

    @Test
    public void statusIsSuccessAfterCalculation() {
        setInput("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), getStatus());
    }

    @Test
    public void sortButtonIsDisabledInitially() {
        assertTrue(sortIsDisable());
    }

    @Test
    public void sortButtonIsDisabledWhenFormatIsBad() {
        setInput("15 4 42 Oops 8 16 23");

        assertTrue(sortIsDisable());
    }

    @Test
    public void sortButtonIsEnabledWithCorrectInput() {
        setInput("15 4 42 8 16 23");

        assertFalse(sortIsDisable());
    }

    @Test
    public void outputIsCorrectAfterSortCorrectInput() {
        setInput("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", getOutput());
    }

    @Test
    public void outputIsCorrectWithAbnormalSpacePlacingInInput() {
        setInput("  15 4    42  8 16 23  ");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", getOutput());
    }

    @Test
    public void outputIsCorrectWithNegativeIntegersInInput() {
        setInput("1 2 -3 4 -5");
        viewModel.calculate();

        assertEquals("-5 -3 1 2 4", getOutput());
    }

    @Test
    public void outputIsCorrectWithIntegersWithPlusInInput() {
        setInput("15 +4 42 8 +16 23");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", getOutput());
    }


    private void setInput(final String inputStr) {
        viewModel.inputProperty().set(inputStr);
    }

    private String getInput() {
        return viewModel.inputProperty().get();
    }

    private String getStatus() {
        return viewModel.statusProperty().get();
    }

    private String getOutput() {
        return viewModel.outputProperty().get();
    }

    private boolean sortIsDisable() {
        return viewModel.sortDisabledProperty().get();
    }

    private ViewModel viewModel;
}
