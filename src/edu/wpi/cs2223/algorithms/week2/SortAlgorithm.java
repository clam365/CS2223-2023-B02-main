package edu.wpi.cs2223.algorithms.week2;

/**
 * Basic interface for performing array based sorting and measuring metrics about its performance.
 *
 * @param <T> type of objects we are sorting.  They must implement Comparable.
 */
public interface SortAlgorithm<T extends Comparable> {
    /**
     * performs an in-place sorting of the input array
     */
    void sort(T[] input);

    /*
     * Exchange two elements in the array.
     */
    void exchange(T[] array, int indexOne, int indexTwo);

    /**
     * @return number of exchanges made to execute the sort
     */
    int numberOfExchanges();

    /**
     * @return number of comparisons made to execute the sort
     */
    int numberOfComparisons();
}
