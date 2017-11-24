import java.util.ArrayList;

public class MergeSort {

    private interface IPreceed<T extends Comparable<T>> {
        // Return if a preceeds b according to the order being used
        public boolean Preceeds(T a, T b);
    }
    public static <T extends Comparable<T>> ArrayList<T> AscendingSort(ArrayList<T> values){
        class PreceedAscending implements IPreceed<T> {
            public boolean Preceeds(T a, T b) {
                return a.compareTo(b) < 0;
            }
        }
        return mergeSort(values, new PreceedAscending());
    }

    public static <T extends Comparable<T>> ArrayList<T> DescendingSort(ArrayList<T> values){
        class PreceedDescending implements IPreceed<T> {
            public boolean Preceeds(T a, T b) {
                return a.compareTo(b) > 0;
            }
        }
        return mergeSort(values, new PreceedDescending());
    }

    private static <T extends Comparable<T>> ArrayList<T> mergeSort(ArrayList<T> input, IPreceed<T> compareFunc) {
        if (input.size() < 2)
            return input;
        int middleIndex = input.size() / 2;
        ArrayList<T> firstHalf = new ArrayList<T>(input.subList(0, middleIndex));
        ArrayList<T> secondHalf = new ArrayList<T>(input.subList(middleIndex, input.size()));
        ArrayList<T> sortedFirstHalf = mergeSort(firstHalf, compareFunc);
        ArrayList<T> sortedSecondHalf = mergeSort(secondHalf, compareFunc);
        return merge(sortedFirstHalf, sortedSecondHalf, compareFunc);
    }

    private static <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> first, ArrayList<T> second,
                                                                IPreceed<T> compareFunc) {
        int firstIndex = 0;
        int secondIndex = 0;
        ArrayList<T> result = new ArrayList<T>();
        while (firstIndex < first.size() && secondIndex < second.size())
            if (compareFunc.Preceeds(second.get((secondIndex)), first.get(firstIndex)))
                result.add(second.get(secondIndex++));
            else
                result.add(first.get(firstIndex++));
        while (firstIndex < first.size())
            result.add(first.get(firstIndex++));
        while (secondIndex < second.size())
            result.add(second.get(secondIndex++));
        return result;
    }
}