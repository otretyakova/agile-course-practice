package ru.unn.agile.Triangle.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

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
        assertEquals("", viewModel.coordAxProperty().get());
        assertEquals("", viewModel.coordAyProperty().get());
        assertEquals("", viewModel.coordBxProperty().get());
        assertEquals("", viewModel.coordByProperty().get());
        assertEquals("", viewModel.coordCxProperty().get());
        assertEquals("", viewModel.coordCyProperty().get());
        // Sides
        assertEquals("|AB| = N/A", viewModel.sideABProperty().get());
        assertEquals("|AC| = N/A", viewModel.sideACProperty().get());
        assertEquals("|BC| = N/A", viewModel.sideBCProperty().get());
        // Corners
        assertEquals("ABC = N/A", viewModel.cornerABCProperty().get());
        assertEquals("ACB = N/A", viewModel.cornerACBProperty().get());
        assertEquals("BAC = N/A", viewModel.cornerBACProperty().get());
        // Other
        assertEquals("P = N/A", viewModel.perimeterProperty().get());
        assertEquals("S = N/A", viewModel.surfaceAreaProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordAx() {
        viewModel.coordAxProperty().set("A1");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordAy() {
        viewModel.coordAyProperty().set("A2");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordBx() {
        viewModel.coordBxProperty().set("B1");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordBy() {
        viewModel.coordByProperty().set("B2");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordCx() {
        viewModel.coordCxProperty().set("C1");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessageCoordCy() {
        viewModel.coordCyProperty().set("C2");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetStatusReadyOnlyIfAllDotsSet() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordAxProperty().set("80.43");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordAyProperty().set("5.11");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordBxProperty().set("73.12");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordByProperty().set("56.18");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordCxProperty().set("36.98");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordCyProperty().set("2.3");
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canNotifyWhenTriangleHasTwoEqualsDots() {
        viewModel.coordAxProperty().set("0.12");
        viewModel.coordAyProperty().set("5.0");
        viewModel.coordBxProperty().set("0.12");
        viewModel.coordByProperty().set("5.0");
        viewModel.coordCxProperty().set("36.05");
        viewModel.coordCyProperty().set("102.34");
        viewModel.calculate();
        assertEquals(Status.DEGENERATED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canNotifyWhenTriangleIsDegenerate() {
        viewModel.coordAxProperty().set("0.0");
        viewModel.coordAyProperty().set("0.0");
        viewModel.coordBxProperty().set("0.0");
        viewModel.coordByProperty().set("1.0");
        viewModel.coordCxProperty().set("0.0");
        viewModel.coordCyProperty().set("2.0");
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
        viewModel.calculate();
        assertEquals(Status.DEGENERATED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void perimeterIsCorrectlyCalculatedWhenFillingFields() {
        setInputData();
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
        assertEquals("P = 7.40", viewModel.perimeterProperty().get());
    }

    @Test
    public void surfaceAreaIsCorrectlyCalculatedWhenFillingFields() {
        setInputData();
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
        assertEquals("S = 2.00", viewModel.surfaceAreaProperty().get());
    }

    @Test
    public void sidesAreCorrectlyCalculatedWhenFillingFields() {
        setInputData();
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
        assertEquals("|AB| = 1.41", viewModel.sideABProperty().get());
        assertEquals("|AC| = 2.83", viewModel.sideACProperty().get());
        assertEquals("|BC| = 3.16", viewModel.sideBCProperty().get());
    }

    @Test
    public void cornersAreCorrectlyCalculatedWhenFillingFields() {
        setInputData();
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
        assertEquals("ABC = 1.11 rad", viewModel.cornerABCProperty().get());
        assertEquals("ACB = 0.46 rad", viewModel.cornerACBProperty().get());
        assertEquals("BAC = 1.57 rad", viewModel.cornerBACProperty().get());
    }

    @Test
    public void allStepsVerification() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        viewModel.coordAxProperty().set("25.0");
        viewModel.coordAyProperty().set("-12.6");
        viewModel.coordBxProperty().set("32.14");
        viewModel.coordByProperty().set("-2.85");
        viewModel.coordCxProperty().set("-4.11");
        viewModel.coordCyProperty().set("2.56");
        viewModel.calculate();
        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
        assertEquals("|AB| = 12.08", viewModel.sideABProperty().get());
        assertEquals("|AC| = 32.82", viewModel.sideACProperty().get());
        assertEquals("|BC| = 36.65", viewModel.sideBCProperty().get());
        assertEquals("ABC = 1.09 rad", viewModel.cornerABCProperty().get());
        assertEquals("ACB = 0.33 rad", viewModel.cornerACBProperty().get());
        assertEquals("BAC = 1.72 rad", viewModel.cornerBACProperty().get());
        assertEquals("P = 81.56", viewModel.perimeterProperty().get());
        assertEquals("S = 196.03", viewModel.surfaceAreaProperty().get());
    }

    private void setInputData() {
        viewModel.coordAxProperty().set("1.0");
        viewModel.coordAyProperty().set("0.0");
        viewModel.coordBxProperty().set("0.0");
        viewModel.coordByProperty().set("1.0");
        viewModel.coordCxProperty().set("-1.0");
        viewModel.coordCyProperty().set("-2.0");
    }
}
