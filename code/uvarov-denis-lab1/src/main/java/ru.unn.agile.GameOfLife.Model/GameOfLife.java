package ru.unn.agile.GameOfLife.Model;

public class GameOfLife {
    private static final int SIZE_TYPES_COUNT = 2;
    private static final int MINIMAL_INPUT_STRINGS_COUNT = 2;
    private static final int MIN_ALIVE_NEIGHBORS = 3;
    private static final int MAX_ALIVE_NEIGHBORS = 4;
    private Map currentGeneration;
    private Map nextGeneration;

    public GameOfLife() {
        currentGeneration = null;
        nextGeneration = null;
    }

    public void readCurrentGeneration(final String[] input) {
        if (input.length < MINIMAL_INPUT_STRINGS_COUNT) {
            throw new IllegalArgumentException();
        }
        String[] sizeParse = input[0].split(" ");
        if (sizeParse.length != SIZE_TYPES_COUNT) {
            throw new IllegalArgumentException();
        }

        int[] numArr = new int[SIZE_TYPES_COUNT];

        for (int i = 0; i < SIZE_TYPES_COUNT; i++) {
            numArr[i] = Integer.parseInt(sizeParse[i]);
        }

        int sizeX = numArr[0];
        int sizeY = numArr[1];

        if (sizeY != input.length - 1) {
            throw new IllegalArgumentException();
        }

        int[][] arrayForGrid = new int[sizeX][sizeY];

        for (int i = 0; i < sizeY; i++) {
            if (input[i + 1].length() != sizeX) {
                throw new IllegalArgumentException();
            } else {
                for (int j = 0; j < sizeX; j++) {
                    arrayForGrid[j][i] = convertSymbolToDot(input[i + 1].charAt(j));
                }
            }
        }

        currentGeneration = new Map(arrayForGrid);
    }

    public String[] writeNextGeneration() {
        int[][] grid = getNextGeneration().getGrid();
        String[] output = new String[1 + nextGeneration.getSizeY()];
        output[0] = String.format("%d %d", nextGeneration.getSizeX(), nextGeneration.getSizeY());
        for (int i = 0; i < nextGeneration.getSizeY(); i++) {
            output[1 + i] = "";
            for (int j = 0; j < nextGeneration.getSizeX(); j++) {
                output[1 + i] = output[1 + i].concat(convertDotToSymbol(grid[j][i]));
            }
        }
        return output;
    }

    public void buildNextGeneration() {
        int sizeX = getCurrentGeneration().getSizeX();
        int sizeY = getCurrentGeneration().getSizeY();
        int[][] gridForNexGen = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                int aliveNeighbors = currentGeneration.countAliveNeighbors(i, j);
                gridForNexGen[i][j] = rulesForNeighbors(aliveNeighbors);
            }
        }
        nextGeneration = new Map(gridForNexGen);
    }

    public Map getCurrentGeneration() {
        if (currentGeneration == null) {
            throw new NullPointerException();
        } else {
            return currentGeneration;
        }
    }

    public Map getNextGeneration() {
        if (nextGeneration == null) {
            throw new NullPointerException();
        } else {
            return nextGeneration;
        }
    }

    private static int rulesForNeighbors(final int count) {
        if (count >= MIN_ALIVE_NEIGHBORS && count <= MAX_ALIVE_NEIGHBORS) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int convertSymbolToDot(final char value) {
        if (value == '.') {
            return 0;
        } else if (value == '*') {
            return 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String convertDotToSymbol(final int value) {
        if (value == 0) {
            return ".";
        } else if (value == 1) {
            return "*";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
