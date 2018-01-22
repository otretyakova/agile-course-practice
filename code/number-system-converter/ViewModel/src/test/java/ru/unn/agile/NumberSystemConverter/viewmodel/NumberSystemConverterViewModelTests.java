package ru.unn.agile.NumberSystemConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.NumberSystemConverter.model.NumberSystemBase;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class NumberSystemConverterViewModelTests {
    public void setExternalViewModel(final NumberSystemConverterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setViewModel() {
        if (this.viewModel == null) {
            this.viewModel = new NumberSystemConverterViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        this.viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", this.viewModel.numberInBaseNumberSystemProperty().get());
        assertEquals("", this.viewModel.numberInTargetNumberSystemProperty().get());
        assertEquals(NumberSystemBase.DEC, this.viewModel.baseNumberSystemProperty().get());
        assertEquals(NumberSystemBase.DEC, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void decimalIsDefaultBaseNumberSystem() {
        assertEquals(NumberSystemBase.DEC, this.viewModel.baseNumberSystemProperty().get());
    }

    @Test
    public void decimalIsDefaultTargetNumberSystem() {
        assertEquals(NumberSystemBase.DEC, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void conversionButtonIsDisabledInitially() {
        assertFalse(this.viewModel.isConversionEnabled());
    }

    @Test
    public void conversionButtonIsEnabledWhenNumberInBaseNumberSystemIsEntered() {
        this.viewModel.numberInBaseNumberSystemProperty().set("123");

        assertTrue(this.viewModel.isConversionEnabled());
    }

    @Test
    public void errorMessageIsNotShownInitially() {
        assertFalse(this.viewModel.isErrorMessageShown());
        assertEquals("", this.viewModel.getErrorMessage());
    }

    @Test
    public void errorMessageIsShownWhenNumberInBaseNumberSystemHasIncorrectSymbols() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);
        this.viewModel.numberInBaseNumberSystemProperty().set("123");

        this.viewModel.convert();

        assertTrue(this.viewModel.isErrorMessageShown());
    }

    @Test
    public void canSetDecimalBaseNumberSystem() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.DEC);

        assertEquals(NumberSystemBase.DEC, this.viewModel.baseNumberSystemProperty().get());
    }

    @Test
    public void canSetDecimalTargetNumberSystem() {
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.DEC);

        assertEquals(NumberSystemBase.DEC, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void canSetOctalBaseNumberSystem() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.OCT);

        assertEquals(NumberSystemBase.OCT, this.viewModel.baseNumberSystemProperty().get());
    }

    @Test
    public void canSetOctalTargetNumberSystem() {
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.OCT);

        assertEquals(NumberSystemBase.OCT, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void canSetHexadecimalBaseNumberSystem() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.HEX);

        assertEquals(NumberSystemBase.HEX, this.viewModel.baseNumberSystemProperty().get());
    }

    @Test
    public void canSetHexadecimalTargetNumberSystem() {
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.HEX);

        assertEquals(NumberSystemBase.HEX, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void canSetBinaryBaseNumberSystem() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);

        assertEquals(NumberSystemBase.BIN, this.viewModel.baseNumberSystemProperty().get());
    }

    @Test
    public void canSetBinaryTargetNumberSystem() {
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.BIN);

        assertEquals(NumberSystemBase.BIN, this.viewModel.targetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromDecimalToBinaryHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.DEC);
        this.viewModel.numberInBaseNumberSystemProperty().set("123");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.BIN);

        this.viewModel.convert();

        assertEquals("1111011", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromBinaryToDecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);
        this.viewModel.numberInBaseNumberSystemProperty().set("1111011");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.DEC);

        this.viewModel.convert();

        assertEquals("123", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void leadingZerosDoNotAffectCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);
        this.viewModel.numberInBaseNumberSystemProperty().set("00001111011");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.DEC);

        this.viewModel.convert();

        assertEquals("123", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromDecimalToHexadecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.DEC);
        this.viewModel.numberInBaseNumberSystemProperty().set("123");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.HEX);

        this.viewModel.convert();

        assertEquals("7B", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromDecimalToOctalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.DEC);
        this.viewModel.numberInBaseNumberSystemProperty().set("123");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.OCT);

        this.viewModel.convert();

        assertEquals("173", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromOctalToDecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.OCT);
        this.viewModel.numberInBaseNumberSystemProperty().set("1473");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.DEC);

        this.viewModel.convert();

        assertEquals("827", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromHexadecimalToDecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.HEX);
        this.viewModel.numberInBaseNumberSystemProperty().set("AF43");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.DEC);

        this.viewModel.convert();

        assertEquals("44867", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromOctalToBinaryHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.OCT);
        this.viewModel.numberInBaseNumberSystemProperty().set("12345");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.BIN);

        this.viewModel.convert();

        assertEquals("1010011100101", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromOctalToHexadecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.OCT);
        this.viewModel.numberInBaseNumberSystemProperty().set("76543");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.HEX);

        this.viewModel.convert();

        assertEquals("7D63", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromHexadecimalToOctalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.HEX);
        this.viewModel.numberInBaseNumberSystemProperty().set("FFAFA334");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.OCT);

        this.viewModel.convert();

        assertEquals("37753721464", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromBinaryToHexadecimalHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);
        this.viewModel.numberInBaseNumberSystemProperty().set("100100101110100");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.HEX);

        this.viewModel.convert();

        assertEquals("4974", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void conversionFromHexadecimalToBinaryHasCorrectResult() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.HEX);
        this.viewModel.numberInBaseNumberSystemProperty().set("A3F");
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.BIN);

        this.viewModel.convert();

        assertEquals("101000111111", this.viewModel.numberInTargetNumberSystemProperty().get());
    }

    @Test
    public void canCloseErrorDialogIfErrorOccurredInConversion() {
        this.viewModel.errorMessageIsShownProperty().set(true);

        this.viewModel.closeErrorDialog();

        assertFalse(this.viewModel.isErrorMessageShown());
    }

    @Test
    public void errorMessageIsNotEmptyIfTryingToConvertNumberInWrongNumberSystem() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.BIN);
        this.viewModel.numberInBaseNumberSystemProperty().set("1234");

        this.viewModel.convert();

        assertTrue(this.viewModel.getErrorMessage().length() > 0);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new NumberSystemConverterViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException illegalArgumentException) {
            assertEquals("Logger parameter can not be null", illegalArgumentException.getMessage());
        } catch (Exception exception) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        final List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setInputData();

        this.viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CONVERT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.numberInBaseNumberSystemProperty().get()
                + ".*" + viewModel.baseNumberSystemProperty().get()
                + ".*" + viewModel.targetNumberSystemProperty().get()
                + ".*"
        ));
    }

    @Test
    public void argumentsInfoIsProperlyFormatted() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": NumberInBaseSystem = " + viewModel.numberInBaseNumberSystemProperty().get()
                + "; BaseNumberSystem = " + viewModel.baseNumberSystemProperty().get()
                + "; TargetNumberSystem = " + viewModel.targetNumberSystemProperty().get()
                + ".*"
        ));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.convert();
        viewModel.convert();
        viewModel.convert();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeBaseNumberSystemChangeInLog() {
        setInputData();

        viewModel.onBaseNumberSystemChanged(NumberSystemBase.DEC, NumberSystemBase.HEX);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(
                ".*" + LogMessages.BASE_SYSTEM_WAS_CHANGED + NumberSystemBase.HEX + ".*"
        ));
    }

    @Test
    public void canSeeTargetNumberSystemChangeInLog() {
        setInputData();

        viewModel.onTargetNumberSystemChanged(NumberSystemBase.DEC, NumberSystemBase.HEX);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(
                ".*" + LogMessages.TARGET_SYSTEM_WAS_CHANGED + NumberSystemBase.HEX + ".*"
        ));
    }

    @Test
    public void baseNumberSystemIsNotLoggedIfNotChanged() {
        viewModel.onBaseNumberSystemChanged(NumberSystemBase.DEC, NumberSystemBase.HEX);

        viewModel.onBaseNumberSystemChanged(NumberSystemBase.HEX, NumberSystemBase.HEX);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void targetNumberSystemIsNotLoggedIfNotChanged() {
        viewModel.onTargetNumberSystemChanged(NumberSystemBase.DEC, NumberSystemBase.HEX);

        viewModel.onTargetNumberSystemChanged(NumberSystemBase.HEX, NumberSystemBase.HEX);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.numberInBaseNumberSystemProperty().get() + "; "
                + viewModel.baseNumberSystemProperty().get() + "; "
                + viewModel.targetNumberSystemProperty().get() + "]"));
    }

    @Test
    public void convertIsNotCalledWhenButtonIsDisabled() {
        viewModel.convert();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.numberInBaseNumberSystemProperty().set("26");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.numberInBaseNumberSystemProperty().set("26");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    private void setInputData() {
        this.viewModel.baseNumberSystemProperty().set(NumberSystemBase.DEC);
        this.viewModel.targetNumberSystemProperty().set(NumberSystemBase.HEX);
        this.viewModel.numberInBaseNumberSystemProperty().set("26");
    }

    private NumberSystemConverterViewModel viewModel;
}
