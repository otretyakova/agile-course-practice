package ru.unn.agile.GameOfLife.Model;

public class Map {
    private int sizeX;
    private int sizeY;
    private int[][] grid;

    public Map(final int[][] grid) {
        if (grid.length < 1) {
            throw new IllegalArgumentException();
        }
        this.sizeY = grid.length;

        boolean isValidLength = true;
        for (int i = 1; i < sizeY; i++) {
            isValidLength = isValidLength && (grid[0].length == grid[i].length);
        }
        if (!isValidLength || grid[0].length < 0) {
            throw new IllegalArgumentException();
        }
        this.sizeX = grid[0].length;

        this.grid = grid;
    }

    public int CountAliveNeighbors(int coordX, int coordY) {
        int result = 0;
        
        int minX = 0;
        int minY = 0;
        int maxX = sizeX - 1;
        int maxY = sizeY - 1;

        if (coordX != minX) { // Middle Left
            result += grid[coordY][coordX - 1];
        }
        if (coordX != maxX) { // Middle Right
            result += grid[coordY][coordX + 1];
        }
        if (coordY != minY) { // Upper Middle
            result += grid[coordY - 1][coordX];
        }
        if (coordY != maxY) { // Lower Middle
            result += grid[coordY + 1][coordX];
        }

        if (coordX != minX && coordY != minY) { // Left Upper corner
            result += grid[coordY - 1][coordX - 1];
        }
        if (coordX != maxX && coordY != minY) { // Right Upper corner
            result += grid[coordY - 1][coordX + 1];
        }
        if (coordX != minX && coordY != maxY) { // Left Lower corner
            result += grid[coordY + 1][coordX + 1];
        }
        if (coordX != maxX && coordY != maxY) { // Right Lower corner
            result += grid[coordY + 1][coordX + 1];
        }

        return result;
    }

    public int[] GetSize() {
        int[] retValue = {sizeX, sizeY};
        return retValue;
    }

    public int[][] GetGrid() {
        return grid;
    }

    @Override
    public boolean equals(final Object object) {
        //Verify that all fields equals
        return false;
    }
}
