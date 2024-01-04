package edu.wpi.cs2223.algorithms.week1;

import edu.wpi.cs2223.algorithms.shared.Node;

/**
 * Implements a game where contestants stand in a circle.  Then starting with the "head" contestant,
 * they count off up to the "countPerRound" value.  The person who says that integer value is eliminated and
 * has to leave the circle.
 *
 * The next round starts with the person right after the one who just got eliminated and once again contestants
 * count up to the "countPerRound" value and whoever says that value leaves the circle.
 *
 * This goes on until the last person is eliminated.
 *
 * Guarantees:
 * * countPerRound is a positive integer
 * * head is the start of a circular linked list
 *
 * Requirements: "play" should return a Node that is the head of a list whose values are the names of the
 * contestants in the order in which they were eliminated.
 *
 * For example, if the game is configured with countPerRound = 2 and the input is :
 *  bob -> james -> rose -> sally -> back to bob,  where bob is the "head" contestant,
 *  this implementation should produce the following result:
 *  james -> sally -> rose -> bob -> null .
 */
public class CircularLinkedListSurvivor {
    /**
     * The number of people that are skipped in each round of elimination.
     */
    final int countPerRound;

    public CircularLinkedListSurvivor(int countPerRound) {
        this.countPerRound = countPerRound;
    }

    /**
     * Play the game as described in the javadoc of this class.
     *
     * @param head - the head contestant in a circularly linked list of contenders.
     */
    public Node<String> play(Node<String> head){
        // TODO: implement
        return null;
    }
}
