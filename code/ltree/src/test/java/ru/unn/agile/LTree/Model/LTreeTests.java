package ru.unn.agile.LTree.Model;

import org.junit.Assert;
import org.junit.Test;
import ru.unn.agile.LTree.Model.LeftSidedHeap.KeyValuePair;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.unn.agile.LTree.Model.LeftSidedHeap.*;

public class LeftSidedHeapTests {

    @Test
    public void canCreateEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canCreateEmptyHeapFromNullArray() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(null);
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canCreateEmptyHeapFromEmptyArray() {
        LeftSidedHeap<Integer> heap = prepareHeap(0);
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canCreateHeapWith3Elements() {
        LeftSidedHeap<Integer> heap = prepareHeap(3);
        assertTrue(heap.size() == 3);
    }

    @Test
    public void canAdd10ElementsToEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        addElements(heap, 10);
        assertTrue(heap.size() == 10);
    }

    @Test
    public void canAdd10ElementsToHeapWith6Elements() {
        LeftSidedHeap<Integer> heap = prepareHeap(6);
        addElements(heap, 10);
        assertTrue(heap.size() == 16);
    }

    @Test
    public void canAdd2EqualedElements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        heap.add(new KeyValuePair<>(1, 2));
        heap.add(new KeyValuePair<>(1, 2));
        assertTrue(heap.size() == 2);
    }

    @Test
    public void cantRemoveElementFromEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        try {
            heap.remove(0);
            Assert.fail();
        } catch (EmptyHeapException ex) {
        }
    }

    @Test
    public void cantRemoveUnexistedElement() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        try {
            assertTrue(heap.remove(-1) == null);
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
        assertTrue(heap.size() == 10);
    }

    @Test
    public void cantRemoveUniqueElementTwice() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new KeyValuePair<>(-1, 4));
        try {
            assertTrue(heap.remove(-1).compareWith(new KeyValuePair<>(-1, 4)));
            assertTrue(heap.remove(-1) == null);
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
        assertTrue(heap.size() == 10);
    }

    @Test
    public void canAdd3ElementsAndRemove3Elements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addElements(heap, 3);
        try {
            removeRandomElements(heap, addedElements, 3);
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
        assertTrue(heap.size() == 0);
    }

    @Test
    public void canAdd5ElementsAndRemove3Elements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addElements(heap, 5);
        try {
            removeRandomElements(heap, addedElements, 3);
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
        assertTrue(heap.size() == 2);
    }

    @Test
    public void cantAdd5ElementsAndRemove6Elements() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        ArrayList<KeyValuePair<Integer>> addedElements = addElements(heap, 5);
        try {
            removeRandomElements(heap, addedElements, 5);
            heap.remove(0);
            Assert.fail();
        } catch (EmptyHeapException ex) {
        }
    }

    @Test
    public void cantGetMinFromEmptyHeap() {
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>();
        try {
            heap.getMin();
            Assert.fail();
        } catch (EmptyHeapException ex) {
        }
    }

    @Test
    public void canGetMinimumCorrectly() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        try {
            assertTrue(heap.getMin().compareWith(new KeyValuePair<>(0, 1)));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    @Test
    public void canGetNewMinAfterDeletePreviousMin() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new KeyValuePair<>(-2, 1));
        heap.add(new KeyValuePair<>(-1, 5));
        try {
            assertTrue(heap.remove(-2).compareWith(new KeyValuePair<>(-2, 1)));
            assertTrue(heap.getMin().compareWith(new KeyValuePair<>(-1, 5)));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    @Test
    public void canGetNewMinAfterAddIt() {
        LeftSidedHeap<Integer> heap = createHeapWithPositiveKeysSize10andMinKey0Value1();
        heap.add(new KeyValuePair<>(-1, -2));
        try {
            assertTrue(heap.getMin().compareWith(new KeyValuePair<>(-1, -2)));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    @Test
    public void cantMergeHeapWithNull() {
        LeftSidedHeap<Integer> heap1 = prepareHeap(5);
        LeftSidedHeap<Integer> heap2 = null;
        assertFalse(heap1.merge(heap2));
        assertTrue(heap1.size() == 5);
    }

    @Test
    public void canMergeEmptyHeapWithNotEmpty() {
        LeftSidedHeap<Integer> heap1 = new LeftSidedHeap<>();
        LeftSidedHeap<Integer> heap2 = prepareHeap(5);
        try {
            final KeyValuePair<Integer> expectedMin = heap2.getMin();
            assertTrue(heap1.merge(heap2));
            assertTrue(heap1.size() == 5);
            assertTrue(heap1.getMin().compareWith(expectedMin));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    @Test
    public void canMergeNotEmptyHeapWithEmpty() {
        LeftSidedHeap<Integer> heap1 = prepareHeap(6);
        LeftSidedHeap<Integer> heap2 = new LeftSidedHeap<>();
        try {
            final KeyValuePair<Integer> expectedMin = heap1.getMin();
            assertTrue(heap1.merge(heap2));
            assertTrue(heap1.size() == 6);
            assertTrue(heap1.getMin().compareWith(expectedMin));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    @Test
    public void canMergeTwoHeaps() {
        LeftSidedHeap<Integer> heap1 = createHeapWithPositiveKeysSize10andMinKey0Value1();
        LeftSidedHeap<Integer> heap2 = createHeapWithPositiveKeysSize10andMinKey0Value1(100);
        assertTrue(heap1.merge(heap2));
        assertTrue(heap1.size() == 20);
        try {
            assertTrue(heap1.getMin().compareWith(new KeyValuePair<>(0, 1)));
        } catch (EmptyHeapException ex) {
            Assert.fail();
        }
    }

    private ArrayList<KeyValuePair<Integer>>
        getArrayWithPositiveKeysSize10andMinKey0Value1(final int offset) {
        ArrayList<KeyValuePair<Integer>> array = new ArrayList<>();
        array.add(new KeyValuePair<Integer>(offset + 3, 10));
        array.add(new KeyValuePair<Integer>(offset + 15, -4));
        array.add(new KeyValuePair<Integer>(offset + 5, 12));
        array.add(new KeyValuePair<Integer>(offset + 6, 3));
        array.add(new KeyValuePair<Integer>(offset + 1, -30));
        array.add(new KeyValuePair<Integer>(offset + 0, 1));
        array.add(new KeyValuePair<Integer>(offset + 2, 9));
        array.add(new KeyValuePair<Integer>(offset + 93, 11));
        array.add(new KeyValuePair<Integer>(offset + 9, -51));
        array.add(new KeyValuePair<Integer>(offset + 42, -10));
        return array;
    }

    private ArrayList<KeyValuePair<Integer>> getArrayWithPositiveKeysSize10andMinKey0Value1() {
        return getArrayWithPositiveKeysSize10andMinKey0Value1(0);
    }

    private LeftSidedHeap<Integer>
        createHeapWithPositiveKeysSize10andMinKey0Value1(final int offset) {
        ArrayList<KeyValuePair<Integer>> array =
                getArrayWithPositiveKeysSize10andMinKey0Value1(offset);
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(array);
        return heap;
    }

    private LeftSidedHeap<Integer> createHeapWithPositiveKeysSize10andMinKey0Value1() {
        return createHeapWithPositiveKeysSize10andMinKey0Value1(0);
    }

    private LeftSidedHeap<Integer> prepareHeap(
            final int numElements) {
        ArrayList<KeyValuePair<Integer>> array = getArrayWithPositiveKeysSize10andMinKey0Value1();
        if (numElements <= array.size()) {
            int extraNumElements = array.size() - numElements;
            for (int i = 0; i < extraNumElements; i++) {
                array.remove(0);
            }
        } else {
            Assert.fail();
        }
        LeftSidedHeap<Integer> heap = new LeftSidedHeap<>(array);
        return heap;
    }

    private ArrayList<KeyValuePair<Integer>> addElements(
            final LeftSidedHeap<Integer> heap, final int numElements) {
        ArrayList<KeyValuePair<Integer>> array = getArrayWithPositiveKeysSize10andMinKey0Value1();
        if (numElements <= array.size()) {
            for (int i = 0; i < numElements; i++) {
                heap.add(array.get(i));
            }
        } else {
            Assert.fail();
        }
        return array;
    }

    private void removeRandomElements(final LeftSidedHeap<Integer> heap,
        final ArrayList<KeyValuePair<Integer>> addedElements, final int numberElements)
            throws EmptyHeapException {
        final int rndSeed = 777;
        final Random rand = new Random(rndSeed);
        for (int i = 0; i < numberElements; i++) {
            int indexToRemove = Math.abs(rand.nextInt()) % heap.size();
            KeyValuePair<Integer> shouldBeRemovedElement = addedElements.remove(indexToRemove);
            KeyValuePair<Integer> wasRemovedElement = heap.remove(shouldBeRemovedElement.getKey());
            assertTrue(wasRemovedElement.compareWith(shouldBeRemovedElement));
        }
    }
}
