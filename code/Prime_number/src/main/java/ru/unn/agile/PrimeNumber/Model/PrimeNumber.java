package ru.unn.agile.PrimeNumber.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

public class PrimeNumber {

    private Integer leftLim;
    private Integer rightLim;

    private List<Integer> primeNum;

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
        if (num < 2) {
            return false;
        }

        Integer limitNum = (int) (sqrt(num + 1));

        for (Integer divider = 2; divider <= limitNum; divider++) {
            if (num % divider == 0) {
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

            if (testNum == Integer.MAX_VALUE) {
                break;
            }
       }
    }

    private boolean[] eratosthenes(final Integer num) {
        boolean[] isPrime = new boolean[num + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        Integer limitNum = (int) (sqrt(num + 1));

        for (Integer divider = 2; divider <= limitNum; divider++) {
            if (isPrime[divider]) {
                for (Integer dividend = 2 * divider; dividend <= num; dividend += divider) {
                    isPrime[dividend] = false;

                    if ((Integer.MAX_VALUE - dividend) < divider) {
                        break;
                    }
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

                if (testNum == Integer.MAX_VALUE) {
                    break;
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
