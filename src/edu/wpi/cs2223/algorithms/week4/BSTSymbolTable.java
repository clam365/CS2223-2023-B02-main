package edu.wpi.cs2223.algorithms.week4;

public class BSTSymbolTable<Key extends Comparable, Value> implements SymbolTable<Key, Value> {

    BSTNode<Key, Value> head;

    @Override
    public void put(Key key, Value value) {
        if (head == null) {
            head = new BSTNode<>(null, null, key, value);
            return;
        }
        //If the key is already in the BST
        ParentAndNode checkingNode = locate(key);
        if(checkingNode.node != null) {
            checkingNode.node.value = value;
            return;
        }

        //If the key is not already in the BST;
        BSTNode<Key, Value> prevNode = null;
        BSTNode<Key, Value> currNode = head;
        while(currNode != null) {
            if(key.compareTo(currNode.key) < 0) {
                prevNode = currNode;
                if(currNode.leftChild == null) {
                    currNode.leftChild = new BSTNode<>(null, null, key, value);
                    break;
                }
                currNode = currNode.leftChild;
            } else if (key.compareTo(currNode.key) > 0) {
                prevNode = currNode;
                if(currNode.rightChild == null) {
                    currNode.rightChild = new BSTNode<>(null, null, key, value);
                    break;
                }
                currNode = currNode.rightChild;
            }
        }
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
        return 0;
    }

    void debug(BSTNode<Key, Value> node, String[] keys, int level) {
        if (node == null) {
            return;
        }

        if (keys[level] == null) {
            keys[level] = "";
        }

        keys[level] += " " + node.key;
        level++;

        debug(node.leftChild, keys, level);
        debug(node.rightChild, keys, level);
    }

    public String treeDiagram(){
        String[] keys = new String[10];
        debug(head, keys, 0);

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
     * For a given target, the node field of ParentAndNode contains the node with the target key if it exists.
     * If it does not exist, node will be null and the parent field of ParentAndNode will contain the last node
     * in the binary search for target (the node that would have been target's parent if target were in the tree).
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

    class ParentAndNode{
        final BSTNode<Key, Value> parent;
        final BSTNode<Key, Value> node;

        public ParentAndNode(BSTNode<Key, Value> parent, BSTNode<Key, Value> node) {
            this.parent = parent;
            this.node = node;
        }
    }
}
