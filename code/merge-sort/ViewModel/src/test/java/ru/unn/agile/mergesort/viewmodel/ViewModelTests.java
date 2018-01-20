package ru.unn.agile.mergesort.viewmodel;

import javafx.beans.property.StringProperty;
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
        input = viewModel.inputProperty();
        status = viewModel.statusProperty();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", input.get());
        assertEquals("", viewModel.outputProperty().get());
        assertEquals(Status.WAITING.toString(), status.get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyInput() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), status.get());
    }

    @Test
    public void statusIsWaitingWithInputWithSpacesOnly() {
        input.set("   ");

        assertEquals(Status.WAITING.toString(), status.get());
    }

    @Test
    public void statusIsReadyWhenInputIsFilledByIntegers() {
        input.set("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), status.get());
    }

    @Test
    public void statusIsWaitingAfterInputErase() {
        input.set("15 4 42 8 16 23");
        input.set("");

        assertEquals(Status.WAITING.toString(), status.get());
    }

    @Test
    public void statusIsBadFormatWhenInputHasWrongFormat() {
        input.set("How this program works???");

        assertEquals(Status.BAD_FORMAT.toString(), status.get());
    }

    @Test
    public void statusIsBadFormatWhenInputHasNotANumberWord() {
        input.set("15 4 42 Oops 8 16 23");

        assertEquals(Status.BAD_FORMAT.toString(), status.get());
    }

    @Test
    public void statusIsReadyAfterBadFormatInputIsFixed() {
        input.set("How this program works???");
        input.set("15 4 42 8 16 23");

        assertEquals(Status.READY.toString(), status.get());
    }

    @Test
    public void statusIsSuccessAfterCalculation() {
        input.set("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), status.get());
    }

    @Test
    public void sortButtonIsDisabledInitially() {
        assertTrue(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void sortButtonIsDisabledWhenFormatIsBad() {
        input.set("15 4 42 Oops 8 16 23");

        assertTrue(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void sortButtonIsEnabledWithCorrectInput() {
        input.set("15 4 42 8 16 23");

        assertFalse(viewModel.sortDisabledProperty().get());
    }

    @Test
    public void outputIsCorrectAfterSortCorrectInput() {
        input.set("15 4 42 8 16 23");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", viewModel.outputProperty().get());
    }

    @Test
    public void outputIsCorrectWithAbnormalSpacePlacing() {
        input.set("  15 4    42  8 16 23  ");
        viewModel.calculate();

        assertEquals("4 8 15 16 23 42", viewModel.outputProperty().get());
    }

    private ViewModel viewModel;
    private StringProperty input;
    private StringProperty status;
}
