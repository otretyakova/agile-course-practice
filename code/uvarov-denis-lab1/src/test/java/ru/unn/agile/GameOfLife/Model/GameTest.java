package ru.unn.agile.GameOfLife.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectInput() {
        String[] inputArray = {"", ""};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectMapSizeByYValue() {
        String[] inputArray = {"1 4", ".", "."};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectMapSizeByXValue() {
        String[] inputArray = {"2 3", ".", ".", "."};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void couldNotWorkWithIncorrectStringLength() {
        String[] inputArray = {"2 3", "..", "...", ".."};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
    }

    @Test
    public void shouldShouldParseCorrectInput() {
        String[] inputArray = {"2 3", "..", "..", ".."};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
        assertEquals(testGame.getCurrentGeneration().getSizeX(), 2);
        assertEquals(testGame.getCurrentGeneration().getSizeY(), 3);
    }

    @Test(expected = NullPointerException.class)
    public void couldNotReturnNotCreatedNextGeneration() {
        GameOfLife testGame = new GameOfLife();
        testGame.getNextGeneration();
    }

    @Test
    public void shouldCreateCorrectNextGenerarionGrid() {
        String[] inputArray = {"2 3", "..", "*.", ".."};
        int[][] correctGrid = {{0, 0, 0}, {0, 0, 0}};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
        testGame.buildNextGeneration();

        assertEquals(testGame.getCurrentGeneration().getSizeX(), 2);
        assertEquals(testGame.getCurrentGeneration().getSizeY(), 3);
        assertTrue(MapTest.isEqualsGrids(testGame.getNextGeneration().getGrid(), correctGrid));
    }

    @Test
    public void shouldCreateCorrectOutputValue() {
        String[] inputArray = {"2 3", "..", "*.", ".."};
        String[] correctOutputArray = {"2 3", "..", "..", ".."};
        GameOfLife testGame = new GameOfLife();
        testGame.readCurrentGeneration(inputArray);
        testGame.buildNextGeneration();
        String[] outputArray = testGame.writeNextGeneration();
        assertTrue(isEqualsStrings(correctOutputArray, outputArray));
    }

    private static boolean isEqualsStrings(final String[] firstArray, final String[] secondArray) {
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
