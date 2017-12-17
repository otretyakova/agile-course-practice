package ru.unn.agile.NumberSystemConverter.model;


public final class NumberSystemConverter {

    private NumberSystemConverter() {
    }

    public static String convert(final String input, final NumberSystemBase src,
                          final NumberSystemBase dst) throws IllegalArgumentException {
        String outputDEC = convertANY2DEC(input, src);
        return convertDEC2ANY(outputDEC, dst);
    }

    private static String convertANY2DEC(final String input, final NumberSystemBase src)
                                                            throws IllegalArgumentException {
        long decResult = 0L;
        validateInput(input, src.getBaseSystem());
        int numberLength = input.length();
        for (int pos = 0; pos < numberLength; pos++) {
            int curElem = Character.getNumericValue((input.charAt(pos)));
            decResult += curElem * Math.pow(src.getBaseSystem(), numberLength - pos - 1);
        }
        return Long.toString(decResult);
    }

    private static String convertDEC2ANY(final String input, final NumberSystemBase dst)
                                                            throws IllegalArgumentException {
        StringBuffer reverseOutput = new StringBuffer("");
        String[] hexLetters = {"A", "B", "C", "D", "E", "F"};
        long inputNum = 0L;
        inputNum = Long.parseLong(input);
        String output = "";
        long residue = 0L;
        while (inputNum != 0) {
            residue = inputNum % dst.getBaseSystem();
            if (residue < NumberSystemBase.DEC.getBaseSystem()) {
                reverseOutput.append(residue);
            } else {
                reverseOutput.append(hexLetters[
                        (int) residue - NumberSystemBase.DEC.getBaseSystem()]);
            }
            inputNum = inputNum / dst.getBaseSystem();
        }
        output = reverseOutput.reverse().toString();
        return output;
    }

    private static void validateInput(final String input, final int systemBase) {
        if (input == null) {
            throw new IllegalArgumentException("Input is Null");
        }
        for (int pos = 0; pos < input.length(); pos++) {
            int curElem = Character.getNumericValue((input.charAt(pos)));
            if ((curElem < 0) || (systemBase <= curElem)) {
                throw new IllegalArgumentException("Input string contains incorrect symbols");
            }
        }
    }
}
