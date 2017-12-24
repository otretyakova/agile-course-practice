package ru.unn.agile.LeftSidedHeap.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LeftSidedHeap.viewmodel.legacy.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final int randKey = 1;
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
        viewModel.setTextAdd("1-2.0");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillSimple() {
        viewModel.setTextRemove("1");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicated() {
        viewModel.setTextAdd("1-2.0;3-4;1-5.8");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillComplicated() {
        viewModel.setTextRemove("1;2;76");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicatedWithSpace() {
        viewModel.setTextAdd("1-2; 3-4 ; 1-5");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }
    @Test
    public void isStatusReadyWhenFieldsAddAreFillComplicatedWithSpaceWithDouble() {
        viewModel.setTextAdd("1-2.0; 3-4 ; 1-5.8");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsRemoveAreFillComplicatedWithSpace() {
        viewModel.setTextRemove("1 ; 2; 76");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatInAdd() {
        viewModel.setTextAdd("asdasfadsf");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatInRemove() {
        viewModel.setTextRemove("asdasfadsf");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatRemoveInAdd() {
        viewModel.setTextAdd("1; 2; 3");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatAddInRemove() {
        viewModel.setTextRemove("1 - 2; 3-4");

        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseAddIsOK() {
        viewModel.setTextAdd("a");
        viewModel.processKeyInTextField(randKey);
        viewModel.setTextAdd("1-3");
        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseRemoveIsOK() {
        viewModel.setTextRemove("a");
        viewModel.processKeyInTextField(randKey);
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField(randKey);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonsDisabledInitially() {
        assertEquals(false, viewModel.isButtonAddEnabled());
        assertEquals(false, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonAddDisabledWhenFormatAddIsBad() {
        viewModel.setTextAdd("1-3");
        viewModel.processKeyInTextField(randKey);
        assertEquals(true, viewModel.isButtonAddEnabled());

        viewModel.setTextAdd("sadfafasd");
        viewModel.processKeyInTextField(randKey);

        assertEquals(false, viewModel.isButtonAddEnabled());
    }

    @Test
    public void isButtonRemoveDisabledWhenFormatRemoveIsBad() {
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField(randKey);
        assertEquals(true, viewModel.isButtonRemoveEnabled());

        viewModel.setTextRemove("sadfafasd");
        viewModel.processKeyInTextField(randKey);

        assertEquals(false, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonRemoveEnabledWhenFormatAddIsBadFormatRemoveIsGood() {
        viewModel.setTextRemove("1");
        viewModel.processKeyInTextField(randKey);
        assertEquals(true, viewModel.isButtonRemoveEnabled());

        viewModel.setTextAdd("sadfafasd");
        viewModel.processKeyInTextField(randKey);

        assertEquals(true, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void isButtonAddEnabledWhenFormatRemoveIsBadFormatAddIsGood() {
        viewModel.setTextAdd("1-3");
        viewModel.processKeyInTextField(randKey);
        assertEquals(true, viewModel.isButtonAddEnabled());

        viewModel.setTextRemove("sadfafasd");
        viewModel.processKeyInTextField(randKey);

        assertEquals(true, viewModel.isButtonAddEnabled());
    }

    @Test
    public void isButtonAddDisabledWithIncompleteInput() {
        viewModel.setTextAdd("1-");

        viewModel.processKeyInTextField(randKey);

        assertEquals(false, viewModel.isButtonAddEnabled());
    }
    @Test
    public void isButtonsEnabledWithCorrectInput() {
        viewModel.setTextAdd("122-31233;31-321");
        viewModel.setTextRemove("122;31233");

        viewModel.processKeyInTextField(randKey);
        assertEquals(true, viewModel.isButtonAddEnabled());
        assertEquals(true, viewModel.isButtonRemoveEnabled());
    }

    @Test
    public void canAddOneElementInEmptyHeap() {
        viewModel.setTextAdd("1-0");

        viewModel.add();

        assertEquals("size: 1\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddTwoElementInEmptyHeap() {
        viewModel.setTextAdd("1-0; 2-1");

        viewModel.add();

        assertEquals("size: 2\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddOneElementInNoEmptyHeap() {
        viewModel.setTextAdd("10-3");
        viewModel.add();

        viewModel.setTextAdd("1-0");

        viewModel.add();

        assertEquals("size: 2\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canAddTwoElementInNoEmptyHeap() {
        viewModel.setTextAdd("10-3");
        viewModel.add();

        viewModel.setTextAdd("1-0;2-23");

        viewModel.add();

        assertEquals("size: 3\nmin: 1=0.0\nremove: -\n", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessageAfterAdd() {
        viewModel.setTextAdd("21312-23132");

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
        viewModel.setTextAdd("10-3");
        viewModel.add();
        viewModel.setTextRemove("10");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveOneElementFromHeapWithMoreElements() {
        viewModel.setTextAdd("10-3;120-2;1-56");
        viewModel.add();
        viewModel.setTextRemove("10");

        viewModel.remove();

        assertEquals("size: 2\nmin: 1=56.0\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithOneElements() {
        viewModel.setTextAdd("10-3");
        viewModel.add();
        viewModel.setTextRemove("10;2");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithTwoElements() {
        viewModel.setTextAdd("10-3;2-21");
        viewModel.add();
        viewModel.setTextRemove("10;2");

        viewModel.remove();

        assertEquals("size: 0\nmin: -\nremove: 10=3.0 2=21.0\n", viewModel.getResult());
    }

    @Test
    public void canRemoveTwoElementFromHeapWithMoreElements() {
        viewModel.setTextAdd("10-3;2-21;32-123");
        viewModel.add();
        viewModel.setTextRemove("10;32");

        viewModel.remove();

        assertEquals("size: 1\nmin: 2=21.0\nremove: 10=3.0 32=123.0\n", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessageAfterRemove() {
        viewModel.setTextAdd("21312-23132");
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
