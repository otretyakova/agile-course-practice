package ru.unn.agile.NumbersInWords.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

public class ViewModelTests {

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
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
    public void statusIsBadFormatWhenInputTooBigNumber() {
        viewModel.setNumber("1000000000000");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputTooBigNegativeNumber() {
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
    public void translateButtonIsDisabledWhenInputTooBigNumber() {
        viewModel.setNumber("1000000000000");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsDisabledWhenInputTooBigNegativeNumber() {
        viewModel.setNumber("-1000000000000");
        assertTrue(viewModel.isTranslateButtonDisabled());
    }

    @Test
    public void translateButtonIsEnabledWhenCorrectInputNumber() {
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
    public void statusIsBadFormatAfterInputIncorrectNumber() {
        viewModel.setNumber("894");
        viewModel.translate();
        viewModel.setNumber("894a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void translateButtonIsDisabledAfterInputIncorrectNumber() {
        viewModel.setNumber("894");
        viewModel.translate();
        viewModel.setNumber("894a");
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

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);
        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        viewModel.setNumber("124");
        viewModel.translate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.TRANSLATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputNumberAfterCalculation() {
        viewModel.setNumber("124");
        viewModel.translate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getInputNumber() + ".*"));
    }

    @Test
    public void logContainsResultAfterTranslation() {
        viewModel.setNumber("124");
        viewModel.translate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getResultWord() + ".*"));
    }

    @Test
    public void argumentsInfoIsProperlyFormatted() {
        viewModel.setNumber("124");
        viewModel.translate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Input number: " + viewModel.getInputNumber()
                + "; Result = " + viewModel.getResultWord() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.setNumber("124");
        viewModel.translate();
        viewModel.translate();
        viewModel.translate();
        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void inputNumberIsCorrectlyLogged() {
        viewModel.setNumber("124");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input number: " + viewModel.getInputNumber()));
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setNumber("124");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setNumber("124");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void logsAreEmptyByDefault() {
        assertEquals("", viewModel.getLogs());
    }

    @Test
    public void canChangeLogsWhenFocusChanged() {
        viewModel.setNumber("124");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLogs();
//        assertEquals(LogMessages.EDITING_FINISHED + "Input number: 124\n", message);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED + "Input number: 124\n"));
    }

    private ViewModel viewModel;
}
