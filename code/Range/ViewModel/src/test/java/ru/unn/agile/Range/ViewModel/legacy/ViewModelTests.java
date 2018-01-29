package ru.unn.agile.Range.ViewModel.legacy;

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
    public void isCalculateButtonIsDisabledWhenInputIsEmpty() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isInputRangeOrSetTextFieldIsDisabledWhenInputIsEmpty() {
        assertFalse(viewModel.isInputRangeOrSetTextFieldEnabled());
    }

    @Test
    public void isInputRangeOrSetTextFieldIsDisabledWhenOperationGETALLPOINTS() {
        viewModel.setOperation(ViewModel.Operation.GET_ALL_POINTS);
        assertFalse(viewModel.isInputRangeOrSetTextFieldEnabled());
    }

    @Test
    public void isInputRangeOrSetTextFieldIsDisabledWhenOperationGETENDPOINTS() {
        viewModel.setOperation(ViewModel.Operation.GET_END_POINTS);

        assertFalse(viewModel.isInputRangeOrSetTextFieldEnabled());
    }

    @Test
    public void isInputRangeOrSetTextFieldIsEnabledWhenOperationCONTAINSPOINTS() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_POINTS);

        assertTrue(viewModel.isInputRangeOrSetTextFieldEnabled());
    }

    @Test
    public void showMessageWhenInputIsEmpty() {
        assertEquals(ViewModel.Message.DEFAULT_RANGE, viewModel.getMessageText());
    }

    @Test
    public void isCalculateButtonIsEnabledWhenEnterRange() {
        viewModel.setInputRange("(1,10]");

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonIsEnabledWhenEnterOtherRange() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_RANGE);
        viewModel.setInputRangeOrSet("(1,10]");

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonIsEnabledWhenEnterSetOfNumbers() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_POINTS);
        viewModel.setInputRangeOrSet("{1,10}");

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonIsDisabledWhenClearInputRange() {
        viewModel.setInputRange("(1,10]");
        viewModel.setInputRange("");

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonIsDisabledWhenClearRangeOrSet() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_RANGE);
        viewModel.setInputRangeOrSet("(1,10]");
        viewModel.setInputRangeOrSet("");

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenCalculateGETALLPOINTS() {
        viewModel.setInputRange("(1,3]");
        viewModel.setOperation(ViewModel.Operation.GET_ALL_POINTS);
        viewModel.calculate();

        assertEquals("2, 3", viewModel.getResult());
    }

    @Test
    public void whenCalculateGETENDPOINTS() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.GET_END_POINTS);
        viewModel.calculate();

        assertEquals("2, 10", viewModel.getResult());
    }

    @Test
    public void whenCalculateAREEQUALRANGESIsTrue() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.ARE_EQUAL_RANGES);
        viewModel.setInputRangeOrSet("(1,10]");
        viewModel.calculate();

        assertEquals("These ranges are equal", viewModel.getResult());
    }

    @Test
    public void whenCalculateAREEQUALRANGESIsFalse() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.ARE_EQUAL_RANGES);
        viewModel.setInputRangeOrSet("(1,11]");
        viewModel.calculate();

        assertEquals("These ranges are not equal", viewModel.getResult());
    }

    @Test
    public void whenCalculateCONTAINSPOINTSOfTrue() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_POINTS);
        viewModel.setInputRangeOrSet("{2,4}");
        viewModel.calculate();

        assertEquals("The range contains these values", viewModel.getResult());
    }

    @Test
    public void whenCalculateCONTAINSPOINTSOfFalse() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_POINTS);
        viewModel.setInputRangeOrSet("{2,15}");
        viewModel.calculate();

        assertEquals("The range doesn't contain these values", viewModel.getResult());
    }

    @Test
    public void whenCalculateCONTAINSRANGEOfTrue() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_RANGE);
        viewModel.setInputRangeOrSet("[2,5]");
        viewModel.calculate();

        assertEquals("The range contains this range", viewModel.getResult());
    }

    @Test
    public void whenCalculateCONTAINSRANGEOfFalse() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_RANGE);
        viewModel.setInputRangeOrSet("[2,15]");
        viewModel.calculate();
        assertEquals("The range doesn't contain this range", viewModel.getResult());
    }

    @Test
    public void whenCalculateOVERLAPSRANGEOfTrue() {

        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);
        viewModel.setInputRangeOrSet("(2,5]");
        viewModel.calculate();

        assertEquals("These ranges overlap", viewModel.getResult());
    }

    @Test
    public void whenCalculateOVERLAPSRANGEOfFalse() {
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);
        viewModel.setInputRange("(1,10]");
        viewModel.setInputRangeOrSet("(20,50]");
        viewModel.calculate();

        assertEquals("These ranges don't overlap", viewModel.getResult());
    }

    @Test
    public void isCalculateButtonIsDisabledWhenEnterInvalidInputRange() {
        viewModel.setInputRange("abc");

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void showErrorMessageWhenEnterInvalidInputRange() {
        viewModel.setInputRange("(1,10]");
        viewModel.setInputRange("abc");

        assertEquals(ViewModel.Message.INCORRECT_INPUT_RANGE, viewModel.getMessageText());
    }

    @Test
    public void showErrorMessageWhenEnterInputLiftBoundExceedRight() {
        viewModel.setInputRange("(10,1]");

        assertEquals("Error: Value of left bound should not exceed value of right bound to the "
                + "inclusion in the range!", viewModel.getMessageText());
    }

    @Test
    public void isCalculateButtonIsDisabledWhenEnterInvalidInputRangeOrSet() {
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);
        viewModel.setInputRange("(1,10]");
        viewModel.setInputRangeOrSet("abc");

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void showErrorMessageWhenEnterInvalidInputOtherRange() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);
        viewModel.setInputRangeOrSet("abc");

        assertEquals(ViewModel.Message.INCORRECT_INPUT_ANOTHER_RANGE, viewModel.getMessageText());
    }

    @Test
    public void showErrorMessageWhenEnterInvalidInputSetOfNumber() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.CONTAINS_POINTS);
        viewModel.setInputRangeOrSet("abc");

        assertEquals(ViewModel.Message.INCORRECT_INPUT_SET_OF_NUMBERS, viewModel.getMessageText());
    }

    @Test
    public void showMessageWhenEnterValidInputRangeAndOtherRange() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);
        viewModel.setInputRangeOrSet("(3,6]");

        assertEquals(ViewModel.Message.CORRECT_INPUT_ANOTHER_RANGE, viewModel.getMessageText());
    }

    @Test
    public void showMessageWhenEnterValidInputRangeAndRangeOrSetEmpty() {
        viewModel.setInputRange("(1,10]");
        viewModel.setOperation(ViewModel.Operation.OVERLAPS_RANGE);

        assertEquals(ViewModel.Message.DEFAULT_ANOTHER_RANGE, viewModel.getMessageText());
    }

    private ViewModel viewModel;
}
