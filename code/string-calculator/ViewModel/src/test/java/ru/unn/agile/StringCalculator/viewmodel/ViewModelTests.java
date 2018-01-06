package ru.unn.agile.StringCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class ViewModelTests {
    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
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

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException exception) {
            assertEquals("Logger parameter can't be null", exception.getMessage());
        } catch (Exception exception) {
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
        viewModel.setInputString("2,5");
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentAfterCalculation() {
        viewModel.setInputString("2,5");
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getInputString() + ".*"));
    }

    @Test
    public void argumentInfoIsProperlyFormatted() {
        viewModel.setInputString("2,5");
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Input string = " + viewModel.getInputString() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessagesOfCalculation() {
        viewModel.setInputString("2,5");
        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();
        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canPutSeveralLogMessagesOfInputArguments() {
        viewModel.setInputString("2,5");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setInputString("7,5");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setInputString("7,5,9");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void argumentIsCorrectlyLogged() {
        viewModel.setInputString("2,5");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input string = " + viewModel.getInputString()));
    }

    @Test
    public void calculateIsNotDisplayedInLogWhenButtonIsDisabled() {
        viewModel.calculate();
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setInputString("2,5");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setInputString("2,5");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    private ViewModel viewModel;
}
