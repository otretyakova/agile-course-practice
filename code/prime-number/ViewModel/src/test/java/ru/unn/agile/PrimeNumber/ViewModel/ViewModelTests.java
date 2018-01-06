package ru.unn.agile.PrimeNumber.ViewModel;

import javafx.collections.FXCollections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
//import javafx.observableArrayList;


//import ru.unn.agile.PrimeNumber.Model.PrimeNumber;
import ru.unn.agile.PrimeNumber.Model.PrimeNumber.Methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/*import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import ru.unn.agile.MetricsDistance.Model.Metric;*/

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
        assertEquals("", viewModel.getRangeFrom());
        assertEquals("", viewModel.getRangeTo());
        assertEquals("", viewModel.getMaxCountPrimes());
        assertEquals("", viewModel.getCurrentAnswer());
        assertEquals("", viewModel.getCurrentAnswer());
    }

    @Test
    public void canGetDefaultStatus() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canCalculate() {
        viewModel.calculate();
    }

    @Test
    public void checkBadFormatStatus() {
        setInvalidData();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusWithNegativeCount() {
        setNegativeIntegers();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusStatusWithBigIntegers() {
        setBigIntegers();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkWaitingStatusWithCorrectIncompleteRange() {
        viewModel.rangeFromProperty().set("2");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void checkWaitingStatusWithCorrectIncompleteData() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("7");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusWithInvalidIncompleteRange() {
        viewModel.rangeFromProperty().set("a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusWithInvalidRange() {
        viewModel.rangeFromProperty().set("a");
        viewModel.rangeToProperty().set("b");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusWithInvalidCount() {
        viewModel.maxCountPrimesProperty().set("a");
        viewModel.rangeFromProperty().set("-10");
        viewModel.rangeToProperty().set("10");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkBadFormatStatusWithBigIntegers() {
        setBigIntegers();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void checkReadyStatusWithCorrectData() {
        setCorrectData();
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenBadFormat() {
        setCorrectData();
        viewModel.rangeFromProperty().set("sdf");
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteRange() {
        viewModel.rangeFromProperty().set("1");
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setCorrectData();
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetEratosthenesMethod() {
        viewModel.methodProperty().set(Methods.ERATOSTHENES);
        assertEquals(Methods.ERATOSTHENES, viewModel.methodProperty().get());
    }

    @Test
    public void simpleIsDefaultMethod() {
        assertEquals(Methods.SIMPLE, viewModel.methodProperty().get());
    }

    @Test
    public void methodSimpleHasCorrectResult() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10");
        viewModel.maxCountPrimesProperty().set("3");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 4 primes in the range from 2 to 10.\n" +
                "Here are 3 of them:\n2, 3, ..., 7\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canPrintOnePrimeNumber() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10");
        viewModel.maxCountPrimesProperty().set("1");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 4 primes in the range from 2 to 10.\n" +
                "Here are 1 of them:\n2\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canPrintZeroPrimeNumbers() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10");
        viewModel.maxCountPrimesProperty().set("0");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 4 primes in the range from 2 to 10.\n"
                , viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canPrintTwoPrimeNumbers() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10000");
        viewModel.maxCountPrimesProperty().set("2");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 1229 primes in the range from 2 to 10000.\n" +
                "Here are 2 of them:\n2, ..., 9973\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canFindNoPrimes() {
        viewModel.rangeFromProperty().set("9980");
        viewModel.rangeToProperty().set("10000");
        viewModel.maxCountPrimesProperty().set("2");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("There are no primes in the range from 9980 to 10000.\n"
                , viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canFindPrimesWithNegativeBound() {
        viewModel.rangeFromProperty().set("-100");
        viewModel.rangeToProperty().set("100");
        viewModel.maxCountPrimesProperty().set("2");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 25 primes in the range from -100 to 100.\n" +
                "Here are 2 of them:\n2, ..., 97\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canFindPrimesWithReverseRange() {
        viewModel.rangeFromProperty().set("10");
        viewModel.rangeToProperty().set("2");
        viewModel.maxCountPrimesProperty().set("2");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 4 primes in the range from 10 to 2.\n" +
                "Here are 2 of them:\n2, ..., 7\n", viewModel.currentAnswerProperty().get());
    }


    @Test
    public void canFindPrimesWithEqualBounds() {
        viewModel.rangeFromProperty().set("13");
        viewModel.rangeToProperty().set("13");
        viewModel.maxCountPrimesProperty().set("10");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 1 primes in the range from 13 to 13.\n" +
                "Here are 1 of them:\n13\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canFindPrimesWithMaxCountMoreThanPrimes() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10");
        viewModel.maxCountPrimesProperty().set("9");
        viewModel.methodProperty().set(Methods.SIMPLE);

        viewModel.calculate();

        assertEquals("Found 4 primes in the range from 2 to 10.\n" +
                "Here are 4 of them:\n2, 3, 5, 7\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canFindPrimesWithEratosthenesMethod() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("10000");
        viewModel.maxCountPrimesProperty().set("9");
        viewModel.methodProperty().set(Methods.ERATOSTHENES);

        viewModel.calculate();

        assertEquals("Found 1229 primes in the range from 2 to 10000.\n" +
                "Here are 9 of them:\n2, 3, 5, 7, 11, ..., 9941, 9949, 9967, 9973\n", viewModel.currentAnswerProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canGetAnswersLists() {
        assertEquals(new ArrayList<String>(), viewModel.answersListProperty().get());
    }

    @Test
    public void canAddAnswerToAnswersList() {
        setCorrectData();

        viewModel.calculate();

        assertEquals(new ArrayList<String>() {{
            add("Found 1 primes in the range from 2 to 2.\n" +
                "Here are 1 of them:\n2\n"); }}, viewModel.answersListProperty().get());
    }

    @Test
    public void canSetCurrentAnswer() {
        viewModel.answersListProperty().set(FXCollections.observableArrayList(
            new ArrayList<String>(){{ add("a"); add("b"); }}
        ));
        viewModel.chooseAnswerById(0);
        assertEquals("a", viewModel.getCurrentAnswer());
    }

    private void setCorrectData() {
        viewModel.rangeFromProperty().set("2");
        viewModel.rangeToProperty().set("2");
        viewModel.maxCountPrimesProperty().set("10");
    }

    private void setInvalidData() {
        viewModel.rangeFromProperty().set("ajfla");
        viewModel.rangeToProperty().set("bdsf");
        viewModel.maxCountPrimesProperty().set("bsf");
    }

    private void setNegativeIntegers() {
        viewModel.rangeFromProperty().set("-1");
        viewModel.rangeToProperty().set("-1");
        viewModel.maxCountPrimesProperty().set("-1");
    }

    private void setBigIntegers() {
        viewModel.rangeFromProperty().set("9999999999");
        viewModel.rangeToProperty().set("9999999999");
        viewModel.maxCountPrimesProperty().set("9999999999");
    }

    private ViewModel viewModel;
}
/*    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getVec1X());
        assertEquals("", viewModel.getVec1Y());
        assertEquals("", viewModel.getVec2X());
        assertEquals("", viewModel.getVec2Y());
        assertEquals("", viewModel.getDim());
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setVec1X("a");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.setVec1X("1");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData("1", "2", "3", "4", "");
        viewModel.setVec1X("trash");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.setVec1X("1");
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData("1", "2", "3", "4", "");
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canSetMinkowskiL1Metric() {
        viewModel.setMetric(Metric.Minkowski);
        assertEquals(Metric.Minkowski, viewModel.getMetric());
    }

    @Test
    public void canSetChebyshevMetric() {
        viewModel.setMetric(Metric.Chebyshev);
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevIsDefaultOperation() {
        assertEquals(Metric.Chebyshev, viewModel.getMetric());
    }

    @Test
    public void chebyshevMetricHasCorrectResult() {
        setInputData("2", "2", "1", "3", "");
        viewModel.setMetric(Metric.Chebyshev);
        viewModel.calculate();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData("1", "2", "3", "4", "");
        viewModel.calculate();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setVec1X("#selfie");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData("1", "2", "3", "4", "");
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void minkowskiL1MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3", "1");
        viewModel.setMetric(Metric.Minkowski);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void minkowskiL2MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3", "2");
        viewModel.setMetric(Metric.Minkowski);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }
    @Test
    public void minkowskiL3MetircHasCorrectResult() {
        setInputData("3", "3", "1", "3", "3");
        viewModel.setMetric(Metric.Minkowski);
        viewModel.calculate();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void chebyshevMetricWithNegativeNumbersHasCorrectResult() {
        setInputData("1.2", "2.3", "-1.4", "-2.5", "");
        viewModel.setMetric(Metric.Chebyshev);
        viewModel.calculate();
        assertEquals("4.8", viewModel.resultProperty().get());
    }
    private ViewModel viewModel;

    private void setInputData(final String vec1X, final String vec1Y, final String vec2X,
                              final String vec2Y, final String dim) {
        viewModel.setVec1X(vec1X);
        viewModel.setVec1Y(vec1Y);
        viewModel.setVec2X(vec2X);
        viewModel.setVec2Y(vec2Y);
        viewModel.setDim(dim);
    }

}*/
