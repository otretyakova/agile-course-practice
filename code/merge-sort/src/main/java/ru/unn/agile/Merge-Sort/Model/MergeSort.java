import java.util.ArrayList;

public final class MergeSort {

    private interface IPreced<T extends Comparable<T>> {
        // Return if a preceds b according to the order being used
        boolean preceeds(T a, T b);
    }
    public static <T extends Comparable<T>> ArrayList<T> ascendingSort(final ArrayList<T> values) {
        class PrecedAscending implements IPreced<T> {
            public boolean preceeds(final T a, final T b) {
                return a.compareTo(b) < 0;
            }
        }
        return mergeSort(values, new PrecedAscending());
    }

    public static <T extends Comparable<T>> ArrayList<T> descendingSort(
            final ArrayList<T> values) {
        class PrecedDescending implements IPreced<T> {
            public boolean preceeds(final T a, final T b) {
                return a.compareTo(b) > 0;
            }
        }
        return mergeSort(values, new PrecedDescending());
    }

    private static <T extends Comparable<T>> ArrayList<T> mergeSort(final ArrayList<T> input,
                                                                    final IPreced<T> compareFunc) {
        if (input.size() < 2) {
            return input;
        }
        int middleIndex = input.size() / 2;
        ArrayList<T> firstHalf = new ArrayList<T>(input.subList(0, middleIndex));
        ArrayList<T> secondHalf = new ArrayList<T>(input.subList(middleIndex, input.size()));
        ArrayList<T> sortedFirstHalf = mergeSort(firstHalf, compareFunc);
        ArrayList<T> sortedSecondHalf = mergeSort(secondHalf, compareFunc);
        return merge(sortedFirstHalf, sortedSecondHalf, compareFunc);
    }

    private static <T extends Comparable<T>> ArrayList<T>
            merge(final ArrayList<T> first,
                  final ArrayList<T> second,
                  final IPreced<T> compareFunc) {
        int firstIndex = 0;
        int secondIndex = 0;
        ArrayList<T> result = new ArrayList<T>();
        while (firstIndex < first.size() && secondIndex < second.size()) {
            if (compareFunc.preceeds(second.get((secondIndex)), first.get(firstIndex))) {
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
