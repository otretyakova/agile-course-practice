package ru.unn.agile.mergesort.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
    // Tests for Car class
    @Test
    public void carCompareToEqualCars() {
        Car value1 = new Car(1, 1);
        Car value2 = new Car(1, 2);
        int expected = 0;
        assertEquals(expected, value1.compareTo(value2));
    }

    @Test
    public void carCompareToLessCars() {
        Car value1 = new Car(2, 3);
        Car value2 = new Car(22, 4);
        int expected = -1;
        assertEquals(expected, value1.compareTo(value2));
    }

    @Test
    public void carCompareToUpCars() {
        Car value1 = new Car(100000, 5);
        Car value2 = new Car(22, 6);
        int expected = 1;
        assertEquals(expected, value1.compareTo(value2));
    }

    @Test
    public void carEquals() {
        Car equalValue1 = new Car(555);
        Car equalValue2 = new Car(555);
        boolean expected = true;
        assertEquals(expected, equalValue1.equals(equalValue2));
    }

    @Test
    public void carNotEquals() {
        Car value1 = new Car(777);
        Car value2 = new Car(888);
        boolean expected = false;
        assertEquals(expected, value1.equals(value2));
    }
}
