package ru.unn.agile.NumbersInWords.viewmodel;

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
    public void canSetDefaultNumber() {
        assertEquals("", viewModel.getInputNumber());
    }

    @Test
    public void canSetDefaultResultWord() {
        assertEquals("", viewModel.getResultWord());
    }

    @Test
    public void canSetDefaultStatus() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenEmptyField() {
        viewModel.setNumber("");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenInputMinus() {
        viewModel.setNumber("-");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenFieldAreFill() {
        viewModel.setNumber("123");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenIncorrectInputNumber() {
        viewModel.setNumber("a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenIncorrectSeparator() {
        viewModel.setNumber("65,7");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputNumberTooBig() {
        viewModel.setNumber("1000000000000");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputNumberTooSmall() {
        viewModel.setNumber("-1000000000000");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void translateButtonIsDisabledWhenInputIsEmpty() {
        viewModel.setNumber("");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsDisabledWhenFormatIsBad() {
        viewModel.setNumber("ab");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsDisabledWhenIncorrectSeparator() {
        viewModel.setNumber("54,7");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsDisabledWhenInputNumberTooBig() {
        viewModel.setNumber("1000000000000");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsDisabledWhenInputNumberTooSmall() {
        viewModel.setNumber("-1000000000000");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsEnabledWhenCorrectInput() {
        viewModel.setNumber("2.2");
        assertFalse(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsEnabledWhenNegativeNumber() {
        viewModel.setNumber("-89");
        assertFalse(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void canTranslateCorrectInputNumber() {
        viewModel.setNumber("922");
        viewModel.translate();
        assertEquals("nine hundred and twenty two", viewModel.getResultWord());
    }

    @Test
    public void statusIsSuccessWhenTranslateCorrectInputNumber() {
        viewModel.setNumber("922");
        viewModel.translate();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canTranslateDoubleInputNumber() {
        viewModel.setNumber("0.123456789");
        viewModel.translate();
        assertEquals("zero point one two three four five six seven eight nine",
                viewModel.getResultWord());
    }

    @Test
    public void canTranslateNegativeInputNumber() {
        viewModel.setNumber("-9.6");
        viewModel.translate();
        assertEquals("negative nine point six", viewModel.getResultWord());
    }

    @Test
    public void statusIsWaitingAfterClearInputNumber() {
        viewModel.setNumber("894");
        viewModel.setNumber("");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void translateButtonIsDisabledAfterClearInputNumber() {
        viewModel.setNumber("894");
        viewModel.setNumber("");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void statusIsReadyWhenInputNewNumber() {
        viewModel.setNumber("654");
        viewModel.translate();
        viewModel.setNumber("65");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void resultIsClearWhenInputNewNumber() {
        viewModel.setNumber("654");
        viewModel.translate();
        viewModel.setNumber("2");
        assertEquals("", viewModel.getResultWord());
    }

    private ViewModel viewModel;
}
