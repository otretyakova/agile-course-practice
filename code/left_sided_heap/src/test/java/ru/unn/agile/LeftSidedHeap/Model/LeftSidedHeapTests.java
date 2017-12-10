package ru.unn.agile.LeftSidedHeap.Model;

import org.junit.Assert;
import org.junit.Test;
import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeftSidedHeapTests {

    @Test
    public void canCreateEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        assertFalse(heap.size() != 0);
    }

    @Test
    public void canCreateEmptyHeapFromEmptyArray() {
        LeftSidedHeap<Integer> heap = prepareHeap(0);
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canCreateHeapWith10Elements() {
        ArrayList<SimpleEntry<Integer, Integer>> initArray =
                getArrayWithPositiveKeysSize10andMinKey0Value1();
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(initArray);
        assertTrue(heap.contains(initArray));
        assertTrue(heap.size() == 10);
    }

    @Test
    public void canAdd10ElementsToEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 10);
        assertTrue(heap.contains(addedElements));
        assertTrue(heap.size() == 10);
    }

    @Test
    public void canAdd10ElementsToHeapWith6Elements() {
        LeftSidedHeap<Integer> heap = prepareHeap(6);
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 10);
        assertTrue(heap.contains(addedElements));
        assertTrue(heap.size() == 16);
    }

    @Test
    public void canAdd2EqualedElements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        heap.add(new SimpleEntry<>(1, 2));
        heap.add(new SimpleEntry<>(1, 2));
        assertTrue(heap.size() == 2);
    }

    @Test
    public void cantFindElementInEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        assertTrue(heap.search(4) == null);
    }

    @Test
    public void cantFindUnexistedElement() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        assertTrue(heap.search(-1) == null);
    }

    @Test
    public void canFindExistedElement() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        assertTrue(heap.search(0).equals(new SimpleEntry<>(0, 1)));
    }

    @Test
    public void canContainExistedElements() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1(123);
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 5);
        assertTrue(heap.contains(addedElements));
    }

    @Test
    public void cantContainUnexistedElements() {
        LeftSidedHeap<Integer> heap = prepareHeap(5);
        ArrayList<SimpleEntry<Integer, Integer>> randArray = new ArrayList<>();
        randArray.add(new SimpleEntry<>(-2, 3));
        randArray.add(new SimpleEntry<>(33, 41));
        assertFalse(heap.contains(randArray));
    }

    @Test
    public void canDoContainForEmptyHeap() {
        boolean thrown = true;
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        try {
            heap.contains(getArrayWithPositiveKeysSize10andMinKey0Value1());
        } catch (Exception ex) {
            thrown = false;
        }
        assertTrue(thrown);
    }

    @Test
    public void cantRemoveElementFromEmptyHeap() {
        boolean thrown = false;
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        try {
            heap.remove(0);
        } catch (IllegalStateException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void cantRemoveUnexistedElement() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        assertTrue(heap.remove(-1) == null);
        assertTrue(heap.size() == 10);
    }

    @Test
    public void cantRemoveUniqueElementTwice() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new SimpleEntry<>(-1, 4));
        heap.remove(-1).equals(new SimpleEntry<>(-1, 4));
        assertTrue(heap.remove(-1) == null);
    }

    @Test
    public void canAdd3ElementsAndRemove3Elements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 3);
        removeRandomElements(heap, addedElements, 3);
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canAdd5ElementsAndRemove3Elements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 5);
        removeRandomElements(heap, addedElements, 3);
        assertTrue(heap.size() == 2);
    }

    @Test
    public void cantAdd5ElementsAndRemove6Elements() {
        boolean thrown = false;
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<SimpleEntry<Integer, Integer>> addedElements = addElements(heap, 5);
        removeRandomElements(heap, addedElements, 5);
        try {
            heap.remove(0);
        } catch (IllegalStateException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void cantGetMinFromEmptyHeap() {
        boolean thrown = false;
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        try {
            heap.getMin();
        } catch (IllegalStateException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void canGetMinimumCorrectly() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        assertTrue(heap.getMin().equals(new SimpleEntry<>(0, 1)));
    }

    @Test
    public void canGetNewMinAfterDeletePreviousMin() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new SimpleEntry<>(-2, 1));
        heap.add(new SimpleEntry<>(-1, 5));
        assertTrue(heap.remove(-2).equals(new SimpleEntry<>(-2, 1)));
        assertTrue(heap.getMin().equals(new SimpleEntry<>(-1, 5)));
    }

    @Test
    public void canGetNewMinAfterAddIt() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new SimpleEntry<>(-1, -2));
        assertTrue(heap.getMin().equals(new SimpleEntry<>(-1, -2)));
    }

    @Test
    public void cantMergeHeapWithNull() {
        LeftSidedHeap<Integer> mainHeap = prepareHeap(5);
        LeftSidedHeap<Integer> nullHeap = null;
        assertFalse(mainHeap.merge(nullHeap));
    }

    @Test
    public void canMergeEmptyHeapWithNotEmpty() {
        LeftSidedHeap<Integer> firstHeap = new LeftSidedHeap<>();
        LeftSidedHeap<Integer> secondHeap = prepareHeap(5);
        assertTrue(firstHeap.merge(secondHeap));
        assertTrue(firstHeap.size() == 5);
    }

    @Test
    public void canMergeNotEmptyHeapWithEmpty() {
        LeftSidedHeap<Integer> mainHeap = prepareHeap(6);
        LeftSidedHeap<Integer> emptyHeap = new LeftSidedHeap<>();
        assertTrue(mainHeap.merge(emptyHeap));
        assertTrue(mainHeap.size() == 6);
    }

    @Test
    public void canMergeTwoHeaps() {
        ArrayList<SimpleEntry<Integer, Integer>> firstHeapElements
                = getArrayWithPositiveKeysSize10andMinKey0Value1(0);
        ArrayList<SimpleEntry<Integer, Integer>> secondHeapElements
                = getArrayWithPositiveKeysSize10andMinKey0Value1(100);
        LeftSidedHeap<Integer> firstHeap = new LeftSidedHeap<>(firstHeapElements);
        LeftSidedHeap<Integer> secondHeap = new LeftSidedHeap<>(secondHeapElements);
        assertTrue(firstHeap.merge(secondHeap));
        assertTrue(firstHeap.contains(firstHeapElements) && firstHeap.contains(secondHeapElements));
    }

    private ArrayList<SimpleEntry<Integer, Integer>>
        getArrayWithPositiveKeysSize10andMinKey0Value1(final int offset) {
        ArrayList<SimpleEntry<Integer, Integer>> array = new ArrayList<>();
        array.add(new SimpleEntry<>(offset + 3, 10));
        array.add(new SimpleEntry<>(offset + 15, -4));
        array.add(new SimpleEntry<>(offset + 5, 12));
        array.add(new SimpleEntry<>(offset + 6, 3));
        array.add(new SimpleEntry<>(offset + 1, -30));
        array.add(new SimpleEntry<>(offset + 0, 1));
        array.add(new SimpleEntry<>(offset + 2, 9));
        array.add(new SimpleEntry<>(offset + 93, 11));
        array.add(new SimpleEntry<>(offset + 9, -51));
        array.add(new SimpleEntry<>(offset + 42, -10));
        return array;
    }

    private ArrayList<SimpleEntry<Integer, Integer>>
        getArrayWithPositiveKeysSize10andMinKey0Value1() {
        return getArrayWithPositiveKeysSize10andMinKey0Value1(0);
    }

    private LeftSidedHeap<Integer>
        createHeapWithPositiveKeysSize10andMinKey0Value1(final int offset) {
        ArrayList<SimpleEntry<Integer, Integer>> array =
                getArrayWithPositiveKeysSize10andMinKey0Value1(offset);
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(array);
        return heap;
    }

    private LeftSidedHeap<Integer> createHeapWithPositiveKeysSize10andMinKey0Value1() {
        return createHeapWithPositiveKeysSize10andMinKey0Value1(0);
    }

    private LeftSidedHeap<Integer> prepareHeap(
            final int numElements) {
        ArrayList<SimpleEntry<Integer, Integer>> array =
                getArrayWithPositiveKeysSize10andMinKey0Value1();
        if (numElements <= array.size()) {
            int extraNumElements = array.size() - numElements;
            for (int i = 0; i < extraNumElements; i++) {
                array.remove(0);
            }
        } else {
            Assert.fail("This function can create not more than 10 elements!");
        }
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(array);
        return heap;
    }

    private ArrayList<SimpleEntry<Integer, Integer>> addElements(
            final LeftSidedHeap<Integer> heap, final int numElements) {
        ArrayList<SimpleEntry<Integer, Integer>> tempArray =
                getArrayWithPositiveKeysSize10andMinKey0Value1();
        ArrayList<SimpleEntry<Integer, Integer>> resultArray = new ArrayList<>();
        if (numElements <= tempArray.size()) {
            for (int i = 0; i < numElements; i++) {
                heap.add(tempArray.get(i));
                resultArray.add(tempArray.get(i));
            }
        } else {
            Assert.fail("This function can add not more than 10 elements!");
        }
        return resultArray;
    }

    private void removeRandomElements(final LeftSidedHeap<Integer> heap,
        final ArrayList<SimpleEntry<Integer, Integer>> addedElements, final int numberElements)
            throws IllegalStateException {
        final int rndSeed = 777;
        final Random rand = new Random(rndSeed);
        for (int i = 0; i < numberElements; i++) {
            int indexToRemove = Math.abs(rand.nextInt()) % heap.size();
            SimpleEntry<Integer, Integer> shouldBeRemovedElement =
                    addedElements.remove(indexToRemove);
            SimpleEntry<Integer, Integer> wasRemovedElement =
                    heap.remove(shouldBeRemovedElement.getKey());
            assertTrue(wasRemovedElement.equals(shouldBeRemovedElement));
        }
    }
}
