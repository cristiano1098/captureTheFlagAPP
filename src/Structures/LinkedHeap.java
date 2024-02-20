
package Structures;


import Exceptions.EmptyCollectionException;
import Interfaces.HeapADT;


/**
 *
 * @author Miguel e Cristiano
 */
public class LinkedHeap<T extends Comparable<T>> extends LinkedBinaryTree<T> implements HeapADT<T> {

    public HeapNode<T> lastNode;

    public LinkedHeap() {
        super();
    }

    @Override
    public void addElement(T element) {
        HeapNode node = new HeapNode(element);

        if (root == null) {
            root = node;
        } else {
            HeapNode nextParent = getNextParentAdd();
            if (nextParent.left == null) {
                nextParent.left = node;
            } else {
                nextParent.right = node;
            }
            node.parent = nextParent;
        }
        lastNode = node;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.parent.left != result)) {
            result = result.parent;
        }

        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = (HeapNode) result.parent.right;
                while (result.left != null) {
                    result = (HeapNode) result.left;
                }
            }
        } else {
            while (result.left != null) {
                result = (HeapNode) result.left;
            }
        }

        return result;
    }

    private void heapifyAdd() {
        T temp;

        HeapNode<T> next = lastNode;
        while ((next != root) && (((Comparable) next.element).compareTo(next.parent.element) < 0)) {
            temp = next.element;
            next.element = next.parent.element;
            next.parent.element = temp;
            next = next.parent;
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }

        T minElement = root.element;

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode next_last = getNewLastNode();
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }

            root.element = lastNode.element;
            lastNode = next_last;
            heapifyRemove();
        }

        count--;
        return minElement;
    }

    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode) root;
        HeapNode<T> left = (HeapNode) node.left;
        HeapNode<T> right = (HeapNode) node.right;
        HeapNode<T> next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.element).compareTo(right.element) < 0) {
            next = left;
        } else {
            next = right;
        }

        while ((next != null) && (((Comparable) next.element).compareTo(node.element) < 0)) {
            temp = node.element;
            node.element = next.element;
            next.element = temp;
            node = next;
            left = (HeapNode) node.left;
            right = (HeapNode) node.right;
            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.element).compareTo(right.element) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
    }

    private HeapNode getNewLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left == result)) {
            result = result.parent;
        }
        if (result != root) {
            result = (HeapNode) result.parent.left;
        }

        while (result.right != null) {
            result = (HeapNode) result.right;
        }

        return result;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }

        return root.element;

    }

}
