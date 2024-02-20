package Structures;

import Interfaces.OrderedListADT;

public class DoubleLinkedOrderedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList() {
        super();
    }

    @Override
    public void add(Comparable element) {
        DoubleNode<T> newNode = new DoubleNode(element);
        DoubleNode<T> current = head;

        if (isEmpty()) {
            head = tail = newNode;
        } else if (element.compareTo(head.getContent()) >= 0) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else if (element.compareTo(tail.getContent()) <= 0) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else {
            while (element.compareTo(current.getContent()) < 0) {
                current = current.getNext();
            }
            current.getPrevious().setNext(newNode);
            newNode.setPrevious(current.getPrevious());
            newNode.setNext(current);
            current.setPrevious(newNode);
        }
    count++;
    }
    
    public boolean binarySearch(){
        boolean found = false;
        
        
        return found;
    }

}
