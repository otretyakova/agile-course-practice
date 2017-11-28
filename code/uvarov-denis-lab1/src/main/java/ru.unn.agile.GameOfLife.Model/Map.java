package ru.unn.agile.GameOfLife.Model;

public class Map {
    private int sizeX;
    private int sizeY;
    private int[][] grid;

    public Map(final int [][] grid) {
        // Simple constructor
    }

    public int CountAliveNeighbors(int coordX, int coordY) {
        // Count of alive Neighbors
        return 0;
    }

    public int[] GetSize() {
        // Size of the map
        int[] retValue = {0,0};
        return retValue;
    }
    public int[][] GetGrid() {
        // Grid in binary view
        int[][] retValue = {{0,0},{0,0}};
        return retValue;
    }

    @Override
    public boolean equals(final Object object) {
        //Verify that all fields equals
        return false;
    }
}
