package ru.unn.agile.AssessmentsAccounting.model;

public enum Assessment {

    VeryBad(1),
    Bad(2),
    Satisfactorily(3),
    Good(4),
    VeryGood(5),
    Great(6),
    Perfect(7);

    Assessment(final int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return this.mark;
    }

    private int mark;
}
