package ru.unn.agile.FinanceCalculator.Model;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public final class Finance {
    public Finance() {
        myDaysFinances = new HashMap<GregorianCalendar, DayFinance>();
    }
    public void addEatingOutCostForDate(final double cost,
                                        final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addEatingOutCost(cost);
    }

    public double getEatingCostForDate(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        return myDaysFinances.get(date).getEatingCost();
    }

    public void addProductsCostForDate(final double cost,
                                       final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addProductsCost(cost);
    }

    public double getProductsCostForDate(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        return myDaysFinances.get(date).getProductsCost();
    }

    public void addBuyCostForDate(final double cost,
                                  final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addBuyCost(cost);
    }

    public double getBuyCostForDate(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        return myDaysFinances.get(date).getBuyCost();

    }

    public void addTransportCost(final double cost, final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addTransportCost(cost);
    }

    public double getTransportCost(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        return myDaysFinances.get(date).getTransportCost();
    }

    public void addServicesCost(final double cost, final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addServicesCost(cost);
    }

    double getServicesCost(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        return myDaysFinances.get(date).getServicesCost();
    }

    void addEntertainmentCost(final double cost, final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        myDaysFinances.get(date).addEntertainmentCost(cost);
    }

    public double getEntertainmentCost(final GregorianCalendar date) throws Exception {
        checkInputDate(date);
        isExist(date);
        return myDaysFinances.get(date).getServicesCost();
    }

    private void checkInputDate(final GregorianCalendar inputDate) throws  NumberFormatException {
   /* Calendar calendar = new GregorianCalendar();
        calendar.set(inputDate.get(Calendar.YEAR),
                inputDate.get(Calendar.MONTH), inputDate.get(Calendar.DATE));

        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int mounth = calendar.getActualMaximum(Calendar.MONTH);
        if (days < inputDate.get(Calendar.DATE) && mounth < inputDate.get(Calendar.MONTH)) {
            throw new NumberFormatException("invalid data");
        }*/
        inputDate.setLenient(false);
        inputDate.getTime();
    }

    private void isExist(final GregorianCalendar inputDate) {
        if (myDaysFinances.get(inputDate) == null) {
            DayFinance finance = new DayFinance();
            myDaysFinances.put(inputDate, finance);
        }
    }


    private Map<GregorianCalendar, DayFinance> myDaysFinances;

}
