package ru.unn.agile.FinanceCalculator.Model;

public class DayFinance {
    public DayFinance() {
        myEatingOutCost = 0.;
        myProductsCost = 0.;
        myBuyCost = 0.;
        myTransportCost = 0.;
        myServicesCost = 0.;
        myEntertainmentCost = 0.;
    }
    public void addEatingOutCost(final double cost) throws Exception {
        checkInputCost(cost, myEatingOutCost);
        myEatingOutCost += cost;
    }

    public double getEatingCost() {
        return myEatingOutCost;
    }

    public void addProductsCost(final double cost) throws Exception {
        checkInputCost(cost, myProductsCost);
        myProductsCost += cost;
    }

    public double getProductsCost() {
        return myProductsCost;
    }

    public void addBuyCost(final double cost) throws Exception {
        checkInputCost(cost, myBuyCost);
        myBuyCost += cost;
    }

    public double getBuyCost() {
        return myBuyCost;
    }

    public void addTransportCost(final double cost) throws Exception {
        checkInputCost(cost, myTransportCost);
        myTransportCost += cost;
    }

    public double getTransportCost() {
        return myTransportCost;
    }

    public void addServicesCost(final double cost) throws Exception {
        checkInputCost(cost, myServicesCost);
        myServicesCost += cost;
    }

    double getServicesCost() {
        return myServicesCost;
    }

    void addEntertainmentCost(final double cost) throws Exception {
        checkInputCost(cost, myEntertainmentCost);
        myEntertainmentCost += cost;
    }

    public double getEntertainmentCost() {
        return myEntertainmentCost;
    }

    private void throwInlegalArgumentExeption(final String message) throws Exception {
        throw new IllegalArgumentException(message);
    }

    private void checkInputCost(final double cost, final double currentInput) throws Exception {
        if (cost < 0) {
            throwInlegalArgumentExeption("Cost must be positive!");
        }
        if (cost == Double.POSITIVE_INFINITY || cost == Double.NEGATIVE_INFINITY) {
            throwInlegalArgumentExeption("Cost can not be infinity!");
        }
        if (Double.isNaN(cost)) {
            throwInlegalArgumentExeption("Cost can not be NAN!");
        }
        if (Double.MAX_VALUE - cost - currentInput < 0) {
            throwInlegalArgumentExeption("Cost can not be infinity!");
        }
    }
    private double myEatingOutCost;
    private double myProductsCost;
    private double myBuyCost;
    private double myTransportCost;
    private double myServicesCost;
    private double myEntertainmentCost;
}
