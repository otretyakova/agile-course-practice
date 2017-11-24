package ru.unn.agile.NumbersInWords.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumbersInWordsConverterTest {
    private NumbersInWordsConverter converter;

    @Before
    public void setUp() {
        converter = new NumbersInWordsConverter();
    }

    @Test
    public void canConvertNumberOne() {
        String convertedNumber = converter.convert("1");
        assertEquals("one", convertedNumber);
    }

    @Test
    public void canConvertNumberTwo() {
        String convertedNumber = converter.convert("2");
        assertEquals("two", convertedNumber);
    }

    @Test
    public void canConvertNumberThree() {
        String convertedNumber = converter.convert("3");
        assertEquals("three", convertedNumber);
    }

    @Test
    public void canConvertNumberFour() {
        String convertedNumber = converter.convert("4");
        assertEquals("four", convertedNumber);
    }

    @Test
    public void canConvertNumberFive() {
        String convertedNumber = converter.convert("5");
        assertEquals("five", convertedNumber);
    }

    @Test
    public void canConvertNumberSix() {
        String convertedNumber = converter.convert("6");
        assertEquals("six", convertedNumber);
    }

    @Test
    public void canConvertNumberSeven() {
        String convertedNumber = converter.convert("7");
        assertEquals("seven", convertedNumber);
    }

    @Test
    public void canConvertNumberEight() {
        String convertedNumber = converter.convert("8");
        assertEquals("eight", convertedNumber);
    }

    @Test
    public void canConvertNumberNine() {
        String convertedNumber = converter.convert("9");
        assertEquals("nine", convertedNumber);
    }

    @Test
    public void canConvertNumberTen() {
        String convertedNumber = converter.convert("10");
        assertEquals("ten", convertedNumber);
    }

    @Test
    public void canConvertNumberEleven() {
        String convertedNumber = converter.convert("11");
        assertEquals("eleven", convertedNumber);
    }

    @Test
    public void canConvertNumberTwelve() {
        String convertedNumber = converter.convert("12");
        assertEquals("twelve", convertedNumber);
    }

    @Test
    public void canConvertNumberThirteen() {
        String convertedNumber = converter.convert("13");
        assertEquals("thirteen", convertedNumber);
    }

    @Test
    public void canConvertNumberFourteen() {
        String convertedNumber = converter.convert("14");
        assertEquals("fourteen", convertedNumber);
    }

    @Test
    public void canConvertNumberFifteen() {
        String convertedNumber = converter.convert("15");
        assertEquals("fifteen", convertedNumber);
    }

    @Test
    public void canConvertNumberSixteen() {
        String convertedNumber = converter.convert("16");
        assertEquals("sixteen", convertedNumber);
    }

    @Test
    public void canConvertNumberSeventeen() {
        String convertedNumber = converter.convert("17");
        assertEquals("seventeen", convertedNumber);
    }

    @Test
    public void canConvertNumberEighteen() {
        String convertedNumber = converter.convert("18");
        assertEquals("eighteen", convertedNumber);
    }

    @Test
    public void canConvertNumberNineteen() {
        String convertedNumber = converter.convert("19");
        assertEquals("nineteen", convertedNumber);
    }

    @Test
    public void canConvertNumberTwentyTwo() {
        String convertedNumber = converter.convert("22");
        assertEquals("twenty two", convertedNumber);
    }

    @Test
    public void canConvertNumberThirtyThree() {
        String convertedNumber = converter.convert("33");
        assertEquals("thirty three", convertedNumber);
    }

    @Test
    public void canConvertNumberFortyFour() {
        String convertedNumber = converter.convert("44");
        assertEquals("forty four", convertedNumber);
    }

    @Test
    public void canConvertNumberFiftyFive() {
        String convertedNumber = converter.convert("55");
        assertEquals("fifty five", convertedNumber);
    }

    @Test
    public void canConvertNumberSixtySix() {
        String convertedNumber = converter.convert("66");
        assertEquals("sixty six", convertedNumber);
    }

    @Test
    public void canConvertNumberSeventySeven() {
        String convertedNumber = converter.convert("77");
        assertEquals("seventy seven", convertedNumber);
    }

    @Test
    public void canConvertNumberEightyEight() {
        String convertedNumber = converter.convert("88");
        assertEquals("eighty eight", convertedNumber);
    }

    @Test
    public void canConvertNumberNinetyNIne() {
        String convertedNumber = converter.convert("99");
        assertEquals("ninety nine", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundred() {
        String convertedNumber = converter.convert("100");
        assertEquals("one hundred", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredTen() {
        String convertedNumber = converter.convert("110");
        assertEquals("one hundred and ten", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredEleven() {
        String convertedNumber = converter.convert("111");
        assertEquals("one hundred and eleven", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredTwentyTwo() {
        String convertedNumber = converter.convert("122");
        assertEquals("one hundred and twenty two", convertedNumber);
    }

    @Test
    public void canConvertNumberNineHundredTwentyTwo() {
        String convertedNumber = converter.convert("922");
        assertEquals("nine hundred and twenty two", convertedNumber);
    }

    @Test
    public void canConvertNumberOneThousand() {
        String convertedNumber = converter.convert("1000");
        assertEquals("one thousand", convertedNumber);
    }

    @Test
    public void canConvertNumberOneThousandOneHundredEleven() {
        String convertedNumber = converter.convert("1111");
        assertEquals("one thousand one hundred and eleven", convertedNumber);
    }

    @Test
    public void canConvertNumberOneThousandOneHundred() {
        String convertedNumber = converter.convert("1100");
        assertEquals("one thousand one hundred", convertedNumber);
    }

    @Test
    public void canConvertNumberTenThousandOneHundredEleven() {
        String convertedNumber = converter.convert("10111");
        assertEquals("ten thousand one hundred and eleven", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredThousand() {
        String convertedNumber = converter.convert("100000");
        assertEquals("one hundred thousand", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredThousandAndOne() {
        String convertedNumber = converter.convert("100001");
        assertEquals("one hundred thousand and one", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredThousandOneHundredAndOne() {
        String convertedNumber = converter.convert("100101");
        assertEquals("one hundred thousand one hundred and one", convertedNumber);
    }

    @Test
    public void canConvertNumberOneHundredAndTwentyTwoThousandOneHundredAndOne() {
        String convertedNumber = converter.convert("122101");
        assertEquals("one hundred and twenty two thousand one hundred and one", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMillion() {
        String convertedNumber = converter.convert("1000000");
        assertEquals("one million", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMillionAndTwelve() {
        String convertedNumber = converter.convert("1000012");
        assertEquals("one million and twelve", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMillionOneHundredThousand() {
        String convertedNumber = converter.convert("1100000");
        assertEquals("one million one hundred thousand", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMillionOneHundredThousandAndSixteen() {
        String convertedNumber = converter.convert("1100016");
        assertEquals("one million one hundred thousand and sixteen", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMilliard() {
        String convertedNumber = converter.convert("1000000000");
        assertEquals("one milliard", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMilliardAndOne() {
        String convertedNumber = converter.convert("1000000001");
        assertEquals("one milliard and one", convertedNumber);
    }

    @Test
    public void canConvertNumberOneMilliardOneHundredThousandAndOne() {
        String convertedNumber = converter.convert("1000100001");
        assertEquals("one milliard one hundred thousand and one", convertedNumber);
    }

    @Test
    public void canConvertBigNumber() {
        String convertedNumber = converter.convert("666777888999");
        assertEquals("six hundred and sixty six milliard seven hundred and seventy seven"
                + " million eight hundred and eighty eight thousand nine hundred and ninety nine",
                convertedNumber);
    }

    @Test
    public void canConvertZeroPointOne() {
        String convertedNumber = converter.convert("0.1");
        assertEquals("zero point one", convertedNumber);
    }

    @Test
    public void canConvertZeroPointOneTwoThreeFourFiveSixSevenEightNine() {
        String convertedNumber = converter.convert("0.123456789");
        assertEquals("zero point one two three four five six seven eight nine", convertedNumber);
    }

    @Test
    public void canConvertZeroPointZeroOne() {
        String convertedNumber = converter.convert("0.01");
        assertEquals("zero point zero one", convertedNumber);
    }

    @Test
    public void canConvertZeroPointZeroZeroOne() {
        String convertedNumber = converter.convert("0.001");
        assertEquals("zero point zero zero one", convertedNumber);
    }

    @Test
    public void canConvertZeroPointZeroZeroOneZeroOne() {
        String convertedNumber = converter.convert("0.00101");
        assertEquals("zero point zero zero one zero one", convertedNumber);
    }

    @Test
    public void canConvertBigDecimalNumber() {
        String convertedNumber = converter.convert("222222.0022");
        assertEquals("two hundred and twenty two thousand two hundred and "
                + "twenty two point zero zero two two",
                convertedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTwoPointsThrowsIllegalArgument() {
        converter.convert("0.001.01");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThereAreLettersThrowsIllegalArgument() {
        converter.convert("1aaa8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstDigitInTwoDigitNumberIsLetterThrowsIllegalArgument() {
        converter.convert("a2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLastDigitInTwoDigitNumberBeforeTwentyIsLetterThrowsIllegalArgument() {
        converter.convert("1a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLettersInDecimalPartThrowsIllegalArgument() {
        converter.convert("1.0aaa5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumberIsTooBigThrowsIllegalArgument() {
        converter.convert("1000000000000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumberIsNullThrowsIllegalArgument() {
        converter.convert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNumberIsEmptyThrowsIllegalArgument() {
        converter.convert("");
    }

    @Test
    public void canConvertNegativeNine() {
        String convertedNumber = converter.convert("-9");
        assertEquals("negative nine", convertedNumber);
    }

    @Test
    public void canConvertNegativeNinePointNine() {
        String convertedNumber = converter.convert("-9.9");
        assertEquals("negative nine point nine", convertedNumber);
    }

}
