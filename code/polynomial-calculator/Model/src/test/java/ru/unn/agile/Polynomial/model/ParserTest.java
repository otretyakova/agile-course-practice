package ru.unn.agile.Polynomial.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void cantParseEmptyString() {
        assertFalse(Parser.getPolynomial("") != null);
    }

    @Test
    public void cantParseIntegerNumber() {
        assertFalse(Parser.getPolynomial("-1") != null);
    }

    @Test
    public void cantParseDoubleNumber() {
        assertFalse(Parser.getPolynomial("7.00005") != null);
    }

    @Test
    public void cantParseMonomialWithMissedDegree() {
        assertFalse(Parser.getPolynomial("1x") != null);
    }

    @Test
    public void cantParseMonomialWithEmptyDegree() {
        assertFalse(Parser.getPolynomial("1x^()") != null);
    }

    @Test
    public void cantParseMonomialWithNotIntegerDegrees() {
        assertFalse(Parser.getPolynomial("8x^(-131.0)") != null);
    }

    @Test
    public void cantParseMonomialWhereDegreeIsLetter() {
        assertFalse(Parser.getPolynomial("8x^(b)") != null);
    }

    @Test
    public void cantParseMonomialWithMissedCoefficient() {
        assertFalse(Parser.getPolynomial("x^(2)") != null);
    }

    @Test
    public void cantParseMonomialWhereCoefficientIsLetter() {
        assertFalse(Parser.getPolynomial("ax^(2)") != null);
    }

    @Test
    public void cantParseMonomialWhereCoefficientsIsMixOfLetterAndDigit() {
        assertFalse(Parser.getPolynomial("8ax^(1)") != null);
        assertFalse(Parser.getPolynomial("a9x^(3)") != null);
    }

    @Test
    public void cantParseMonomialWithWhitespacesInCoefficient() {
        assertFalse(Parser.getPolynomial("1 4x^(7)") != null);
    }

    @Test
    public void cantParseMonomialWithDoublePlusesAtTheBeginning() {
        assertFalse(Parser.getPolynomial("++1x^(1)") != null);
    }

    @Test
    public void cantParseMonomialWithTrashAtTheEnd() {
        assertFalse(Parser.getPolynomial("7x^(2)-") != null);
    }

    @Test
    public void cantParseMonomialWithWhitespaceAfterDegree() {
        assertFalse(Parser.getPolynomial("9x^(-1) ") != null);
    }

    @Test
    public void cantParseMonomialWithWhitespaceInDegree() {
        assertFalse(Parser.getPolynomial("-63x^( -2)") != null);
    }

    @Test
    public void cantParseMonomialWithBigX() {
        assertFalse(Parser.getPolynomial("881.0X^(9)") != null);
    }

    @Test
    public void cantParseMonomialWithPlusAtTheBeginning() {
        assertFalse(Parser.getPolynomial("+1x^(1)") != null);
    }

    @Test
    public void cantParseMonomialWithWhitespaceBeforeCoefficient() {
        assertFalse(Parser.getPolynomial(" 64x^(-2)") != null);
    }

    @Test
    public void cantParseMonomialWithWhitespaceAfterCoefficient() {
        assertFalse(Parser.getPolynomial("85 x^(1)") != null);
    }

    @Test
    public void cantParseMonomialWithoutRightCurlyInDegree() {
        assertFalse(Parser.getPolynomial("85x^(1") != null);
    }

    @Test
    public void canParseZeroMonomial() {
        Polynomial poly = Parser.getPolynomial("0.0x^(6)");
        assertFalse(poly == null);
        assertEquals("0.0", poly.toString());
    }

    @Test
    public void canParseMonomialWithPositiveCoefficientAndDegree() {
        Polynomial poly = Parser.getPolynomial("1.5074x^(10)");
        assertFalse(poly == null);
        assertEquals("1.5074x^(10)", poly.toString());
    }

    @Test
    public void canParseMonomialWithNegativeCoefficient() {
        Polynomial poly = Parser.getPolynomial("-111.56x^(9)");
        assertFalse(poly == null);
        assertEquals("-111.56x^(9)", poly.toString());
    }

    @Test
    public void canParseMonomialWithNegativeDegree() {
        Polynomial poly = Parser.getPolynomial("0.01x^(-339)");
        assertFalse(poly == null);
        assertEquals("0.01x^(-339)", poly.toString());
    }

    @Test
    public void canParseMonomialWithNegativeCoefficientAndDegree() {
        Polynomial poly = Parser.getPolynomial("-41.0441001x^(-9)");
        assertFalse(poly == null);
        assertEquals("-41.0441001x^(-9)", poly.toString());
    }

    @Test
    public void canParseMonomialWithStrangeDegree() {
        Polynomial poly = Parser.getPolynomial("5x^(-02)");
        assertFalse(poly == null);
        assertEquals("5.0x^(-2)", poly.toString());
    }

    @Test
    public void cantParsePolynomialWithMonomialWithoutDegree() {
        assertFalse(Parser.getPolynomial("1x^(2)-5x") != null);
    }

    @Test
    public void cantParsePolynomialWithWrongOperand() {
        assertFalse(Parser.getPolynomial("1x^(2)*5x^(-1)") != null);
    }

    @Test
    public void cantParsePolynomialWithBigX() {
        assertFalse(Parser.getPolynomial("-6x^(12)-4X^(-41)") != null);
    }

    @Test
    public void cantParsePolynomialWithNumberMonomial() {
        assertFalse(Parser.getPolynomial("1x^(2)+5") != null);
    }

    @Test
    public void cantParsePolynomialWithMonomialWithoutRightCurly() {
        assertFalse(Parser.getPolynomial("1x^(2)-2x^(-2") != null);
    }

    @Test
    public void cantParsePolynomialWithTrash() {
        assertFalse(Parser.getPolynomial("1x^(2)+word") != null);
    }

    @Test
    public void canParseZeroPolynomial() {
        Polynomial poly = Parser.getPolynomial("0.0x^(6)-0x^(-1)+0.0x^(4)");
        assertFalse(poly == null);
        assertEquals("0.0", poly.toString());
    }

    @Test
    public void canParsePolynomialWithIntegerCoefficients() {
        Polynomial poly = Parser.getPolynomial("1x^(89)-555x^(-41)+9x^(-1)");
        assertFalse(poly == null);
        assertEquals("-555.0x^(-41)+9.0x^(-1)+1.0x^(89)", poly.toString());
    }

    @Test
    public void canParsePolynomialWithFloatCoefficients() {
        Polynomial poly = Parser.getPolynomial("1.00001x^(89)-555.9999x^(-41)+9.3x^(-1)");
        assertFalse(poly == null);
        assertEquals("-555.9999x^(-41)+9.3x^(-1)+1.00001x^(89)", poly.toString());
    }

    @Test
    public void canParsePolynomialWithIntegerAndFloatCoefficients() {
        Polynomial poly = Parser.getPolynomial("11x^(2)-5x^(1)+0.010104x^(-4)-13x^(0)");
        assertFalse(poly == null);
        assertEquals("0.010104x^(-4)-13.0x^(0)-5.0x^(1)+11.0x^(2)", poly.toString());
    }

    @Test
    public void canParsePolynomialWhereAllMonomialsHaveTheSameDegree() {
        Polynomial poly = Parser.getPolynomial("88.1x^(-3)-1x^(-3)+2.00x^(-3)");
        assertFalse(poly == null);
        assertEquals("89.1x^(-3)", poly.toString());
    }

    @Test
    public void canParsePolynomialWhereSomeMonomialsHaveTheSameDegree() {
        Polynomial poly = Parser.getPolynomial("1.90x^(-4)-7x^(-77)-1.02x^(-77)");
        assertFalse(poly == null);
        assertEquals("-8.02x^(-77)+1.9x^(-4)", poly.toString());
    }

    @Test
    public void canParseArbitraryPolynomial() {
        Polynomial poly = Parser.getPolynomial(
                "1133123.000107x^(-423)-0.0000009x^(1)-999x^(0)");
        assertFalse(poly == null);
        assertEquals("1133123.000107x^(-423)-999.0x^(0)-9.0E-7x^(1)",
                poly.toString());
    }

    @Test
    public void cantParseTrash() {
        Polynomial poly = Parser.getPolynomial("I'm polynomial");
        assertFalse(poly != null);
    }
}
