package ru.unn.agile.StatisticalValues.infrastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import ru.unn.agile.StatisticalValues.viewmodel.ILogger;

public class StatisticalLogger implements ILogger {
    public StatisticalLogger(final String filename) {
        this.filename = filename;

        BufferedWriter statisticalLogWriter = null;
        try {
            statisticalLogWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writerBuffer = statisticalLogWriter;
    }

    @Override
    public void addLogText(final String s) {
        try {
            writerBuffer.write(now() + " > " + s);
            writerBuffer.newLine();
            writerBuffer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLogText() {
        BufferedReader reader;
        ArrayList<String> logResult = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                logResult.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logResult;
    }

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writerBuffer;
    private final String filename;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}

