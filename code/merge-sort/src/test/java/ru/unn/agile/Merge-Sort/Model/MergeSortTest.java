import org.junit.Test;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MergeSortTest {
    @Test
    public void EmptyArrayAscending(){
        ArrayList<Integer> input = new ArrayList<Integer>();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        ArrayList<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void SingleValueAscending(){
        Integer[] values = {1};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        ArrayList<Integer> expected = input;
        ArrayList<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void TwoIntAscending(){
        Integer[] values = {2, 1};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1,2};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesIntAscending(){
        Integer[] values = {1 , 11, 56, 999, -2, 18, -1999, 3, 4};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {-1999, -2, 1, 3, 4, 11, 18, 56, 999};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesTwoDegreeSizeIntAscending(){
        Integer[] values = {1 , 11, 56, 999, -2, 18, -1999, 3, 4, 15, 24, 87, 13, 44, 77, 100};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {-1999, -2, 1, 3, 4, 11, 13, 15, 18, 24, 44, 56, 77, 87,  100, 999};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesDoubleAscending(){
        Double[] values = {1. , 10., 4.5, 54.01, 9., 0., 2., 3.9, 4.8};
        ArrayList<Double> input = new ArrayList<Double>(Arrays.asList(values));
        Double[] expectedValues = {0., 1. ,2., 3.9, 4.5, 4.8, 9., 10., 54.01,};
        ArrayList<Double> expected = new ArrayList<Double>(Arrays.asList(expectedValues));
        ArrayList<Double> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesStringAscending(){
        List<String> values = Arrays.asList("coffee", "wine", "juice", "vodka");
        ArrayList<String> input  = new ArrayList<String>(values);
        List<String> expectedValues = Arrays.asList("coffee", "juice", "vodka", "wine");
        ArrayList<String> expected  = new ArrayList<String>(expectedValues);

        ArrayList<String> actual = MergeSort.ascendingSort(input);
        assertEquals(expected, actual);
    }

    class Car implements Comparable<Car> {
        private int maxSpeed;
        public int GetMaxSpeed(){
            return maxSpeed;
        }
        public Car( int theMaxSpeed ){
            maxSpeed = theMaxSpeed;
        }
        public int compareTo(Car first) {
            if (first.maxSpeed == maxSpeed)
                return 0;
            if (first.maxSpeed < maxSpeed)
                return 1;
            else
                return -1;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj==this) return true;
            if (obj==null || obj.getClass()!=this.getClass())
                return false;
            return (this.maxSpeed==((Car) obj).maxSpeed);
        }
    }

    @Test
    public void CustomTypeTwoValuesComparatorAscending(){
        Car[] values = new Car[]{ new Car(400), new Car(30)};
        ArrayList<Car> input = new ArrayList<Car>(Arrays.asList(values));
        Car[] valuesExpected = new Car[]{new Car(30),new Car(400)};
        ArrayList<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        ArrayList<Car> sortValues = MergeSort.ascendingSort(input);
        assertEquals(expected,sortValues);
    }

    @Test
    public void CustomTypeMultipleValuesAscending(){
        Car[] values = new Car[]{ new Car(30), new Car(400),
                       new Car(50), new Car(1), new Car(13),
                       new Car(777)};
        ArrayList<Car> input = new ArrayList<Car>(Arrays.asList(values));

        Car[] valuesExpected = new Car[]{ new Car(1), new Car(13),
                               new Car(30), new Car(50), new Car(400),
                               new Car(777)};
        ArrayList<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        ArrayList<Car> sortValues = MergeSort.ascendingSort(input);
        assertEquals(expected,sortValues);
    }

    @Test
    public void EmptyArrayDescending(){
        ArrayList<Integer> input = new ArrayList<Integer>();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        ArrayList<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void SingleValueDescending(){
        Integer[] values = {7};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        ArrayList<Integer> expected = input;
        ArrayList<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void TwoIntDescending(){
        Integer[] values = {77, 88};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {88, 77};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesIntDescending(){
        Integer[] values = {1 , 11, 6, -7, -2, 18, 1999, 3, 4};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1999, 18, 11, 6, 4, 3, 1, -2, -7};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesTwoDegreeSizeIntDescending(){
        Integer[] values = {1 , 44, 56, 999, -2, 110, 20, 3, 4, 8, 24, 87, 13, -400, 77, 1200};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(values));
        Integer[] expectedValues = {1200, 999, 110, 87, 77, 56, 44, 24, 20, 13, 8, 4, 3, 1, -2, -400};
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(expectedValues));
        ArrayList<Integer> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesDoubleDescending(){
        Double[] values = {5.1 , 12.3, 88.2, 54.01, 9., -8.99, 2., 3.9, 4.8};
        ArrayList<Double> input = new ArrayList<Double>(Arrays.asList(values));
        Double[] expectedValues = {88.2, 54.01, 12.3, 9.0, 5.1, 4.8, 3.9, 2.0, -8.99};
        ArrayList<Double> expected = new ArrayList<Double>(Arrays.asList(expectedValues));
        ArrayList<Double> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleValuesStringDescending(){
        List<String> values = Arrays.asList("red", "Alabaster", "Apricot", "Crystal");
        ArrayList<String> input  = new ArrayList<String>(values);
        List<String> expectedValues = Arrays.asList("red", "Crystal", "Apricot", "Alabaster");
        ArrayList<String> expected  = new ArrayList<String>(expectedValues);

        ArrayList<String> actual = MergeSort.descendingSort(input);
        assertEquals(expected, actual);
    }

    @Test
    public void CustomTypeTwoValuesDecending(){
        Car[] values = new Car[]{ new Car(404), new Car(56)};
        ArrayList<Car> input = new ArrayList<Car>(Arrays.asList(values));
        Car[] valuesExpected = new Car[]{new Car(404),new Car(56)};
        ArrayList<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));
        ArrayList<Car> sortValues = MergeSort.descendingSort(input);
        assertEquals(expected,sortValues);
    }

    @Test
    public void CustomTypeMultipleValuesDecending(){
        Car[] values = new Car[]{new Car(55),
        new Car(50), new Car(0), new Car(18),
        new Car(1024)};

        ArrayList<Car> input = new ArrayList<Car>(Arrays.asList(values));

        Car[] valuesExpected = new Car[]{new Car(1024),new Car(55),
                               new Car(50),new Car(18),new Car(0)};
        ArrayList<Car> expected = new ArrayList<Car>(Arrays.asList(valuesExpected));

        ArrayList<Car> sortValues = MergeSort.descendingSort(input);
        assertEquals(expected,sortValues);
    }


    @Test
    public void StableSortDecending(){

    }
}
