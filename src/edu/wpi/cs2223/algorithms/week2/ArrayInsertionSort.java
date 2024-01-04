package edu.wpi.cs2223.algorithms.week2;

/**
 * InsertionSort implementation for input arrays of comparable types.
 */
public class ArrayInsertionSort<T extends Comparable> implements SortAlgorithm<T> {
    SortHelper<T> sortHelper;

    int comparisons = 0;

    @Override
    public void sort(T[] input) {
        sortHelper = new SortHelper<>();
        comparisons = 0;

        for (int a = 0; a < input.length; a++){
            for (int b = a; b > 0; b--) {

                comparisons++;
                if (input[b].compareTo(input[b-1]) < 0) {
                    exchange(input, b, b-1);
                } else {
                    // break out of inner loop as soon as we find one a value that does not need
                    // exchanging - because we know that the left side of the input is sorted, we don't
                    // need to keep going past this point
                    break;
                }
            }

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
