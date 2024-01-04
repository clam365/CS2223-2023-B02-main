package edu.wpi.cs2223.algorithms.week5.cllam;

import edu.wpi.cs2223.algorithms.week4.BSTNode;
import edu.wpi.cs2223.algorithms.week4.SymbolTable;

/**
 * Binary Search backed implementation of a Symbol Table.  Deletes are implemented via Hibbard's method: nodes are
 * actually removed from the tree.
 *
 * @param <Key> type of keys in the symbol table
 * @param <Value> type of values in the symbol table
 *
 * NOTE!!: I had to make the initial BSTNode head public along with the ParentAndNode's b/c it was not reading
 *
 * 1. Running time of put(): O(N); it's O(1) if the head is empty but any other following nodes
 *    will be O(N) to traverse through entire BST to find the correct place to put it in.
 *
 *    Running time of get(): O(N); loop to traverse through BST to find key, worst case is at lowest depth.
 *
 *    Running time of delete(): I would say O(N) across all cases. Because in the original delete() and not the
 *    cases, we use locate to find what we are deleting, and that operation is O(N). The deletions in each case
 *    is O(1), but without the helper we have to copy and paste that everywhere. So O(N).
 *
 *    Running time of contain(): O(N); almost the same function as get(), just returns the boolean version
 *
 *    Running time of isEmpty(): O(1); just one check at the head with an if statement.
 *
 *    Running time of size(): O(1); increments and decrements based on call from switches.
 *
 * 2. To ensure O(logN) running time across all our operations, balancing the BST Tree provides the best optimization.
 *    The depth of the tree should be logarithmic to the number of elements N. Just like RedBlack Trees.
 */
public class BSTSymbolTableHibbardDelete<Key extends Comparable, Value> implements SymbolTable<Key, Value> {
    public BSTNode<Key, Value> head;
    int size = 0;

