package ru.unn.agile.GameOfLife.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Test(expected = IllegalArgumentException.class)
    public void couldNotCreateMapWithIncorrectGrid() {
        int[][] grid = {{}};
        Map testMap = new Map(grid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotCreateMapWithIncorrectGridFormat() {
        int[][] grid = {{0, 0}, {0, 0, 0}};
        Map testMap = new Map(grid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotCreateMapWithIncorrectGridValues() {
        int[][] grid = {{5, 0}, {0, 0}};
        Map testMap = new Map(grid);
    }

    @Test
    public void shouldCreateMapWithInputParameters() {
        int[][] grid = {{1, 0}, {0, 0}, {0, 1}};
        Map testMap = new Map(grid);
        assertEquals(testMap.GetSize()[1], 2);
        assertEquals(testMap.GetSize()[2], 3);
        assertTrue(IsEqualsGrids(grid, testMap.GetGrid()));
    }

    public static boolean IsEqualsGrids(int[][] firstGrid, int[][] secondGrid) {
        boolean result = true;
        try {
            for (int i = 0; i < firstGrid.length; i++) {
                for (int j = 0; i < firstGrid[1].length; i++) {
                    result = result && (firstGrid[i][j] == secondGrid[i][j]);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    @Test
    public void shouldReturnZeroForDeadGrid() {
        int[][] grid = {{0}};
        Map testMap = new Map(grid);
        assertEquals(testMap.CountAliveNeighbors(0, 0), 0);
    }

    @Test
    public void shouldReturnCorrectValueForAliveNeighbors() {
        int[][] grid = {{0, 1}, {1, 1}};
        Map testMap = new Map(grid);
        assertEquals(testMap.CountAliveNeighbors(0, 0), 3);
    }
}
