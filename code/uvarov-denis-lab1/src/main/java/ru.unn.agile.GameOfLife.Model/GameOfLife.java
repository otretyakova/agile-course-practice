package ru.unn.agile.GameOfLife.Model;

public class GameOfLife {

    public GameOfLife() {
        currentGeneration = null;
        nextGeneration = null;
    }

    public void readCurrentGeneration(final String[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (input.length < MINIMAL_INPUT_STRINGS_COUNT) {
            throw new IllegalArgumentException(
                    "Incorrect input value! Not enough count of input strings");
        }
        String[] sizeParse = input[0].split(" ");
        if (sizeParse.length != SIZE_TYPES_COUNT) {
            throw new IllegalArgumentException("Incorrect count of grid size parameters!");
        }

        int[] numArr = new int[SIZE_TYPES_COUNT];

        for (int i = 0; i < SIZE_TYPES_COUNT; i++) {
            numArr[i] = Integer.parseInt(sizeParse[i]);
        }

        final int sizeX = numArr[0];
        final int sizeY = numArr[1];

        if (sizeY != input.length - 1) {
            throw new IllegalArgumentException("Incorrect Y-axis size!");
        }

        int[][] arrayForGrid = new int[sizeX][sizeY];

        for (int i = 0; i < sizeY; i++) {
            if (input[i + 1].length() != sizeX) {
                throw new IllegalArgumentException("Incorrect X-axis size!");
            } else {
                for (int j = 0; j < sizeX; j++) {
                    arrayForGrid[j][i] = parseSymbolToState(input[i + 1].charAt(j));
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
                output[1 + i] = output[1 + i].concat(codeStateToSymbol(grid[j][i]));
            }
        }
        return output;
    }

    public void buildNextGeneration() {
        final int sizeX = getCurrentGeneration().getSizeX();
        final int sizeY = getCurrentGeneration().getSizeY();
        int[][] gridForNexGen = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                int aliveNeighbors = currentGeneration.countAliveNeighbors(i, j);
                gridForNexGen[i][j] = rulesForNetState(aliveNeighbors);
            }
        }
        nextGeneration = new Map(gridForNexGen);
    }

    public Map getCurrentGeneration() {
        if (currentGeneration == null) {
            throw new NullPointerException("Current generation was not set!");
        } else {
            return currentGeneration;
        }
    }

    public Map getNextGeneration() {
        if (nextGeneration == null) {
            throw new NullPointerException("Next generation was not calculated!");
        } else {
            return nextGeneration;
        }
    }

    private static int rulesForNetState(final int aliveNeighborsCount) {
        if (aliveNeighborsCount >= MIN_ALIVE_NEIGHBORS
                && aliveNeighborsCount <= MAX_ALIVE_NEIGHBORS) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int parseSymbolToState(final char value) {
        if (value == '.') {
            return 0;
        } else if (value == '*') {
            return 1;
        } else {
            throw new IllegalArgumentException("Incorrect argument '" + value + "'!");
        }
    }

    private static String codeStateToSymbol(final int value) {
        if (value == 0) {
            return ".";
        } else if (value == 1) {
            return "*";
        } else {
            throw new IllegalArgumentException("Incorrect state value: '" + value + "'!");
        }
    }

    private static final int SIZE_TYPES_COUNT = 2;
    private static final int MINIMAL_INPUT_STRINGS_COUNT = 2;
    private static final int MIN_ALIVE_NEIGHBORS = 3;
    private static final int MAX_ALIVE_NEIGHBORS = 4;
    private Map currentGeneration;
    private Map nextGeneration;
}
