package ru.unn.agile.BookSale.infrastructure_lab3_legacy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.BookSale.ViewModel.legacy.RegexMatcher.matchesPattern;

public class TxtLoggerTests {
    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAMES);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAMES));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAMES + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testmessage = "Test message";

        txtLogger.log(testmessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(".*" + testmessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message one", "Test message two"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessage = txtLogger.getLog();
        for (int i = 0; i < actualMessage.size(); i++) {
            assertThat(actualMessage.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    private static final String FILENAMES = "./TxtLoggerTests-lab3-legacy.log";
    private TxtLogger txtLogger;
}
