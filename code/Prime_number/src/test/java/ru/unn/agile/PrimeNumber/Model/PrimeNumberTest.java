package ru.unn.agile.PrimeNumber.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PrimeNumberTest {

    @Test
    public void canCreateClassWithRange(){
        Integer left = 1;
        Integer right = 2;
        PrimeNumber limit = new PrimeNumber(left, right);

        Integer leftOut = limit.getLeftLim();
        Integer rightOut = limit.getRightLim();

        assertEquals(left, leftOut);
        assertEquals(right, rightOut);
    }

    @Test
    public void canFindSimpleOnePrimeSimpleSearch() {
        Integer left = 1;
        Integer right = 1;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);

        searchOne.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleTwoPrimeSimpleSearch() {
        Integer left = 1;
        Integer right = 2;
        PrimeNumber searchTwo = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);
        prime.add(2);

        searchTwo.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = searchTwo.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindTwoPrimeComplexRangeSimpleSearch() {
        Integer left = 4;
        Integer right = 8;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(5);
        prime.add(7);

        searchOne.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithSwapLimitRangeSimpleSearch() {
        Integer left = 11;
        Integer right = 7;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(7);
        prime.add(11);

        search.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLeftLimitNoNaturalNumSimpleSearch() {
        Integer left = -3;
        Integer right = 4;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);
        prime.add(2);
        prime.add(3);

        search.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLimitNoNaturalNumSimpleSearch() {
        Integer left = -3;
        Integer right = -10;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();

        search.findPrimeNumberFromRangeSimpleSearch();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleOnePrimeEratosthenes() {
        Integer left = 1;
        Integer right = 1;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);

        searchOne.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindSimpleTwoPrimeEratosthenes() {
        Integer left = 1;
        Integer right = 2;
        PrimeNumber searchTwo = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);
        prime.add(2);

        searchTwo.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = searchTwo.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindTwoPrimeComplexRangeEratosthenes() {
        Integer left = 7;
        Integer right = 11;
        PrimeNumber searchOne = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(7);
        prime.add(11);

        searchOne.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = searchOne.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithSwapLimitRangeEratosthenes() {
        Integer left = 5;
        Integer right = 3;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(3);
        prime.add(5);

        search.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLeftLimitNoNaturalNumEratosthenes() {
        Integer left = -2;
        Integer right = 2;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();
        prime.add(1);
        prime.add(2);

        search.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }

    @Test
    public void canFindWithLimitNoNaturalNumEratosthenes() {
        Integer left = -5;
        Integer right = -8;
        PrimeNumber search = new PrimeNumber(left, right);
        ArrayList<Integer> prime = new ArrayList<Integer>();

        search.findPrimeNumberFromRangeEratosthenes();
        ArrayList<Integer> resPrime = search.getPrimeList();

        assertEquals(prime, resPrime);
    }
}
