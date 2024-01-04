package edu.wpi.cs2223.algorithms.week2;

/**
 * SelectionSort implementation for input arrays of comparable types.
 */
public class ArraySelectionSort<T extends Comparable> implements SortAlgorithm<T>{
    SortHelper<T> sortHelper;

    int comparisons = 0;

    @Override
    public void sort(T[] input) {
        comparisons = 0;
        sortHelper = new SortHelper<>();

        for (int a = 0; a < input.length; a++){
            int indexOfMin = a;

            for (int b = a + 1; b < input.length; b++) {
                comparisons++;
                if (input[b].compareTo(input[indexOfMin]) < 0) {
                    indexOfMin = b;
                }
            }
            exchange(input, a, indexOfMin);

            System.out.printf("After iteration %d, array state is: %s.\n", a, sortHelper.printArrayState(input));
        }
    }

    @Override
    public void exchange(T[] array, int indexOne, int indexTwo) {
        sortHelper.exchange(array, indexOne, indexTwo);
    }

    @Override
    public int numberOfExchanges() {
        return sortHelper.numberOfExchanges();
    }

    @Override
    public int numberOfComparisons() {
        return comparisons;
    }
}
