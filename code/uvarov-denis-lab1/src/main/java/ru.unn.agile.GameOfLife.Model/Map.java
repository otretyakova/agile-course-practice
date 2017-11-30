package ru.unn.agile.GameOfLife.Model;

public class Map {
    private int sizeX;
    private int sizeY;
    private int[][] grid;

    public Map(final int[][] grid) {
        if (grid.length < 1) {
            throw new IllegalArgumentException();
        }
        this.sizeX = grid.length;

        boolean isValidLength = true;

        for (int i = 1; i < sizeX; i++) {
            isValidLength = isValidLength && (grid[0].length == grid[i].length);
        }
        if (!isValidLength || grid[0].length < 1) {
            throw new IllegalArgumentException();
        }
        this.sizeY = grid[0].length;
        boolean isValidValues = true;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                isValidValues = isValidValues && grid[i][j] >= 0 && grid[i][j] <= 1;
            }
        }
        if (isValidValues) {
            this.grid = grid;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int[] getSize() {
        return new int[]{getSizeX(), getSizeY()};
    }

    public int[][] getGrid() {
        return grid;
    }

    public int countAliveNeighbors(final int coordX, final int coordY) {
        return leftNeighbor(coordX, coordY) + rightNeighbor(coordX, coordY)
                + upperNeighbor(coordX, coordY) + lowerNeighbor(coordX, coordY)
                + leftUpperNeighbor(coordX, coordY) + rightUpperNeighbor(coordX, coordY)
                + leftLowerNeighbor(coordX, coordY) + rightLowerNeighbor(coordX, coordY);
    }

    private int leftNeighbor(final int coordX, final int coordY) {
        return (coordX != 0) ? grid[coordX - 1][coordY] : 0;
    }

    private int rightNeighbor(final int coordX, final int coordY) {
        return (coordX != sizeX - 1) ? grid[coordX + 1][coordY] : 0;
    }

    private int upperNeighbor(final int coordX, final int coordY) {
        return (coordX != 0) ? grid[coordX - 1][coordY] : 0;
    }

    private int lowerNeighbor(final int coordX, final int coordY) {
        return (coordY != sizeY - 1) ? grid[coordX][coordY + 1] : 0;
    }

    private int leftUpperNeighbor(final int coordX, final int coordY) {
        return (coordX != 0 && coordY != 0)
                ? grid[coordX - 1][coordY - 1] : 0;
    }

    private int rightUpperNeighbor(final int coordX, final int coordY) {
        return (coordX != sizeX - 1 && coordY != 0)
                ? grid[coordX + 1][coordY - 1] : 0;
    }

    private int leftLowerNeighbor(final int coordX, final int coordY) {
        return (coordX != 0 && coordY != sizeY - 1)
                ? grid[coordX - 1][coordY + 1] : 0;
    }

    private int rightLowerNeighbor(final int coordX, final int coordY) {
        return (coordX != sizeX - 1 && coordY != sizeY - 1)
                ? grid[coordX + 1][coordY + 1] : 0;
    }
}
