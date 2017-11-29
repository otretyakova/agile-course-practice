package ru.unn.agile.PrimeNumber.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumber {

    private Integer leftLim;
    private Integer rightLim;

    private List<Integer> primeNum;
    private PrimeNumber() {
        this.primeNum = new ArrayList<Integer>();
    }

    public void setLim(final Integer left, final Integer right) {

        if (left < right) {
            this.leftLim = left;
            this.rightLim = right;
        } else {
            this.leftLim = right;
            this.rightLim = left;
        }

        if (this.leftLim < 1) {
            this.leftLim = 1;
        }
    }

    public PrimeNumber(final Integer left, final Integer right) {

        if (left > right) {
            this.leftLim = right;
            this.rightLim = left;
        } else {
            this.leftLim = left;
            this.rightLim = right;
        }

        if (this.leftLim < 1) {
            this.leftLim = 1;
        }

        this.primeNum = new ArrayList<Integer>();
    }

    private boolean isPrime(final Integer num) {

        for (Integer divider = 2; divider < num; divider++) {
            Integer wholePart = (num / divider) * divider;
            if (wholePart == num) {
                return false;
            }
        }
        return true;
    }

    public void findPrimeNumberFromRangeSimpleSearch() {

        this.primeNum.clear();

        for (Integer testNum = this.leftLim; testNum <= this.rightLim; testNum++) {
            if (isPrime(testNum)) {
                this.primeNum.add(testNum);
            }
        }
    }

    private boolean[] eratosthenes(final Integer num) {

        boolean[] isPrime = new boolean[num + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = true;

        for (Integer divider = 2; divider <= num; divider++) {
            if (isPrime[divider]) {
                for (Integer dividend = 2 * divider; dividend <= num; dividend += divider) {
                    isPrime[dividend] = false;
                }
            }
        }

        return isPrime;
    }

    public void findPrimeNumberFromRangeEratosthenes() {

        if (rightLim > 0) {

            this.primeNum.clear();
            boolean[] isPrime = eratosthenes(this.rightLim);


            for (Integer testNum = this.leftLim; testNum <= this.rightLim; testNum++) {
                if (isPrime[testNum]) {
                    this.primeNum.add(testNum);
                }
            }
        }
    }

    public int getLeftLim() {
        return this.leftLim;
    }

    public int getRightLim() {
        return this.rightLim;
    }

    public List<Integer> getPrimeList() {
        return this.primeNum;
    }

    public void printAllPrimeList() {

        for (Integer prime : primeNum) {
             System.out.printf("%i\n", prime.intValue());
        }
    }

}
