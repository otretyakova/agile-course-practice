package ru.unn.agile.LTree.Model;

import org.junit.Test;
import ru.unn.agile.LTree.Model.LTree.KeyValuePair;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LTreeTests {

    private Random rand = new Random(777);

    ArrayList<KeyValuePair<Integer>> prepareInitList(final int numberElements) {
        ArrayList<KeyValuePair<Integer>>  array = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            int rndKey = Math.abs(rand.nextInt()) % (numberElements * 2);
            int rndValue = rand.nextInt();
            array.add(new KeyValuePair<>(rndKey, rndValue));
        }
        return array;
    }

    ArrayList<KeyValuePair<Integer>> addRandomElements(
            final LTree<Integer> tree, final int numberElements) {
        ArrayList<KeyValuePair<Integer>> addedElements = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            int rndKey = Math.abs(rand.nextInt() * rand.nextInt()) % 10000000;
            int rndValue = rand.nextInt();
            addedElements.add((new KeyValuePair<>(rndKey, rndValue)));
            tree.add(new KeyValuePair<>(rndKey, rndValue));
        }
        return addedElements;
    }

    void removeRandomElements(final LTree<Integer> tree,
        final ArrayList<KeyValuePair<Integer>> addedElements, final int numberElements) {
        for (int i = 0; i < numberElements; i++) {
            int indexToRemove = Math.abs(rand.nextInt()) % addedElements.size();
            KeyValuePair<Integer> shouldBeRemovedElement = addedElements.remove(indexToRemove);
            KeyValuePair<Integer> realWasRemovedElement =
                    tree.remove(shouldBeRemovedElement.getKey());
            assertTrue(realWasRemovedElement.compareWith(shouldBeRemovedElement));
        }
    }

    LTree<Integer> createRandTreeWithExpectedMin(
            final int numberElements, final KeyValuePair<Integer> expectedMin) {
        LTree<Integer> tree = new LTree<>();
        for (int i = 0; i < numberElements - 1; i++) {
            int rndKey = Math.abs(rand.nextInt() % numberElements)
                    + 2 * Math.abs(expectedMin.getKey());
            int rndValue = rand.nextInt();
            tree.add(new KeyValuePair<>(rndKey, rndValue));
        }
        tree.add(expectedMin);
        return tree;
    }

    @Test
    public void canCreateEmptyTree() {
        LTree<Integer> tree = new LTree<>();
        assertTrue(tree.size() == 0);
    }

    @Test
    public void canCreateEmptyTreeFromNullArray() {
        LTree<Integer> tree = new LTree<>(null);
        assertTrue(tree.size() == 0);
    }

    @Test
    public void canCreateEmptyTreeFromEmptyArray() {
        LTree<Integer> tree = new LTree<>(prepareInitList(0));
        assertTrue(tree.size() == 0);
    }

    @Test
    public void canCreateTreeWith3Elements() {
        LTree<Integer> tree = new LTree<>(prepareInitList(3));
        assertTrue(tree.size() == 3);
    }

    @Test
    public void canAdd10ElementsToEmptyTree() {
        LTree<Integer> tree = new LTree<>();
        addRandomElements(tree, 10);
        assertTrue(tree.size() == 10);
    }

    @Test
    public void canAdd10ElementsToTreeWith6Elements() {
        LTree<Integer> tree = new LTree<>(prepareInitList(6));
        addRandomElements(tree, 10);
        assertTrue(tree.size() == 16);
    }

    @Test
    public void canAdd2EqualedElements() {
        LTree<Integer> tree = new LTree<>();
        tree.add(new KeyValuePair<>(1, 2));
        tree.add(new KeyValuePair<>(1, 2));
        assertTrue(tree.size() == 2);
    }

    @Test
    public void cantRemoveElementFromEmptyTree() {
        LTree<Integer> tree = new LTree<>();
        assertTrue(tree.remove(0) == null);
    }

    @Test
    public void cantRemoveUnexistedElement() {
        LTree<Integer> tree = createRandTreeWithExpectedMin(15, new KeyValuePair<>(3, 10));
        assertTrue(tree.remove(2) == null);
        assertTrue(tree.size() == 15);
    }

    @Test
    public void cantRemoveUnicalElementTwice() {
        LTree<Integer> tree = createRandTreeWithExpectedMin(4, new KeyValuePair<>(3, 10));
        tree.add(new KeyValuePair<>(1, 2));
        assertTrue(tree.remove(1).compareWith(new KeyValuePair<>(1, 2)));
        assertTrue(tree.remove(1) == null);
        assertTrue(tree.size() == 4);
    }

    @Test
    public void canAdd3ElementsAndRemove3Elements() {
        LTree<Integer> tree = new LTree<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addRandomElements(tree, 3);
        removeRandomElements(tree, addedElements, 3);
        assertTrue(tree.size() == 0);
    }

    @Test
    public void canAdd5ElementsAndRemove3Elements() {
        LTree<Integer> tree = new LTree<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addRandomElements(tree, 5);
        removeRandomElements(tree, addedElements, 3);
        assertTrue(tree.size() == 2);
    }

    @Test
    public void cantAdd5ElementsAndRemove6Elements() {
        LTree<Integer> tree = new LTree<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addRandomElements(tree, 5);
        removeRandomElements(tree, addedElements, 5);
        assertTrue(tree.remove(0) == null);
    }

    @Test
    public void cantGetMinFromEmptyTree() {
        LTree<Integer> tree = new LTree<>();
        assertTrue(tree.getMin() == null);
    }

    @Test
    public void canGetMinimumCorrectly() {
        LTree<Integer> tree = createRandTreeWithExpectedMin(15, new KeyValuePair<>(3, 10));
        KeyValuePair<Integer> key = tree.getMin();
        assertTrue(tree.getMin().compareWith(new KeyValuePair<>(3, 10)));
    }

    @Test
    public void canGetNewMinAfterDeletePreviousMin() {
        LTree<Integer> tree = createRandTreeWithExpectedMin(15, new KeyValuePair<>(3, 10));
        tree.add(new KeyValuePair<>(4, 31));
        assertTrue(tree.remove(3).compareWith(new KeyValuePair<>(3, 10)));
        assertTrue(tree.getMin().compareWith(new KeyValuePair<>(4, 31)));
    }

    @Test
    public void canGetNewMinAfterAddIt() {
        LTree<Integer> tree = createRandTreeWithExpectedMin(15, new KeyValuePair<>(3, 10));
        tree.add(new KeyValuePair<>(1, 15));
        assertTrue(tree.getMin().compareWith(new KeyValuePair<>(1, 15)));
    }

    @Test
    public void cantMergeTreeWithNull() {
        LTree<Integer> tree1 = new LTree<>(prepareInitList(5));
        LTree<Integer> tree2 = null;
        assertFalse(tree1.merge(tree2));
        assertTrue(tree1.size() == 5);
    }

    @Test
    public void canMergeEmptyTreeWithNotEmpty() {
        LTree<Integer> tree1 = new LTree<>();
        LTree<Integer> tree2 = new LTree<>(prepareInitList(5));
        final KeyValuePair<Integer> expectedMin = tree2.getMin();
        assertTrue(tree1.merge(tree2));
        assertTrue(tree1.size() == 5);
        assertTrue(tree1.getMin().compareWith(expectedMin));
    }

    @Test
    public void canMergeNotEmptyTreeWithEmpty() {
        LTree<Integer> tree1 = new LTree<>(prepareInitList(5));
        LTree<Integer> tree2 = new LTree<>();
        final KeyValuePair<Integer> expectedMin = tree1.getMin();
        assertTrue(tree1.merge(tree2));
        assertTrue(tree1.size() == 5);
        assertTrue(tree1.getMin().compareWith(expectedMin));
    }

    @Test
    public void canMergeTwoTrees() {
        LTree<Integer> tree1 = createRandTreeWithExpectedMin(11, new KeyValuePair<>(4, 11));
        LTree<Integer> tree2 = createRandTreeWithExpectedMin(19, new KeyValuePair<>(5, 12));
        assertTrue(tree1.merge(tree2));
        assertTrue(tree1.size() == 30);
        assertTrue(tree1.getMin().compareWith(new KeyValuePair<>(4, 11)));
    }
}
