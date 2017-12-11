package ru.unn.agile.GameOfLife.Model;

public class Map {
    public Map(final int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (grid.length < 1) {
            throw new IllegalArgumentException("Cannot create MAP: Empty grid!");
        }
        this.sizeX = grid.length;

        boolean isValidLength = true;

        for (int x = 1; x < sizeX; x++) {
            isValidLength = isValidLength && (grid[0].length == grid[x].length);
        }
        if (!isValidLength || grid[0].length < 1) {
            throw new IllegalArgumentException("Incorrect grid size!");
        }
        this.sizeY = grid[0].length;
        boolean isValidValues = true;
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                isValidValues = isValidValues && grid[x][y] >= 0 && grid[x][y] <= 1;
            }
        }
        if (isValidValues) {
            this.grid = grid;
        } else {
            throw new IllegalArgumentException("Grid has incorrect states!");
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
        if (coordX < getSizeX() && coordY < getSizeY()
                && coordX >= 0 && coordY >= 0) {
            return leftNeighbor(coordX, coordY) + rightNeighbor(coordX, coordY)
                    + upperNeighbor(coordX, coordY) + lowerNeighbor(coordX, coordY)
                    + leftUpperNeighbor(coordX, coordY) + rightUpperNeighbor(coordX, coordY)
                    + leftLowerNeighbor(coordX, coordY) + rightLowerNeighbor(coordX, coordY);
        } else {
            throw new IllegalArgumentException("Incorrect coordinates of point!");
        }
    }

    private int leftNeighbor(final int coordX, final int coordY) {
        return (coordX != 0) ? grid[coordX - 1][coordY] : 0;
    }

    private int rightNeighbor(final int coordX, final int coordY) {
        return (coordX != sizeX - 1) ? grid[coordX + 1][coordY] : 0;
    }

    private int upperNeighbor(final int coordX, final int coordY) {
        return (coordY != 0) ? grid[coordX][coordY - 1] : 0;
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

    private int sizeX;
    private int sizeY;
    private int[][] grid;
}
