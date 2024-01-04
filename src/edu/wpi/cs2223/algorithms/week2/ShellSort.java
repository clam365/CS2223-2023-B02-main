package edu.wpi.cs2223.algorithms.week2;

/**
 * ShellSort implementation for input arrays of comparable types.
 */
public class ShellSort<T extends Comparable> implements SortAlgorithm<T> {
    SortHelper<T> sortHelper;

    int comparisons = 0;

    @Override
    public void sort(T[] input) {
        comparisons = 0;
        sortHelper = new SortHelper<>();

        int interval = determineInterval(input.length);

        while (interval >= 1) {
            for (int a = interval; a < input.length; a++) {
                for (int b = a; b >= interval; b -= interval) {

                    comparisons++;
                    if (input[b].compareTo(input[b-interval]) < 0) {
                        exchange(input, b, b-interval);
                    } else {
                        // break out of inner loop as soon as we find one a value that does not need
                        // exchanging - because we know that the left side of the input is sorted, we don't
                        // need to keep going past this point
                        break;
                    }
                }
            }

            System.out.printf("After iteration with interval size: %d, array state is: %s.\n", interval, sortHelper.printArrayState(input));
            interval = interval / 3;
        }

    }

    int determineInterval(int inputLength) {
        int interval = 1;

        while (interval < inputLength / 3) {
            interval = interval * 3 + 1;
        }

        return interval;
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
