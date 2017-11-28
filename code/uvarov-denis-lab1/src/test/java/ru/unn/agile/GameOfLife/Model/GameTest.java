package ru.unn.agile.GameOfLife.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectInput() {
        String[] inputArray = {"", ""};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectMapSizeByYValue() {
        String[] inputArray = {"1 4", ".", "."};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectMapSizeByXValue() {
        String[] inputArray = {"2 3", ".", ".", "."};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectStringLength() {
        String[] inputArray = {"2 3", "..", "...", ".."};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
    }

    @Test
    public void shouldShouldParseCorrectInput() {
        String[] inputArray = {"2 3", "..", "..", ".."};
        int[][] correctGrid = {{0, 0}, {0, 0}, {0, 0}};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
        assertEquals(testGame.GetCurrentGeneration().GetGrid()[1], 2);
        assertEquals(testGame.GetCurrentGeneration().GetGrid()[2], 3);
        assertTrue(MapTest.IsEqualsGrids(testGame.GetCurrentGeneration().GetGrid(), correctGrid));
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotReturnNotCreatedNextGeneration() {
        GameOfLife testGame = new GameOfLife();
        testGame.GetNextGeneration();
    }

    @Test
    public void shouldCreateCorrectNextGenerarionGrid() {
        String[] inputArray = {"2 3", "..", "*.", ".."};
        int[][] correctGrid = {{0, 0}, {0, 0}, {0, 0}};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
        testGame.BuildNextGeneration();

        assertEquals(testGame.GetCurrentGeneration().GetGrid()[1], 2);
        assertEquals(testGame.GetCurrentGeneration().GetGrid()[2], 3);
        assertTrue(MapTest.IsEqualsGrids(testGame.GetNextGeneration().GetGrid(), correctGrid));
    }

    @Test
    public void shouldCreateCorrectOutputValue() {
        String[] inputArray = {"2 3", "..", "*.", ".."};
        String[] correctOutputArray = {"2 3", "..", "..", ".."};
        GameOfLife testGame = new GameOfLife();
        testGame.ReadCurrentGeneration(inputArray);
        testGame.BuildNextGeneration();
        String[] outputArray = testGame.WriteNextGeneration();
        assertTrue(IsEqualsStrings(correctOutputArray, outputArray));
    }

    public static boolean IsEqualsStrings(String[] firstArray, String[] secondArray) {
        boolean result = true;
        try {
            for (int i = 0; i < firstArray.length; i++) {
                result = result && firstArray[i].equals(secondArray[i]);
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }
}
