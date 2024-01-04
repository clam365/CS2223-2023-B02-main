package edu.wpi.cs2223.algorithms.week2;

import edu.wpi.cs2223.algorithms.week2.cllam.SelectionSort;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
    @Test
    public void sortEmpty(){
        SelectionSort<String> selectionSort = new SelectionSort<>(null);
        assertNull(selectionSort.sort());
    }

    @Test
    public void sortSingleElement(){
        SelectionSort<String> selectionSort = new SelectionSort<>(new BasicLinkedNode<>("hey", null));
        LinkedNode<String> sortedResult = selectionSort.sort();

        assertEquals("hey", sortedResult.value());
        assertNull(sortedResult.next());
    }

    @Test
    public void sortTwoElements(){
        SelectionSort<String> selectionSort = new SelectionSort<>(
                new BasicLinkedNode<>("hey",
                        new BasicLinkedNode<>("aloha", null)));
        LinkedNode<String> sortedResult = selectionSort.sort();

        assertEquals("aloha", sortedResult.value());
        assertEquals("hey", sortedResult.next().value());
        assertNull(sortedResult.next().next());
    }


    @Test
    public void sortMultipleElements(){
        // 4, 1, 7, 6, 10, 33

        BasicLinkedNode<Integer> head = new BasicLinkedNode<>(4, null);
        head.updateNext(
                new BasicLinkedNode<>(1,
                        new BasicLinkedNode<>(7,
                                new BasicLinkedNode<>(6,
                                        new BasicLinkedNode<>(10,
                                                new BasicLinkedNode<>(33, null))))));

        SelectionSort<Integer> selectionSort = new SelectionSort<>(head);
        LinkedNode<Integer> sortedResult = selectionSort.sort();

        assertEquals(1, sortedResult.value());
        assertEquals(4, sortedResult.next().value());
        assertEquals(6, sortedResult.next().next().value());
        assertEquals(7, sortedResult.next().next().next().value());
        assertEquals(10, sortedResult.next().next().next().next().value());
        assertEquals(33, sortedResult.next().next().next().next().next().value());
        assertNull(sortedResult.next().next().next().next().next().next());
    }

    @Test
    public void sortAlreadySortedInput(){
        // 1, 4, 6, 7, 10, 33

        BasicLinkedNode<Integer> head = new BasicLinkedNode<>(1, null);
        head.updateNext(
                new BasicLinkedNode<>(4,
                        new BasicLinkedNode<>(6,
                                new BasicLinkedNode<>(7,
                                        new BasicLinkedNode<>(10,
                                                new BasicLinkedNode<>(33, null))))));

        SelectionSort<Integer> selectionSort = new SelectionSort<>(head);
        LinkedNode<Integer> sortedResult = selectionSort.sort();

        assertEquals(1, sortedResult.value());
        assertEquals(4, sortedResult.next().value());
        assertEquals(6, sortedResult.next().next().value());
        assertEquals(7, sortedResult.next().next().next().value());
        assertEquals(10, sortedResult.next().next().next().next().value());
        assertEquals(33, sortedResult.next().next().next().next().next().value());
        assertNull(sortedResult.next().next().next().next().next().next());
    }

    @Test
    public void sortMultipleElementsWithRepeats(){
        // 4, 1, 7, 6, 6, 10, 33, 7

        BasicLinkedNode<Integer> head = new BasicLinkedNode<>(4, null);
        head.updateNext(
                new BasicLinkedNode<>(1,
                        new BasicLinkedNode<>(7,
                                new BasicLinkedNode<>(6,
                                        new BasicLinkedNode<>(6,
                                            new BasicLinkedNode<>(10,
                                                    new BasicLinkedNode<>(33,
                                                            new BasicLinkedNode<>(7, null))))))));

        SelectionSort<Integer> selectionSort = new SelectionSort<>(head);
        LinkedNode<Integer> sortedResult = selectionSort.sort();

        assertEquals(1, sortedResult.value());
        assertEquals(4, sortedResult.next().value());
        assertEquals(6, sortedResult.next().next().value());
        assertEquals(6, sortedResult.next().next().next().value());
        assertEquals(7, sortedResult.next().next().next().next().value());
        assertEquals(7, sortedResult.next().next().next().next().next().value());
        assertEquals(10, sortedResult.next().next().next().next().next().next().value());
        assertEquals(33, sortedResult.next().next().next().next().next().next().next().value());
        assertNull(sortedResult.next().next().next().next().next().next().next().next());
    }

    @Test
    public void doublingPerformanceTest(){
        for (int n = 1; n < Math.pow(2, 14); n = n * 2) {
            SelectionSort<Integer> selectionSort = new SelectionSort<>(constructRandomList(n));
            selectionSort.sort();

            System.out.printf(
                    "Input with %d elements sorted with %d invocations to next.  Expected running time was %d.\n",
                    n, selectionSort.countOfNextInvocations(),
                    selectionSort.runningTime(n));
        }
    }

    LinkedNode<Integer> constructRandomList(int size) {
        Random random = new Random();

        LinkedNode<Integer> head = new BasicLinkedNode<>(random.nextInt(), null);
        LinkedNode<Integer> current = head;

        for (int i = 0; i < (size - 1); i ++) {
            LinkedNode<Integer> newNode = new BasicLinkedNode<>(random.nextInt(), null);
            current.updateNext(newNode);
            current = newNode;
        }

        return head;
    }
}