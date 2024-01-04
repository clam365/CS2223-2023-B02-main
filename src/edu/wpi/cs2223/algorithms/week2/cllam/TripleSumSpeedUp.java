package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.LinkedNode;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import edu.wpi.cs2223.algorithms.week2.InstrumentorResult;

/**
 * An implementation of the TripleSum problem with a performance improvement over its most straightofrward
 * solution.
 */
public class TripleSumSpeedUp {

    AtomicInteger nextCounter;

    LinkedNode<Integer> accessTrackingHead;

    public TripleSumSpeedUp(LinkedNode<Integer> head) {
        InstrumentorResult<Integer> instrumentorResult = new LinkedNodeInstrumentor<Integer>().instrument(head);
        this.nextCounter = instrumentorResult.nextCounter;
        this.accessTrackingHead = instrumentorResult.head;
    }

    public Set<Set<Integer>> find(){
        // TODO: implement
        Set<Set<Integer>> result = new HashSet<>();
        return result;
    }

    public int countOfNextInvocations(){
        return nextCounter.get();
    }

    public int runningTime(int elementCount) {
        // TODO: implement
        return 0;
    }
}

