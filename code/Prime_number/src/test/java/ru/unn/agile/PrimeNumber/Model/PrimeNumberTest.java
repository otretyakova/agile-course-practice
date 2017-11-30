package ru.unn.agile.PrimeNumber.Model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PrimeNumberTest {

    @Test
    public void canCreateClassWithRange() {
        Integer left = 1;
        Integer right = 2;
        PrimeNumber limitTest = new PrimeNumber(left, right);

        Integer leftOut = limitTest.getLeftLim();
        Integer rightOut = limitTest.getRightLim();

        assertEquals(left, leftOut);
        assertEquals(right, rightOut);
    }

    @Test
    public void canCreateClassWithRangeAndResetRange() {
        Integer left = 1;
        Integer right = 2;
        PrimeNumber limitTest = new PrimeNumber(left, right);

        left = 3;
        right = 5;
        limitTest.setLim(left, right);
        Integer leftOut = limitTest.getLeftLim();
        Integer rightOut = limitTest.getRightLim();

        assertEquals(left, leftOut);
        assertEquals(right, rightOut);
    }

    @Test
    public void canCreateClassWithRangeAndResetReverseRange() {
        Integer left = 10;
        Integer right = 12;
        PrimeNumber limitTest = new PrimeNumber(left, right);

        left = 5;
        right = 3;
        limitTest.setLim(left, right);
        Integer leftOut = limitTest.getLeftLim();
        Integer rightOut = limitTest.getRightLim();

        assertEquals(right, leftOut);
        assertEquals(left, rightOut);
    }

    @Test
    public void canFindSimpleOnePrimeSimpleSearch() {
        Integer left = 1;
        Integer right = 2;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(2);

        searchOne.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleTwoPrimeSimpleSearch() {
        Integer left = 1;
        Integer right = 3;
        PrimeNumber searchTwo = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(2);
        prime.add(3);

        searchTwo.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = searchTwo.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindTwoPrimeComplexRangeSimpleSearch() {
        Integer left = 4;
        Integer right = 8;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(5);
        prime.add(7);

        searchOne.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithSwapLimitRangeSimpleSearch() {
        Integer left = 11;
        Integer right = 7;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(7);
        prime.add(11);

        search.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLeftLimitNoNaturalNumSimpleSearch() {
        Integer left = -3;
        Integer right = 4;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(2);
        prime.add(3);

        search.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLimitNoNaturalNumSimpleSearch() {
        Integer left = -3;
        Integer right = -10;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();

        search.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindBigNumberSimpleSearch() {
        Integer left = 1000000000;
        Integer right = 1000000020;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(1000000007);
        prime.add(1000000009);

        search.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindMaxIntSimpleSearch() {
        Integer left = Integer.MAX_VALUE - 1;
        Integer right = Integer.MAX_VALUE;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(Integer.MAX_VALUE);

        search.findPrimeNumberFromRangeSimpleSearch();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleOnePrimeEratosthenes() {
        Integer left = 8;
        Integer right = 11;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(11);

        searchOne.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleTwoPrimeEratosthenes() {
        Integer left = 13;
        Integer right = 17;
        PrimeNumber searchTwo = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(13);
        prime.add(17);

        searchTwo.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = searchTwo.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindTwoPrimeComplexRangeEratosthenes() {
        Integer left = 7;
        Integer right = 11;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(7);
        prime.add(11);

        searchOne.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithSwapLimitRangeEratosthenes() {
        Integer left = 5;
        Integer right = 3;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(3);
        prime.add(5);

        search.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLeftLimitNoNaturalNumEratosthenes() {
        Integer left = -2;
        Integer right = 2;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(2);

        search.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLimitNoNaturalNumEratosthenes() {
        Integer left = -5;
        Integer right = -8;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();

        search.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindBigNumberEratosthenes() {
        Integer left = 1000;
        Integer right = 1020;
        PrimeNumber search = new PrimeNumber(left, right);
        List<Integer> prime = new ArrayList<Integer>();
        prime.add(1009);
        prime.add(1013);
        prime.add(1019);

        search.findPrimeNumberFromRangeEratosthenes();
        List<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }
}
