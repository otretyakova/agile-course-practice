package ru.unn.agile.Polynomial.viewmodel;

public enum Operation {
    ADD("Add"),
    SUB("Sub"),
    MULTIPLY("Mul");
    private final String name;

    Operation(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
