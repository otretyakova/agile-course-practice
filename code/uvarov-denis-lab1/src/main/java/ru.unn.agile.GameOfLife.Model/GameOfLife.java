package ru.unn.agile.GameOfLife.Model;

public class GameOfLife {
    private Map currentGeneration;
    private Map nextGeneration;

    public GameOfLife() {
    }

    public void ReadCurrentGeneration(final String[] input) {
        // Parse current generation to Map view
    }

    public String[] WriteNextGeneration() {
        String[] ret = {"", ""};
        return ret;
    }

    public void BuildNextGeneration() {
        // Create next generation
    }

    public Map GetCurrentGeneration() {
        throw new NullPointerException();
    }

    public Map GetNextGeneration() {
        throw new NullPointerException();
    }
}
