package ru.unn.agile.LeftSidedHeap.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LeftSidedHeap.viewmodel.legacy.ViewModel.Status;

import static org.junit.Assert.*;

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
        assertEquals("", viewModel.getTextAdd());
        assertEquals("", viewModel.getTextRemove());
        assertEquals("size: 0\nmin: -\nremove: -\n", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenAddWithEmptyFields() {
        viewModel.add();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenRemoveWithEmptyFields() {
        viewModel.remove();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddAreFillSimple() {
        viewModel.setTextAdd("1_2.0");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillSimple() {
        viewModel.setTextRemove("1");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicated() {
        viewModel.setTextAdd("1_2.0;3_4;1_5.8");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddContainsNegativeData() {
        viewModel.setTextAdd("1_-2.0;");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillComplicated() {
        viewModel.setTextRemove("1;2;76");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicatedWithSpace() {
        viewModel.setTextAdd("1_2; 3_4 ; 1_5");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }
    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicatedWithSpaceWithDouble() {
        viewModel.setTextAdd("1_2.0; 3_4 ; 1_5.8");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillComplicatedWithSpace() {
        viewModel.setTextRemove("1 ; 2; 76");

        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatInAdd() {
        viewModel.setTextAdd("asdasfadsf");

        viewModel.processKeyInTextField();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatInRemove() {
        viewModel.setTextRemove("asdasfadsf");

        viewModel.processKeyInTextField();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatRemoveInAdd() {
        viewModel.setTextAdd("1; 2; 3");

        viewModel.processKeyInTextField();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatAddInRemove() {
        viewModel.setTextRemove("1 _ 2; 3_4");

        viewModel.processKeyInTextField();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseAddIsOK() {
        viewModel.setTextAdd("a");
        viewModel.processKeyInTextField();
        viewModel.setTextAdd("1_3");
        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseRemoveIsOK() {
        viewModel.setTextRemove("a");
        viewModel.processKeyInTextField();
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonsDisabledInitially() {
        assertEquals(false, viewModel.isButtonAddEnabled());
        assertEquals(false, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonAddDisabledWhenFormatAddIsBad() {
        viewModel.setTextAdd("1_3");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonAddEnabled());

        viewModel.setTextAdd("sadfafasd");
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isButtonAddEnabled());
    }

    @Test
    public void isButtonRemoveDisabledWhenFormatRemoveIsBad() {
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonRemoveEnabled());

        viewModel.setTextRemove("sadfafasd");
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonRemoveEnabledWhenFormatAddIsBadFormatRemoveIsGood() {
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonRemoveEnabled());

        viewModel.setTextAdd("sadfafasd");
        viewModel.processKeyInTextField();

        assertEquals(true, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonRemoveDisabledWhenFormatAddIsBadFormatAndDeleteRemove() {
        viewModel.setTextRemove("1231");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonRemoveEnabled());

        viewModel.setTextAdd("asdagdbd");
        viewModel.processKeyInTextField();
        viewModel.setTextRemove("");
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonAddDisabledWhenFormatRemoveIsBadFormatAndDeleteAdd() {
        viewModel.setTextAdd("1_1");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonAddEnabled());

        viewModel.setTextRemove("safsgbd");
        viewModel.processKeyInTextField();
        viewModel.setTextAdd("");
        viewModel.processKeyInTextField();
        assertEquals(false, viewModel.isButtonAddEnabled());
    }

    @Test
    public void isButtonAddEnabledWhenFormatRemoveIsBadFormatAddIsGood() {
        viewModel.setTextAdd("1_3");
        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonAddEnabled());

        viewModel.setTextRemove("sadfafasd");
        viewModel.processKeyInTextField();

        assertEquals(true, viewModel.isButtonAddEnabled());
    }

    @Test
    public void isButtonAddDisabledWithIncompleteInput() {
        viewModel.setTextAdd("1_");

        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isButtonAddEnabled());
    }
    @Test
    public void isButtonsEnabledWithCorrectInput() {
        viewModel.setTextAdd("122_31233;31_321");
        viewModel.setTextRemove("122;31233");

        viewModel.processKeyInTextField();
        assertEquals(true, viewModel.isButtonAddEnabled());
        assertEquals(true, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void canAddOneElementInEmptyHeap() {
        viewModel.setTextAdd("1_0");

        viewModel.add();

        assertEquals("size: 1\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddOneNegativeElementInEmptyHeap() {
        viewModel.setTextAdd("1_-10.8");

        viewModel.add();

        assertEquals("size: 1\nmin: 1=-10.8\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddOneElementWithNegativeKeyInEmptyHeap() {
        viewModel.setTextAdd("-11_18");

        viewModel.add();

        assertEquals("size: 1\nmin: -11=18.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddTwoElementInEmptyHeap() {
        viewModel.setTextAdd("1_0; 2_1");

        viewModel.add();

        assertEquals("size: 2\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddOneElementInNoEmptyHeap() {
        viewModel.setTextAdd("10_3");
        viewModel.add();

        viewModel.setTextAdd("1_0");

        viewModel.add();

        assertEquals("size: 2\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddTwoElementInNoEmptyHeap() {
        viewModel.setTextAdd("10_3");
        viewModel.add();

        viewModel.setTextAdd("1_0;2_23");

        viewModel.add();

        assertEquals("size: 3\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessageAfterAdd() {
        viewModel.setTextAdd("21312_23132");

        viewModel.add();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessageInAdd() {
        viewModel.setTextAdd("asfsdf");

        viewModel.add();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canRemoveFromEmptyHeap() {
        viewModel.setTextRemove("21");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canRemoveOneElementFromHeapWithOneElement() {
        viewModel.setTextAdd("10_3");
        viewModel.add();
        viewModel.setTextRemove("10");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveOneElementFromHeapWithMoreElements() {
        viewModel.setTextAdd("10_3;120_2;1_56");
        viewModel.add();
        viewModel.setTextRemove("10");

        viewModel.remove();

        assertEquals("size: 2\nmin: 1=56.0\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithOneElements() {
        viewModel.setTextAdd("10_3");
        viewModel.add();
        viewModel.setTextRemove("10;2");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithTwoElements() {
        viewModel.setTextAdd("10_3;2_21");
        viewModel.add();
        viewModel.setTextRemove("10;2");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0 2=21.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementWithOneKeyFromHeap() {
        viewModel.setTextAdd("1_3;1_3435");
        viewModel.add();
        viewModel.setTextRemove("1");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 1=3.0 1=3435.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithMoreElements() {
        viewModel.setTextAdd("10_3;2_21;32_123");
        viewModel.add();
        viewModel.setTextRemove("10;32");

        viewModel.remove();

        assertEquals("size: 1\nmin: 2=21.0\nremove: 10=3.0 32=123.0\n", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessageAfterRemove() {
        viewModel.setTextAdd("21312_23132");
        viewModel.add();

        viewModel.setTextRemove("21312");
        viewModel.remove();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessageInRemove() {
        viewModel.setTextRemove("asfsdf");

        viewModel.remove();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

}