    @Override
    public void put(Key key, Value value) {
        BSTNode current = head; //pointer to head
        BSTNode parentNode = null;
        boolean isDuplicate = contains(key); //true or false if it has it

        //Base Case of creating the root
        if (head == null) {
            head = new BSTNode<>(null, null, key, value);
            //Incrementing size
            size++;
            return; //end entire program
        }

        //If key already exists, we will update its value instead
        if (isDuplicate == true) {
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; //Updating our value
                    return;
                }
                else {
                    if (key.compareTo(current.key) < 0) {
                        current = current.leftChild; //key < BST current key go left
                    }
                    else {
                        current = current.rightChild; //key > BST current key go right
                    }
                }
            }
        }

        //If key does not previously exist (isDuplicate == false), add it to the BST
        else {
            // Loop to iterate through BST until it reaches the bottom depth of where we need to go for insert
            while (current != null) {
                if (key.compareTo(current.key) < 0) {
                    parentNode = current;
                    current = current.leftChild; //key < BST current key go left
                }
                else {
                    parentNode = current;
                    current = current.rightChild; //key > BST current key go right
                }

            }

            // We are now inserting it in
            if (key.compareTo(parentNode.key) < 0) {
                parentNode.leftChild = new BSTNode<>(null, null, key, value);
            }
            else {
                parentNode.rightChild = new BSTNode<>(null, null, key, value);
            }

            size++; //we then will increment the BST total node amount
        }
    }

    @Override
    public Value get(Key key) {
        BSTNode current = head; //our pointer

        while (current != null) {
            if (current.key.equals(key)) {
                Value solution = (Value) current.value; //Return value of key if found
                return solution;
            }
            else {
                if (key.compareTo(current.key) < 0) { //key < BST current key go left
                    current = current.leftChild;
                }
                else {
                    current = current.rightChild; //key > BST current key go right
                }
            }
        }
        return null; //if key is not found
    }

    Value deleteCaseOneNoChildren(ParentAndNode location){

        Value solution = (Value) location.node.value; //extracting value

        //Disconnecting parent nodes from both left and right
        location.parent.leftChild = null;
        location.parent.rightChild = null;
        return solution;
    }

    Value deleteCaseTwoOnlyLeftChild(ParentAndNode location) {
        Value solution = (Value) location.node.value; // extracting value

        // Base case when the node being deleted is the root
        if (location.parent == null) {
            head = location.node.leftChild;
        }

        else {
            // See whether the node being deleted is the left or right child of its parent
            boolean isLeftChild = location.isNodeLeftChild();

            // Parent pointers go to node leftChild
            if (isLeftChild) {
                location.parent.leftChild = location.node.leftChild;
            }

            else {
                location.parent.rightChild = location.node.leftChild;
            }
        }

        location.node.leftChild = null; // Deleting the deleted node
        return solution;
    }

    Value deleteCaseTwoOnlyRightChild(ParentAndNode location) {
        Value solution = (Value) location.node.value; // extracting value

        // Base case when the node being deleted is the root
        if (location.parent == null) {
            head = location.node.rightChild;
        }

        else {
            // See whether the node being deleted is the left or right child of its parent
            boolean isLeftChild = location.isNodeLeftChild();

            // Parent pointers go to the node rightChild
            if (isLeftChild) {
                location.parent.leftChild = location.node.rightChild;
            }

            else {
                location.parent.rightChild = location.node.rightChild;
            }
        }

        location.node.rightChild = null; // Deleting the deleted node
        return solution;
    }

    Value deleteCaseThreeTwoChildren(ParentAndNode location) {
        Value solution = (Value) location.node.value; // extract value

        // Finding the successor
        ParentAndNode successor = findSuccessor(location.node);

        // Replacing the value of the node to be deleted with the value of the successor
        location.node.key = successor.node.key;
        location.node.value = successor.node.value;

        // Changing pointers to delete the node we want
        if (successor.parent.leftChild == successor.node) {
            successor.parent.leftChild = successor.node.rightChild;
        }

        else {
            successor.parent.rightChild = successor.node.rightChild;
        }

        return solution;
    }

    /**
     * Find the node that is the smallest node that is bigger than the passed node.  To do that,
     * we go right once, and then left as far as we can go.
     */
    public ParentAndNode findSuccessor(BSTNode<Key, Value> node){

        BSTNode parentNode = null;
        BSTNode current = node;

        //Going right once
        parentNode = current;
        current = current.rightChild;

        //Now iterating left until we find the minimum
        while (current.leftChild != null) {
            parentNode = current;
            current = current.leftChild;
        }

        ParentAndNode solution = new ParentAndNode(parentNode, current);
        return solution;
    }

    @Override //THIS IS ALREADY DONE FOR US
    public Value delete(Key key) {
        ParentAndNode location = locate(key);

        if (location.node == null) {
            return null;
        }

        size--;

        // Case 1: Node being deleted has No Children
        if (location.node.leftChild == null && location.node.rightChild == null) {
            return deleteCaseOneNoChildren(location);
        }

        // Case 2: Node being deleted has One Child - right
        if (location.node.leftChild == null && location.node.rightChild != null) {
            return deleteCaseTwoOnlyRightChild(location);
        }

        // Case 2:  Node being deleted has One Child - left
        if (location.node.leftChild != null && location.node.rightChild == null) {
            return deleteCaseTwoOnlyLeftChild(location);
        }

        // Case 3: Node being deleted has Two children
        return deleteCaseThreeTwoChildren(location);
    }

    @Override
    public boolean contains(Key key) {
        BSTNode current = head; //our pointer

        // Loop to iterate through BST
        while (current != null) {
            if (current.key.equals(key)) { //Return True if it is found
                return true;
            }

            else { //Go down depth in BST
                if (key.compareTo(current.key) < 0) {
                    current = current.leftChild; //if key < BST current key
                }
                else {
                    current = current.rightChild; //if key > BST current key
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        // Checks if this is empty of course and returns the outcome
        if (head == null && size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Helper method to locate a node and its parent for a given key.  The parent reference is helpful when performing
     * manipulation on the BST.  If the key is found in the root, the parent reference will be null.
     */
    ParentAndNode locate(Key target){
        if (head == null){
            return new ParentAndNode(null, null);
        }

        BSTNode<Key, Value> current = head;
        BSTNode<Key, Value> previous = null;

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
        public final BSTNode<Key, Value> parent;
        public final BSTNode<Key, Value> node;

        public ParentAndNode(BSTNode<Key, Value> parent, BSTNode<Key, Value> node) {
            this.parent = parent;
            this.node = node;
        }

        boolean isNodeLeftChild(){
            return parent.leftChild == node;
        }
    }

    /**
     * Builds up a string representation of this BST.  An attempt is made to make all spacing as true to the structure
     * of the tree as possible.  For example, the following invocations:
     * bstSymbolTable.put(6, "six");
     * bstSymbolTable.put(3, "three");
     * bstSymbolTable.put(8, "eight");
     *
     * Should result in a string that looks like this:
     *         6
     *       3   8
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
     * Helper for the treeDiagram utility.
     */
    void buildDebugInfoForLevel(BSTNode<Key, Value> node, String[] keys, int level) {
        if (node == null) {
            return;
        }

        if (keys[level] == null) {
            keys[level] = "";
        }

        keys[level] += " " + node.key;
        level++;

        buildDebugInfoForLevel(node.leftChild, keys, level);
        buildDebugInfoForLevel(node.rightChild, keys, level);
    }
}