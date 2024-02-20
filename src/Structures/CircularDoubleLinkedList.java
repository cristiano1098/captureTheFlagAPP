package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;


public class CircularDoubleLinkedList<T> extends DoubleLinkedList<T> {

    public CircularDoubleLinkedList() {
        super();
    }
    
  
    public void addToHead(T data) throws ElementNotFoundException {
        if (data == null) {
            throw new ElementNotFoundException();
        }
        
        DoubleNode<T> newNode = new  DoubleNode(data);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        count++;
    }
    
  
    public void addToTail(T data) throws ElementNotFoundException {
        if (data == null) {
            throw new ElementNotFoundException();
        }
        
        DoubleNode<T> newNode = new  DoubleNode(data);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            newNode.setNext(head);
            head.setPrevious(newNode);
            tail = newNode;
        }
        count++;
    }
    
    
    @Override
    public T remove(T object) throws ElementNotFoundException, EmptyCollectionException {
        DoubleNode<T> newNode = new  DoubleNode(object);
        
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        
        boolean found = false;
        DoubleNode<T> current = head.getNext();
        T result = null;
        
        if (head.equals(newNode)) {
            found = true;
        }
        
        while (current != null && found == false) {
            if (newNode.getContent().equals(current.getContent())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        
        if (found == false) {
            throw new ElementNotFoundException();
        }
        
        if (size() == 1) {
            head = tail = null;
        } else if (current.equals(head)) {
            result = head.getContent();
            tail.setNext(head.getNext());
            head.getNext().setPrevious(tail);
            head = head.getNext();
        } else if (current.equals(tail)) {
            result = tail.getContent();
            tail.getPrevious().setNext(head);
            head.setPrevious(tail.getPrevious());
            tail = tail.getPrevious();
        } else {
            result = current.getContent();
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        count--;
        return result;
    }
}
