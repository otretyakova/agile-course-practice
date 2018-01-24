package ru.unn.agile.ConvertNumeral.ViewModel.legacy;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void clean() {
        viewModel = null;
    }

    @Test
    public void canCreateViewModel() {
        assertNotNull(viewModel);
    }

    @Test
    public void convertButtonIsDisabledWhenInputIsEmpty() {
        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void showMessageWhenInputIsEmpty() {
        assertEquals(ViewModel.Message.DEFAULT, viewModel.getMessageText());
    }

    @Test
    public void convertButtonIsEnabledWhenEnterArabicNumber() {
        viewModel.setInputNumber("1");

        assertTrue(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void convertButtonIsEnabledWhenEnterRomanNumber() {
        viewModel.setInputNumber("I");

        assertTrue(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void convertButtonIsDisabledWhenClearInputNumber() {
        viewModel.setInputNumber("1");
        viewModel.setInputNumber("");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void whenConvertArabicNumber10DisplayRomanNumberX() {
        viewModel.setInputNumber("10");
        viewModel.convert();

        assertEquals("X", viewModel.getOutputNumber());
    }

    @Test
    public void whenConvertRomanNumberVDisplayArabicNumber5() {
        viewModel.setInputNumber("V");
        viewModel.convert();

        assertEquals("5", viewModel.getOutputNumber());
    }

    @Test
    public void convertButtonIsDisabledWhenEnterInvalidInputNumber() {
        viewModel.setInputNumber("abc");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void showErrorMessageWhenEnterInvalidInputNumber() {
        viewModel.setInputNumber("abc");

        assertEquals(ViewModel.Message.INCORRECT_INPUT, viewModel.getMessageText());
    }

    @Test
    public void showErrorMessageWhenConvertInvalidRomanNumber() {
        viewModel.setInputNumber("VXC");
        viewModel.convert();

        assertEquals("Error: Incorrectly entered Roman number!", viewModel.getMessageText());
    }

    @Test
    public void showErrorMessageWhenConvertArabicNumberMoreThen3999() {
        viewModel.setInputNumber("4000");
        String errorMessage = "Error: Incorrectly entered Arabic number."
                + " It should be between 0 and 3999!";
        viewModel.convert();

        assertEquals(errorMessage, viewModel.getMessageText());
    }

    @Test
    public void showMessageWhenCorrectInvalidArabicNumber() {
        viewModel.setInputNumber("abc");
        viewModel.setInputNumber("5");

        assertEquals(ViewModel.Message.ENTER_ARABIC, viewModel.getMessageText());
    }

    @Test
    public void showMessageWhenCorrectInvalidRomanNumber() {
        viewModel.setInputNumber("abc");
        viewModel.setInputNumber("XX");

        assertEquals(ViewModel.Message.ENTER_ROMAN, viewModel.getMessageText());
    }

    @Test
    public void showMessageWhenClearInputNumber() {
        viewModel.setInputNumber("a");
        viewModel.setInputNumber("");

        assertEquals(ViewModel.Message.DEFAULT, viewModel.getMessageText());
    }

    @Test
    public void canEnterNumericSymbolInInput() {
        assertTrue(viewModel.isAvailableInsertInput(0, "2"));
    }

    @Test
    public void canEnterIVXLCDMSymbolInInput() {
        assertTrue(viewModel.isAvailableInsertInput(0, "X"));
    }

    @Test
    public void canEnterSomeSymbolExcludingNumericAndRomansNumericInInput() {
        assertFalse(viewModel.isAvailableInsertInput(0, "a"));
    }

    @Test
    public void canEnterArabicWithCountOfSymbolsMore4InInput() {
        assertFalse(viewModel.isAvailableInsertInput(4, "2"));
    }

    @Test
    public void canInsertArabicWithCountOfSymbolsMore4InInput() {
        assertFalse(viewModel.isAvailableInsertInput(0, "12345"));
    }

    @Test
    public void canEnterNumericSymbolAfterRomansNumericSymbolInInput() {
        viewModel.setInputNumber("I");
        assertFalse(viewModel.isAvailableInsertInput(1, "2"));
    }

    private ViewModel viewModel;
}
