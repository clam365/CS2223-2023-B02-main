package edu.wpi.cs2223.algorithms.week3;

/**
 * Simple helper for common utilities to be reused by various array backed sort implementations.
 */
public class SortHelper<T extends Comparable> {
    int exchanges = 0;

    void exchange(T[] array, int indexOne, int indexTwo){
        T temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;

        exchanges++;
    }

    String printArrayState(T[] input) {
        String debugOut = "";
        for (int a = 0; a < input.length; a++) {
            debugOut += input[a] + " ";
        }

        return debugOut;
    }

    public int numberOfExchanges(){
        return exchanges;
    }
}
