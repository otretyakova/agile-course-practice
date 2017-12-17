package ru.unn.agile.Polynomial.model;

public final class Parser {
    public static Polynomial getPolynomial(final String txt) {
        if (txt.isEmpty() || txt.indexOf(" ") != -1) {
            return null;
        }
        String strPolynomial = new String(txt);
        if (strPolynomial.charAt(0) != '-') {
            strPolynomial = "+" + strPolynomial;
        }
        strPolynomial = strPolynomial.replace('^', 'y');
        strPolynomial = strPolynomial.replaceAll("[()]", "x");
        strPolynomial = strPolynomial.replaceAll("xyx", "x");
        String[] coeffsAndDegrees = strPolynomial.split("x");
        if (txt.length() - txt.replaceAll("[)]", "").length()
                != coeffsAndDegrees.length / 2) {
            return null;
        }
        Polynomial polynomial = new Polynomial();
        try {
            for (int i = 0; i < coeffsAndDegrees.length; i += 2) {
                Double coefficient = Double.parseDouble(coeffsAndDegrees[i]);
                Integer degree = Integer.parseInt(coeffsAndDegrees[i + 1]);
                polynomial.addMonomial(degree, coefficient);
            }
        } catch (Exception ex) {
            polynomial = null;
        }
        return polynomial;
    }

    private Parser() { }
}
