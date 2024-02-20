
package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;


public class LinkedList<T> {

    protected LinearNode<T> head, tail;
    protected int count = 0;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public LinearNode<T> getHead() {
        return head;
    }

    public void setHead(LinearNode<T> head) {
        this.head = head;
    }

    public LinearNode<T> getTail() {
        return tail;
    }

    public void setTail(LinearNode<T> tail) {
        this.tail = tail;
    }

    public void addToHead(T data) throws ElementNotFoundException {
        if(data == null) throw new ElementNotFoundException();
        
        LinearNode<T> newNode = new LinearNode(data);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        count++;
    }

    public void addToTail(T data) throws ElementNotFoundException {
         if(data == null) throw new ElementNotFoundException();
        
        LinearNode<T> newNode = new LinearNode(data);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        count++;
    }

    public void remove(T data) throws ElementNotFoundException, EmptyCollectionException {
        LinearNode<T> newNode = new LinearNode(data);

        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        boolean found = false;
        LinearNode<T> previous = null;
        LinearNode<T> current = head;

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
            tail.setNext(null);
        } else {
            previous.setNext(current.getNext());
        }
        count--;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        LinearNode<T> current = head;
        int contador = 1;

        if (contador <= count) {
            while (contador <= count) {
                //s.append("\nNode " + contador + ": \n" + current.getContent() + "\n\n");
                s.append("\n" + current.getContent() + "\n");
                current = current.getNext();
                contador++;
            }
        } else {
            s.append("Nao ha elementos na lista!");
        }
        return s.toString();
    }
    

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public T get(int index) {
        if (index >= 0 && index < this.size()) {
            LinearNode<T> current = this.head;
            for(int i = 0; i < index; ++i) {
                current = current.getNext();
            }
            return current.getContent();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
}
