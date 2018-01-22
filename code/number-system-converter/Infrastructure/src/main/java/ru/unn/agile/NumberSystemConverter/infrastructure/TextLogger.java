package ru.unn.agile.NumberSystemConverter.infrastructure;

import ru.unn.agile.NumberSystemConverter.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    public TextLogger(final String fileName) {
        this.fileName = fileName;

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (fileWriter != null) {
            writer = new BufferedWriter(fileWriter);
        } else {
            writer = null;
        }
    }

    @Override
    public void log(final String string) {
        try {
            writer.write(getCurrentTime() + ": " + string);
            writer.newLine();
            writer.flush();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        }

        ArrayList<String> log = new ArrayList<>();

        if (fileReader == null) {
            return log;
        }

        BufferedReader reader = new BufferedReader(fileReader);
        try {
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return log;
    }

    private static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private final BufferedWriter writer;
    private final String fileName;
}
