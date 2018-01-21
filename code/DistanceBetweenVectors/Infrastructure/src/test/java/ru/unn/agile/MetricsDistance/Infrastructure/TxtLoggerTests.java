package ru.unn.agile.MetricsDistance.Infrastructure;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class TxtLoggerTests {
    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateLoggerWithEmptyFileName() {
        new TxtLogger("");
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertTrue(actualMessages.get(i).matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        String regexWithDateAndTime = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
        assertTrue(message.matches(regexWithDateAndTime));
    }

    @Test
    public void canLogWithTag() {
        String testMessage = "Test message";
        String logTag = "Test";

        txtLogger.log(testMessage, logTag);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(logTag + ".*" + testMessage));
    }

    @Test
    public void canLogWithEmptyTag() {
        String testMessage = "Test message";
        String logTag = "";

        txtLogger.log(testMessage, logTag);

        String message = txtLogger.getLog().get(0);
        String regexWithDateAndTime = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > " + testMessage;
        assertTrue(message.matches(regexWithDateAndTime));
    }

    private static final String FILENAME = "./TxtLogger_Tests-lab3.log";
    private TxtLogger txtLogger;
}
