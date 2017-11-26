package ru.unn.agile.mergesort.Model;
import java.util.ArrayList;
import java.util.List;

public final class MergeSort {

    private interface IPrecede<T extends Comparable<T>> {
        // Return if a preceds b according to the order being used
        boolean precede(T a, T b);
    }
    public static <T extends Comparable<T>> List<T> ascendingSort(final List<T> values) {
        class PrecedAscending implements IPrecede<T> {
            public boolean precede(final T a, final T b) {
                return a.compareTo(b) < 0;
            }
        }
        return mergeSort(values, new PrecedAscending());
    }

    public static <T extends Comparable<T>> List<T> descendingSort(
            final List<T> values) {
        class PrecedDescending implements IPrecede<T> {
            public boolean precede(final T a, final T b) {
                return a.compareTo(b) > 0;
            }
        }
        return mergeSort(values, new PrecedDescending());
    }

    private static <T extends Comparable<T>> List<T> mergeSort(final List<T> input,
                                                               final IPrecede<T> compareFunc) {
        if (input.size() < 2) {
            return input;
        }
        int middleIndex = input.size() / 2;
        List<T> firstHalf = new ArrayList<T>(input.subList(0, middleIndex));
        List<T> secondHalf = new ArrayList<T>(input.subList(middleIndex, input.size()));
        List<T> sortedFirstHalf = mergeSort(firstHalf, compareFunc);
        List<T> sortedSecondHalf = mergeSort(secondHalf, compareFunc);
        return merge(sortedFirstHalf, sortedSecondHalf, compareFunc);
    }

    private static <T extends Comparable<T>> List<T> merge(final List<T> first,
                                                           final List<T> second,
                                                           final IPrecede<T> compareFunc) {
        int firstIndex = 0;
        int secondIndex = 0;
        ArrayList<T> result = new ArrayList<T>();
        while (firstIndex < first.size() && secondIndex < second.size()) {
            if (compareFunc.precede(second.get(secondIndex), first.get(firstIndex))) {
                result.add(second.get(secondIndex++));
            } else {
                result.add(first.get(firstIndex++));
            }
        }
        while (firstIndex < first.size()) {
            result.add(first.get(firstIndex++));
        }
        while (secondIndex < second.size()) {
            result.add(second.get(secondIndex++));
        }
        return result;
    }

    private MergeSort() {

    };
}
