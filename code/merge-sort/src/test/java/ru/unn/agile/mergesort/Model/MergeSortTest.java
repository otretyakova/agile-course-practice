import ru.unn.agile.mergesort.Model.MergeSort;
import org.junit.Test;
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void emptyArrayAscending() {
        List<Integer> input = new ArrayList<Integer>();
        List<Integer> expected = new ArrayList<Integer>();
        List<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void singleValueAscending() {
        Integer[] values = {1};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        List<Integer> expected = input;
        List<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void twoIntAscending() {
        Integer[] values = {2, 1};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1, 2};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesIntAscending() {
        Integer[] values = {1, 11, 56, 999, -2, 18, -1999, 3, 4};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {-1999, -2, 1, 3, 4, 11, 18, 56, 999};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesTwoDegreeSizeIntAscending() {
        Integer[] values = {1, 11, 56, 999, -2, 18, -1999, 3, 4, 15, 24, 87, 13, 44, 77, 100};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {-1999, -2, 1, 3, 4, 11,
                13, 15, 18, 24, 44, 56, 77, 87,  100, 999};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesDoubleAscending() {
        Double[] values = {1., 10., 4.5, 54.01, 9., 0., 2., 3.9, 4.8};
        List<Double> input = new ArrayList<Double>(Arrays.asList(values));
        Double[] expectedValues = {0., 1., 2., 3.9, 4.5, 4.8, 9., 10., 54.01};
        List<Double> expected = new ArrayList<Double>(Arrays.asList(expectedValues));
        List<Double> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesStringAscending() {
        List<String> values = Arrays.asList("coffee", "wine", "juice", "vodka");
        List<String> input  = new ArrayList<String>(values);
        List<String> expectedValues = Arrays.asList("coffee", "juice", "vodka", "wine");
        List<String> expected  = new ArrayList<String>(expectedValues);

        List<String> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    class Car implements Comparable<Car> {
        private int maxSpeed;
        Car(final int theMaxSpeed) {
            maxSpeed = theMaxSpeed;
        }
        public int compareTo(final Car first) {
            if (first.maxSpeed == maxSpeed) {
                return 0;
            }
            if (first.maxSpeed < maxSpeed) {
                return 1;
            } else {
                return -1;
            }
        }
        @Override
            public int hashCode() {
                return maxSpeed;
            }
        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            return (this.maxSpeed == ((Car) obj).maxSpeed);
        }
    }

    @Test
    public void customTypeTwoValuesComparatorAscending() {
        Car[] values = new Car[]{new Car(400), new Car(30)};
        List<Car> input = new ArrayList<Car>(Arrays.asList(values));
        Car[] valuesExpected = new Car[]{new Car(30), new Car(400)};
        List<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        List<Car> sortValues = MergeSort.ascendingSort(input);
        assertEquals(expected, sortValues);
    }

    @Test
    public void customTypeMultipleValuesAscending() {
        Car[] values = new Car[]{new Car(30), new Car(400),
                       new Car(50), new Car(1), new Car(13),
                       new Car(777)};
        ArrayList<Car> input = new ArrayList<Car>(Arrays.asList(values));

        Car[] valuesExpected = new Car[]{new Car(1), new Car(13),
                               new Car(30), new Car(50), new Car(400),
                               new Car(777) };
        List<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        List<Car> sortValues = MergeSort.ascendingSort(input);
        assertEquals(expected, sortValues);
    }

    @Test
    public void emptyArrayDescending() {
        List<Integer> input = new ArrayList<Integer>();
        List<Integer> expected = new ArrayList<Integer>();
        List<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void singleValueDescending() {
        Integer[] values = {7};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        List<Integer> expected = input;
        List<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void twoIntDescending() {
        Integer[] values = {77, 88};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {88, 77};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesIntDescending() {
        Integer[] values = {1, 11, 6, -7, -2, 18, 1999, 3, 4};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1999, 18, 11, 6, 4, 3, 1, -2, -7};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesTwoDegreeSizeIntDescending() {
        Integer[] values = {1, 44, 56, 999, -2, 110, 20, 3, 4, 8, 24, 87, 13, -400, 77, 1200};
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1200, 999, 110, 87, 77,
                56, 44, 24, 20, 13, 8, 4, 3, 1, -2, -400};
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        List<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesDoubleDescending() {
        Double[] values = {5.1, 12.3, 88.2, 54.01, 9., -8.99, 2., 3.9, 4.8};
        List<Double> input = new ArrayList<Double>(Arrays.asList(values));
        Double[] expectedValues = {88.2, 54.01, 12.3, 9.0, 5.1, 4.8, 3.9, 2.0, -8.99};
        List<Double> expected = new ArrayList<Double>(Arrays.asList(expectedValues));
        List<Double> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void multipleValuesStringDescending() {
        List<String> values = Arrays.asList("red", "Alabaster", "Apricot", "Crystal");
        ArrayList<String> input  = new ArrayList<String>(values);
        List<String> expectedValues = Arrays.asList("red", "Crystal", "Apricot", "Alabaster");
        ArrayList<String> expected  = new ArrayList<String>(expectedValues);

        List<String> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void customTypeTwoValuesDecending() {
        Car[] values = new Car[]{new Car(404), new Car(56)};
        List<Car> input = new ArrayList<Car>(Arrays.asList(values));
        Car[] valuesExpected = new Car[]{new Car(404), new Car(56)};
        List<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        List<Car> sortValues = MergeSort.descendingSort(input);
        assertEquals(expected, sortValues);
    }

    @Test
    public void customTypeMultipleValuesDecending() {
        Car[] values = new Car[]{new Car(55),
        new Car(50), new Car(0), new Car(18),
        new Car(1024)};

        List<Car> input = new ArrayList<Car>(Arrays.asList(values));

        Car[] valuesExpected = new Car[]{new Car(1024), new Car(55),
                               new Car(50), new Car(18), new Car(0)};
        List<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));

        List<Car> sortValues = MergeSort.descendingSort(input);
        assertEquals(expected, sortValues);
    }
}
