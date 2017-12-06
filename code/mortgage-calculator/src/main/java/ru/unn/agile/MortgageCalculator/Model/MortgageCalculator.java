package ru.unn.agile.MortgageCalculator.Model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class MortgageCalculator {
    public static final int MONTH_IN_YEAR = 12;
    private float amount;
    private int period;
    private String periodType;
    private float rate;
    private List<Integer> payments;
    private int payment;
    private String error;

    public MortgageCalculator(final float amount, final int period, final String periodType,
                              final float rate) {
        this.amount = amount;
        this.period = period;
        this.periodType = periodType;
        this.rate = rate;
        payments = new ArrayList<Integer>();
    }

    public List<Integer> differentiatedPayment() {
        int n = 0;
        float amountT = amount;
        if (period != 0) {
            if (rate != 0) {
                switch (periodType) {
                    case "month":
                        n = period;
                        break;
                    case "year":
                        n = period * MONTH_IN_YEAR;
                        break;
                    default:
                        error = "Type of period is undefined";
                        return null;
                }
                for (int i = 0; i < n; i++) {
                    payments.add(round(amountT / (n - i) + amountT * (rate / MONTH_IN_YEAR)));
                    amountT -= amount / n;
                }
                return payments;
            } else {
                error = "Rate is zero";
                return null;
            }
        } else {
            error = "Period is zero";
            return null;
        }
    }

    public int annuityPayment() {
        int n = period;
        if (period != 0) {
            if (rate != 0) {
                if (periodType != null && !periodType.equals("month")
                        && !periodType.equals("year")) {
                    error = "Type of period is undefined";
                    return 0;
                } else if (periodType != null && periodType.equals("year")) {
                    n *= MONTH_IN_YEAR;
                }
                payment = (int) (amount * (((rate / MONTH_IN_YEAR) * pow(1 + rate / MONTH_IN_YEAR,
                        n)) / (pow(1 + (rate / MONTH_IN_YEAR), n) - 1)));
                return payment;
            } else {
                error = "Rate is zero";
                return 0;
            }
        } else {
            error = "Period is zero";
            return 0;
        }
    }

    public int printPayments(final String paymentType, final int periodNumber) {
        int pay = 0;
        switch (paymentType) {
            case "differentiated":
                payments = differentiatedPayment();
                pay = payments.get(periodNumber);
                break;
            case "annuity":
                payment = annuityPayment();
                pay = payment;
                break;
            default:
                error = "Can't print payment of undefined type of payment";
                return 0;
        }
        return pay;
    }

    public String getError() {
        return error;
    }
}
