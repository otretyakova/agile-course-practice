package ru.unn.agile.Polynomial.viewmodel;

import ru.unn.agile.Polynomial.model.Polynomial;

public final class Parser {
    public static Polynomial getPolynomial(final String txt) {
        if (!isValidText(txt)) {
            return null;
        }
        String strPolynomial = new String(txt);
        if (strPolynomial.charAt(0) != '-') {
            strPolynomial = "+" + strPolynomial;
        }
        strPolynomial = strPolynomial.replace('^', 'y');
        strPolynomial = strPolynomial.replaceAll("[()]", "z");
        strPolynomial = strPolynomial.replaceAll("xyz", "z");
        String[] coefficientsAndDegrees = strPolynomial.split("z");
        Polynomial polynomial = new Polynomial();
        try {
            for (int i = 0; i < coefficientsAndDegrees.length; i += 2) {
                if (coefficientsAndDegrees[i].charAt(0) != '+'
                        && coefficientsAndDegrees[i].charAt(0) != '-') {
                    throw new Exception("No sign between monomials!");
                }
                Double coefficient = Double.parseDouble(coefficientsAndDegrees[i]);
                Integer degree = Integer.parseInt(coefficientsAndDegrees[i + 1]);
                polynomial.addMonomial(degree, coefficient);
            }
        } catch (Exception ex) {
            polynomial = null;
        }
        return polynomial;
    }

    private static boolean isValidText(final String txt) {
        boolean result;
        result = !txt.isEmpty();
        result &= txt.indexOf(' ') == -1;
        result &= txt.indexOf('y') == -1;
        result &= txt.indexOf('z') == -1;
        result &= (txt.replaceAll("[(]", "").length()
                == txt.replaceAll("[)]", "").length());
        String tempTxt = txt.replace('^', 'y');
        tempTxt = tempTxt.replace('(', 'z');
        tempTxt = tempTxt.replaceAll("xyz", "(");
        result &= (tempTxt.replaceAll("[(]", "").length()
                == tempTxt.replaceAll("[)]", "").length());
        return result;
    }

    private Parser() { }
}
