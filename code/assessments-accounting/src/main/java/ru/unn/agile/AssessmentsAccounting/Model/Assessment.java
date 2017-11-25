package ru.unn.agile.assessmentsaccounting.model;

public enum Assessment {
    VeryBad(1),
    Bad(2),
    Satisfactorily(3),
    Good(4),
    VeryGood(5),
    Great(6),
    Perfect(7);

    private int mark;

    Assessment(final int mark) {
        this.mark = mark;
    }

    int getMark() {
        return this.mark;
    }
}
