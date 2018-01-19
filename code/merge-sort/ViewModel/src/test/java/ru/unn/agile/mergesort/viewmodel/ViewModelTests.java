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
        assertEquals("", viewModel.inputProperty().get());
        assertEquals("", viewModel.outputProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyInput() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputIsFilledByIntegers() {
        viewModel.inputProperty().set("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingAfterInputErase() {
        viewModel.inputProperty().set("15 4 42 8 16 23");
        viewModel.inputProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInputHasWrongFormat() {
        viewModel.inputProperty().set("How this program works???");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInputHasNotANumberWord() {
        viewModel.inputProperty().set("15 4 42 Oops 8 16 23");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInputHasMoreThanTenNumbers() {
        viewModel.inputProperty().set("15 4 42 8 16 23 15 4 42 8 16");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyAfterBadFormatInputIsFixed() {
        viewModel.inputProperty().set("How this program works???");
        viewModel.inputProperty().set("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsSuccessAfterCalculation() {
        viewModel.inputProperty().set("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void sortButtonIsDisabledInitially() {
        assertTrue(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void sortButtonIsDisabledWhenFormatIsBad() {
        viewModel.inputProperty().set("15 4 42 Oops 8 16 23");

        assertTrue(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void sortButtonIsEnabledWithCorrectInput() {
        viewModel.inputProperty().set("15 4 42 8 16 23");

        assertFalse(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void outputIsCorrectAfterSortCorrectInput() {
        viewModel.inputProperty().set("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", viewModel.outputProperty().get());
    }

    private ViewModel viewModel;
}
