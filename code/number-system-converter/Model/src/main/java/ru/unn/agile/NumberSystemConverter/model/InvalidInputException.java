package ru.unn.agile.NumberSystemConverter.model;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(final String message) {
        super(message);
    }
}
