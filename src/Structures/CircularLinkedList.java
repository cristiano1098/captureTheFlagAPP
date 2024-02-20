
package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;


public class CircularLinkedList<T> extends LinkedList<T> {
    
    public CircularLinkedList() {
        super();
    }
    
    @Override
    public void addToHead(T data) throws ElementNotFoundException {
        if (data == null) {
            throw new ElementNotFoundException();
        }
        
        LinearNode<T> newNode = new LinearNode(data);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        count++;
    }
    
    @Override
    public void addToTail(T data) throws ElementNotFoundException {
        if (data == null) {
            throw new ElementNotFoundException();
        }
        
        LinearNode<T> newNode = new LinearNode(data);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setNext(head);
            tail = newNode;
        }
        count++;
    }
    
    @Override
    public void remove(T data) throws ElementNotFoundException, EmptyCollectionException {
        LinearNode<T> newNode = new LinearNode(data);
        
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        
        boolean found = false;
        LinearNode<T> previous = head;
        LinearNode<T> current = head.getNext();
        
        if (head.equals(newNode)) {
            found = true;
        }
        
        while (current != null && found == false) {
            if (newNode.getContent().equals(current.getContent())) {
                found = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        
        if (found == false) {
            throw new ElementNotFoundException();
        }
        
        if (size() == 1) {
            head = tail = null;
        } else if (current.equals(head)) {
            head = current.getNext();
        } else if (current.equals(tail)) {
            tail = previous;
        } else {
            previous.setNext(current.getNext());
        }
        count--;
    }
}
