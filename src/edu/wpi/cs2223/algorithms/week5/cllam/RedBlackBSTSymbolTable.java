package edu.wpi.cs2223.algorithms.week5.cllam;

import edu.wpi.cs2223.algorithms.week4.SymbolTable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Red-Black BST Tree implementation of a balanced 2-3 Tree for the purposes of backing a Symbol Table.
 *
 * @param <Key> type of keys in our symbol table
 * @param <Value> type of values in our symbol table
 *
 *
 */
public class RedBlackBSTSymbolTable<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    RedBlackBSTNode<Key, Value> head;
    int size = 0;

    /**
     * Correct the red-black tree by rotating a right leaning red edge to the left.  This was covered on slide
     * 81 and on in our Nov 21 lecture.
     *
     * @return the node that is now the parent with a left leaning red edge.
     *
     * 1. Running time of isEmpty() and size(): O(1); simple calls of incrementing/decrementing
     *    or if statements.
     *
     *    Running time of put(), get(), contains(): O(logN); because we have balanced structures and height
     *    and no poor balances, we will not be running on O(N).
     *
     * 2. RedBlack BST's have better benefits over our BST Symbol Table implementations of Lazy Delete and
     *    Hibbard's Cases. RedBlackBST's will always have a balanced structure AND height within, keeping
     *    our operations runtime to O(logN).
     */
    RedBlackBSTNode<Key, Value> rotateLeft(RedBlackBSTNode<Key, Value> node) {

        //Our temp pointer to the node's rightChild
        RedBlackBSTNode<Key, Value> tempRightChild = node.rightChild;

        // Rotating
        node.rightChild = tempRightChild.leftChild;
        tempRightChild.leftChild = node;

        // Adjusting the colors
        tempRightChild.isRed = node.isRed;
        node.isRed = true;

        return tempRightChild;
    }

    /**
     * Temporarily shift a left leaning red edge to the right.  This is useful for setting up a color flip.  This
     * was covered on slide 89 and on in our Nov 21 lecture.
     *
     * @return the node that is now the parent with a right leaning red edge.
     */
    RedBlackBSTNode<Key, Value> rotateRight(RedBlackBSTNode<Key, Value> node) {

        //Our temp pointer to the node's leftChild
        RedBlackBSTNode<Key, Value> tempLeftChild = node.leftChild;

        // Rotating
        node.leftChild = tempLeftChild.rightChild;
        tempLeftChild.rightChild = node;

        // Adjusting colors
        tempLeftChild.isRed = node.isRed;
        node.isRed = true;

        return tempLeftChild;
    }

    /**
     * Transform a 4-node that is represented by a BST node with two red children into three separate 2-nodes.  This
     * was covered on slide 95 and on in our Nov 21 lecture.
     */
    void flipColors(RedBlackBSTNode<Key, Value> node) {
        // Ensure the current node and its children are not null
        if (node == null || node.leftChild == null || node.rightChild == null) {
            return;
        }

        // Flip the colors
        node.isRed = true;
        node.leftChild.isRed = !node.leftChild.isRed;
        node.rightChild.isRed = !node.rightChild.isRed;
    }

    /**
     * Since red-black trees need to be corrected, via calls to rotateLeft, rotateRight, and flipColors post
     * insertion, and because these calls might cause a chain of corrections from the point of insertion up through
     * the root, put is implemented recursively - to facilitate the up-tree correction.
     */
    RedBlackBSTNode put(RedBlackBSTNode<Key, Value> node, Key key, Value value){
        // this is the base case where we have gotten to the bottom of the tree without finding the key being inserted,
        // and we create the new node here.  The recursion chain will take care of correcting the tree from here.
        if (node == null) {
            RedBlackBSTNode newNode = new RedBlackBSTNode(null, null, key, value);
            size++;
            newNode.isRed = true;
            return newNode;
        }

        int comparisonResult = key.compareTo(node.key);

        if (comparisonResult < 0){
            // recursively call put on the left subtree - when the left subtree is non-empty, no new nodes will
            // be created for this immediate put call, but the recursion will mean that we'll have an opportunity to
            // fix up this part of the tree post ultimate new node insertion
            node.leftChild = put(node.leftChild, key, value);
        }

        if (comparisonResult > 0){
            // recursively call put on the right subtree - when the left subtree is non-empty, no new nodes will
            // be created for this immediate put call, but the recursion will mean that we'll have an opportunity to
            // fix up this part of the tree post ultimate new node insertion
            node.rightChild = put(node.rightChild, key, value);
        }

        // node exists in the tree: we can just update its value - no adjustments need to be made in this case since
        // we have not altered the tree's structure, and we can fast return here
        if (comparisonResult == 0) {
            node.value = value;
            return node;
        }

        // if we have gotten this far, we need to make adjustments post node insertion.  The adjustments
        // are made locally, but due to the recursion in this method, they will be called for each node in
        // the root-null path

        // link to right child is red, but link to left child is not, so rotate to the left
        if (node.rightChild != null && node.rightChild.isRed && (node.leftChild == null || !node.leftChild.isRed)) {
            node = rotateLeft(node);
        }

        // two subsequent left, red nodes
        if (node.leftChild != null && node.leftChild.isRed && node.leftChild.leftChild != null && node.leftChild.leftChild.isRed) {
            node = rotateRight(node);
        }

        // left red and right red
        if (node.leftChild != null && node.leftChild.isRed && node.rightChild != null && node.rightChild.isRed){
            flipColors(node);
        }

        return node;
    }

    @Override
    public void put(Key key, Value value) {
        head = put(head, key, value);
        head.isRed = false;
    }

    @Override
    public Value get(Key key) {
        ParentAndNode location = locate(key);
        if (location.node == null) {
            return null;
        }

        return location.node.value;
    }

    @Override
    public Value delete(Key key) {
        // NOT focused on deletes in Red-Black trees yet - its ok to leave this unimplemented
        return null;
    }

    @Override
    public boolean contains(Key key) {
        ParentAndNode location = locate(key);
        return location.node != null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Helper to store info about a root-null path.
     */
    class PathInfo{
        int length;

        Key key;
        boolean leftChild;

        public PathInfo(int length, Key key, boolean leftChild) {
            this.length = length;
            this.key = key;
            this.leftChild = leftChild;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "length=" + length +
                    ", key=" + key +
                    ", leftChild=" + leftChild +
                    '}';
        }
    }

    /**
     * Returns true if either:
     *  1) all of the paths from root to a null child link are of the same length
     *  2) all of the paths from root to a null child link are of two different lengths and those lengths are
     *  off by one.
     */
    public boolean isTreeBalanced(){
        Set<PathInfo> allPaths = new HashSet<>();
        pathDepth(head, 0, allPaths);

        Set<Integer> allLengths = allPaths.stream().map(path -> path.length).collect(Collectors.toSet());

        if (allLengths.size() == 0) {
            System.out.println("Empty tree is trivially balanced");
            return true;
        }

        if (allLengths.size() == 1) {
            System.out.println("Tree is balanced: all root-null paths are of length: " + allLengths.iterator().next());
            return true;
        }

        if (allLengths.size() == 2) {
            Iterator<Integer> lengthsIterator = allLengths.iterator();
            int lengthOne = lengthsIterator.next();
            int lengthTwo = lengthsIterator.next();

            System.out.println("Tree has two distinct lengths: " + lengthOne + " and " + lengthTwo);
            return Math.abs(lengthOne - lengthTwo) <= 1;
        }

        System.out.println("Tree is not balanced because there are more than 2 distinct depths among root-null paths: " + allPaths);
        return false;
    }

    void pathDepth(RedBlackBSTNode<Key, Value> node, int currentLength, Set<PathInfo> allPaths){
        if (node == null) {
            return;
        }

        if (node.leftChild != null) {
            pathDepth(node.leftChild, node.leftChild.isRed ? currentLength : currentLength + 1, allPaths);
        } else {
            allPaths.add(new PathInfo(currentLength, node.key, true));
        }

        if (node.rightChild != null) {
            pathDepth(node.rightChild, currentLength + 1, allPaths);
        } else {
            allPaths.add(new PathInfo(currentLength, node.key, false));
        }
    }

    /**
     * Helper for the treeDiagram utility.
     */
    void buildDebugInfoForLevel(RedBlackBSTNode<Key, Value> node, String[] keys, int level) {
        if (keys[level] == null) {
            keys[level] = "";
        }

        if (node == null) {
            keys[level] += " ";
            return;
        }

        keys[level] += " " + node.key + (node.isRed ? "R" : "");
        level++;

        buildDebugInfoForLevel(node.leftChild, keys, level);
        buildDebugInfoForLevel(node.rightChild, keys, level);
    }

    /**
     * Builds up a string representation of this BST.  An attempt is made to make all spacing as true to the structure
     * of the tree as possible.  For example, the following invocations:
     * bstSymbolTable.put(6, "six");
     * bstSymbolTable.put(3, "three");
     *
     * Should result in a string that looks like this:
     *         6
     *       3R
     *
     * Where an R suffix indicates that the link into that node is Red.
     */
    public String treeDiagram(){
        String[] keys = new String[10];
        buildDebugInfoForLevel(head, keys, 0);

        String result = "";
        int level = 0;

        int totalLevels = 0;
        while (keys[totalLevels] != null) {
            totalLevels++;
        }

        while (keys[level] != null) {
            String indent = "";
            for (int indentIndex = 0; indentIndex < (totalLevels - level); indentIndex++){
                indent += " ";
            }

            result = result + indent + keys[level] + " \n";
            level++;
        }

        return result;
    }

    /**
     * To be valid, must not have:
     * 1) any right leaning red links
     * 2) two consecutive left red links
     */
    public boolean isValidRedBlackTree(){
        return isValidRedBlackTree(head);
    }

    boolean isValidRedBlackTree(RedBlackBSTNode<Key, Value> node){
        if (node == null) {
            return true;
        }

        // no red, right leaning edges allowed
        if (node.rightChild != null && node.rightChild.isRed) {
            return false;
        }

        // no consecutive left, red edges allowed
        if (node.leftChild != null && node.leftChild.isRed && node.leftChild.leftChild != null && node.leftChild.leftChild.isRed) {
            return false;
        }

        return isValidRedBlackTree(node.leftChild) && isValidRedBlackTree(node.rightChild);

    }

    /**
     * Helper method to locate a node and its parent for a given key.  The parent reference is helpful when performing
     * manipulation on the BST.  If the key is found in the root, the parent reference will be null.
     */
    ParentAndNode locate(Key target){
        if (head == null){
            return new ParentAndNode(null, null);
        }

        RedBlackBSTNode<Key, Value> current = head;
        RedBlackBSTNode<Key, Value> previous = null;

        while (current != null){
            if (current.key.equals(target)) {
                return new ParentAndNode(previous, current);
            }

            previous = current;

            if (target.compareTo(current.key) < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        return new ParentAndNode(previous, null);
    }

    /**
     * Simple, internal tuple of a node and its parent.
     */
    public class ParentAndNode{
        final RedBlackBSTNode<Key, Value> parent;
        final RedBlackBSTNode<Key, Value> node;

        public ParentAndNode(RedBlackBSTNode<Key, Value> parent, RedBlackBSTNode<Key, Value> node) {
            this.parent = parent;
            this.node = node;
        }
    }
}
