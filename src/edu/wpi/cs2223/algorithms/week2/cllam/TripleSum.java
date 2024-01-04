package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.InstrumentorResult;
import edu.wpi.cs2223.algorithms.week2.LinkedNode;
import edu.wpi.cs2223.algorithms.week2.cllam.LinkedNodeInstrumentor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An implementation of the Triple Sum (or Three Sum) problem.  The input to the problem is a linked
 * list of integers and the goal of the implementation is to find all distinct triples of integers that
 * sum to 0.
 *
 * To simplify things, it is safe to assume that all input values are unique.
 *
 * 1. The runtime of my implementation is straightforward, O(N^3). There are 2 Nested loops inside an outer one.
 *
 * 2. The number of actual calls when looking at doublingPerformanceTest compared to expected running time
 *    is a fraction that continues to get smaller as the size increases. The expected running time is the worst case scenario.
 *
 * 3. Mathematical Induction for T(N) = N^3
 *    Base Case: T(1) = 1 * 1 * 1 = 1
 *    We want to imply T(N + 1)
 *
 *    T(N+1) = (N + 1)^3
 *           = N^3 + 3x^2 + x + 1
 *           = T(N) + 3x^2 + x + 1
 *
 *    By mathematical induction, T(N) holds true for the base case N = 1 and also holds true for T(N+1).
 *
 */
public class TripleSum {
    AtomicInteger nextCounter;
    LinkedNode<Integer> accessTrackingHead;

    //CONSTRUCTOR
    public TripleSum(LinkedNode<Integer> head) {
        InstrumentorResult<Integer> instrumentorResult = new LinkedNodeInstrumentor<Integer>().instrument(head);
        this.nextCounter = instrumentorResult.nextCounter;
        this.accessTrackingHead = instrumentorResult.head;
    }

    /**
     * Returns a set of sets of integers.  Where each of the inner sets contain exactly three integers such
     * that the sum of these three integers is zero.  The external set should contain every possible combination
     * of 3 integers that sum to zero from the input linked list of integers.
     */
    public Set<Set<Integer>> find() {
        nextCounter.set(0); //Initializing Counter of comparisons

        // the set of integers that equal to 0 which will be inputted into Hashset Result
        Set<Set<Integer>> result = new HashSet<>(); //set of sets of integers

        LinkedNode<Integer> a = accessTrackingHead; //copy of linkedlist so we don't modify it

        while (a != null) {
            LinkedNode<Integer> b = a.next();
            while (b != null) {
                LinkedNode<Integer> c = b.next();
                while (c != null) {
                    int sum = a.value() + b.value() + c.value();
                    if (sum == 0) {
                        //System.out.println(a.value() +  " + " + b.value() + " + " + c.value() );
                        Set<Integer> inputIntoResult = new HashSet<>(); //creating set of integers and adding in values
                        inputIntoResult.add(a.value());
                        inputIntoResult.add(b.value());
                        inputIntoResult.add(c.value());
                        result.add(inputIntoResult); //adding the set
                    }
                    c = c.next();
                    nextCounter.getAndIncrement();
                }
                b = b.next(); //iterate through the rest
                nextCounter.getAndIncrement();
            }
            a = a.next();
            nextCounter.getAndIncrement();
        }
        return result;
    }

    public int countOfNextInvocations() {
        return nextCounter.get();
    }


    public int runningTime(int elementCount) {
        int expected = elementCount * elementCount * elementCount; // O(N^3)
        return expected;
    }
}
