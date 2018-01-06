package ru.unn.agile.StringCalculator.infrastructure;

import ru.unn.agile.StringCalculator.viewmodel.ILogger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception excp) {
            excp.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(now() + " > " + s);
            writer.newLine();
            writer.flush();
        } catch (Exception excp) {
            System.out.println(excp.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception excp) {
            System.out.println(excp.getMessage());
        }

        return log;
    }

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String fileName;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }
}
