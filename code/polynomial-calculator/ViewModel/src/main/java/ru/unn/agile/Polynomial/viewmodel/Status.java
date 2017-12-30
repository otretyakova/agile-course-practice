package ru.unn.agile.Polynomial.viewmodel;

public enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate'"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");
    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
