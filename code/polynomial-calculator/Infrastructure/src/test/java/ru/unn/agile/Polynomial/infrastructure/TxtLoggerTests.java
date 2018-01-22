package ru.unn.agile.Polynomial.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class TxtLoggerTests {
    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
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
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.addInfo(testMessage);

        String messageInLog = txtLogger.getLog().get(0);
        assertTrue(messageInLog.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        txtLogger.addInfo(messages[0]);
        txtLogger.addInfo(messages[1]);

        List<String> listLogger= txtLogger.getLog();
        for (int i = 0; i < listLogger.size(); i++) {
            assertTrue(listLogger.get(i).matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLogger.addInfo(testMessage);

        String message = txtLogger.getLog().get(0);
        String dateRegex = "^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
        String timeRegex = "(0?[0-9]|(1?[0-9]|2[0-3])):([0-5][0-9]):([0-5][0-9])";
        assertTrue(message.matches(dateRegex + " " + timeRegex + " > .*"));
    }
    @Test
    public void notFailConstructTxtLoggerWithBadFileName() {
         TxtLogger txtLogger = new TxtLogger(FILENAME_ACCESS_DENIED);
    }

    @Test
    public void notFailLoggingWithNullPointerWriter() {
        TxtLogger badLogger = new TxtLogger(FILENAME_ACCESS_DENIED);
        badLogger.addInfo("");
    }

    private static final String FILENAME_ACCESS_DENIED = "";
    private static final String FILENAME = "./TxtLogger_Tests-lab3.log";
    private TxtLogger txtLogger;
}
