package edu.wpi.cs2223.algorithms.week2;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TripleSumSpeedUpTest {
    @Test
    public void emptyList(){
        TripleSumSpeedUp TripleSumSpeedUp = new TripleSumSpeedUp(null);
        Set<Set<Integer>> result = TripleSumSpeedUp.find();

        assertTrue(result.isEmpty());
    }

    @Test
    public void notEnoughElements(){
        TripleSumSpeedUp TripleSumSpeedUp = new TripleSumSpeedUp(new BasicLinkedNode<>(5, null));
        Set<Set<Integer>> result = TripleSumSpeedUp.find();

        assertTrue(result.isEmpty());
    }

    @Test
    public void findsTripleSumSpeedUps(){
        BasicLinkedNode<Integer> head = new BasicLinkedNode<>(4, null);
        head.updateNext(
                new BasicLinkedNode<>(1,
                        new BasicLinkedNode<>(3,
                                new BasicLinkedNode<>(-5,
                                        new BasicLinkedNode<>(6,
                                                new BasicLinkedNode<>(-1,
                                                        new BasicLinkedNode<>(-11,
                                                                new BasicLinkedNode<>(7, null))))))));

        TripleSumSpeedUp TripleSumSpeedUp = new TripleSumSpeedUp(head);
        Set<Set<Integer>> result = TripleSumSpeedUp.find();

        assertEquals(3, result.size());

        Set<Integer> expectedSumOne = new HashSet<>();
        expectedSumOne.add(4);
        expectedSumOne.add(1);
        expectedSumOne.add(-5);

        assertTrue(result.contains(expectedSumOne));

        Set<Integer> expectedSumTwo = new HashSet<>();
        expectedSumTwo.add(-5);
        expectedSumTwo.add(6);
        expectedSumTwo.add(-1);

        assertTrue(result.contains(expectedSumTwo));

        Set<Integer> expectedSumThree = new HashSet<>();
        expectedSumThree.add(4);
        expectedSumThree.add(-11);
        expectedSumThree.add(7);

        assertTrue(result.contains(expectedSumThree));
    }

//    @Test
    public void doublingPerformanceTest(){
        for (int n = 1; n < Math.pow(2, 12); n = n * 2) {
            TripleSumSpeedUp TripleSumSpeedUp = new TripleSumSpeedUp(constructRandomList(n));
            TripleSumSpeedUp.find();

            System.out.printf(
                    "Input with %d elements triple-summed with %d invocations to next.  Expected running time was %d.\n",
                    n, TripleSumSpeedUp.countOfNextInvocations(),
                    TripleSumSpeedUp.runningTime(n));
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