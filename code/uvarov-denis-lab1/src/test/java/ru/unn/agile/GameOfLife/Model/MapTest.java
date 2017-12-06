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
        int[][] grid = {{1, 0, 0}, {0, 1, 0}};
        int[] gridSize = {2, 3};
        Map testMap = new Map(grid);
        assertArrayEquals(testMap.getSize(), gridSize);
        assertEquals(testMap.getSizeX(), 2);
        assertEquals(testMap.getSizeY(), 3);
        assertTrue(isEqualsGrids(grid, testMap.getGrid()));
    }

    public static boolean isEqualsIntArrays(final int[] firstArray, final int[] secondArray) {
        boolean result = true;
        try {
            for (int i = 0; i < firstArray.length; i++) {
                result = result && (firstArray[i] == secondArray[i]);
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    public static boolean isEqualsGrids(final int[][] firstGrid, final int[][] secondGrid) {
        boolean result = true;
        try {
            for (int i = 0; i < firstGrid.length; i++) {
                result = result && isEqualsIntArrays(firstGrid[i], secondGrid[i]);
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
        assertEquals(testMap.countAliveNeighbors(0, 0), 0);
    }

    @Test
    public void shouldReturnCorrectValueForAliveNeighbors() {
        int[][] grid = {{0, 1}, {1, 1}};
        Map testMap = new Map(grid);
        assertEquals(testMap.countAliveNeighbors(0, 0), 3);
    }
}
