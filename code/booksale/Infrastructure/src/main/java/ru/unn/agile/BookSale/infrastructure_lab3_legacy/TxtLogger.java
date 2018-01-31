package ru.unn.agile.BookSale.infrastructure_lab3_legacy;

import ru.unn.agile.BookSale.ViewModel.legacy.ILogger;

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
        this.filename = fileName;

        BufferedWriter logwriter = null;
        try {
            logwriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufWriter = logwriter;
    }

    @Override
    public void log(final String str) {
        try {
            bufWriter.write(now() + " > " + str);
            bufWriter.newLine();
            bufWriter.flush();
        } catch (Exception excp) {
            System.out.println(excp.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader buffreader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            buffreader = new BufferedReader(new FileReader(filename));
            String line = buffreader.readLine();

            while (line != null) {
                log.add(line);
                line = buffreader.readLine();
            }
        } catch (Exception excp) {
            System.out.println(excp.getMessage());
        }

        return log;
    }

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdatf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdatf.format(cal.getTime());
    }

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter bufWriter;
    private final String filename;
}
