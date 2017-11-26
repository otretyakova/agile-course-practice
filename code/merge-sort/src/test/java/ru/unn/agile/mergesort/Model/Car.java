package ru.unn.agile.mergesort.Model;

class Car implements Comparable<Car> {
    private int maxSpeed;
    private int serialNumber;
    Car(final int theMaxSpeed) {
        init(theMaxSpeed, 10);
    }
    Car(final int theMaxSpeed, final int theSerialNumber) {
        init(theMaxSpeed, theSerialNumber);
    }
    void init(final int theMaxSpeed, final int theSerialNumber) {
        maxSpeed = theMaxSpeed;
        serialNumber = theSerialNumber;
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
