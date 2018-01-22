package ru.unn.agile.StatisticalValues.infrastructure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import static junit.framework.TestCase.assertNotNull;
import static ru.unn.agile.StatisticalValues.infrastructure.RegularExpMatcher.matchesPattern;

public class StatisticalLoggerTests {
    @Before
    public void setUp() {
        statLogger = new StatisticalLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(statLogger);
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
        String testMessage = "Test statistical logger message";

        statLogger.addLogText(testMessage);

        String message = statLogger.getLogText().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test statistical logger message 1",
                             "Test statistical logger message 2"};

        statLogger.addLogText(messages[0]);
        statLogger.addLogText(messages[1]);

        List<String> actualMessages = statLogger.getLogText();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        statLogger.addLogText(testMessage);

        String message = statLogger.getLogText().get(0);
        String pattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
        assertThat(message, matchesPattern(pattern));
    }

    private static final String FILENAME = "./statLogger_Tests-lab3.log";
    private StatisticalLogger statLogger;
}
