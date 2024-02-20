package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.ListADT;
import java.util.Iterator;

public abstract class DoubleLinkedList<T> implements ListADT<T> {

    protected int count = 0;
    protected DoubleNode<T> tail, head;
    private int counter = 0;

    public DoubleLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    public DoubleNode<T> getTail() {
        return tail;
    }

    public void setTail(DoubleNode<T> tail) {
        this.tail = tail;
    }

    public DoubleNode<T> getHead() {
        return head;
    }

    public void setHead(DoubleNode<T> head) {
        this.head = head;
    }

    @Override
    public T remove(T object) throws EmptyCollectionException, ElementNotFoundException {

        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        boolean found = false;
        DoubleNode<T> current = head;
        T result = null;

        while (current != null && found == false) {
            if (current.getContent().equals(object)) {
                found = true;
                result = current.getContent();
            } else {
                current = current.getNext();
            }
        }

        if (found == false) {
            throw new ElementNotFoundException();
        }

        if (size() == 1) {
            head = tail = null;
        } else if (current.getContent().equals(head.getContent())) {
            head.getNext().setPrevious(null);
            head = head.getNext();
        } else if (current.getContent().equals(tail.getContent())) {
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
        } else {
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
        }
        count--;
        return result;
    }
    
    @Override
    public boolean contains(T targetElement) throws EmptyCollectionException{
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        boolean found = false;
        DoubleNode<T> current = head;

        while (current != null && found == false) {
            if (current.getContent().equals(targetElement)) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        
        return found == true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        DoubleNode<T> current = head;
        int contador = 0;

        if (contador < size()) {
            while (contador < size()) {
                if (current.equals(tail) && current.equals(head)) {
                    s.append("\n").append(current.getContent()).append("(HEAD)(TAIL)");
                } else if (current.equals(tail)) {
                    s.append("\n").append(current.getContent()).append("(TAIL)");
                } else if (current.equals(head)) {
                    s.append("\n").append(current.getContent()).append("(HEAD)");
                } else {
                    s.append("\n").append(current.getContent());
                }
                current = current.getNext();
                contador++;
            }
        } else {
            s.append("Nao ha elementos na lista!");
        }
        return s.toString();
    }
    
    public void toStringAsc(DoubleNode current) throws EmptyCollectionException{
       if(isEmpty()){
           throw new EmptyCollectionException();
       } else {
           System.out.println("\nNode: " + current.getContent());
           current = current.getNext();
           counter++;
           if(counter < size()){
               toStringAsc(current);
           } else {
               counter = 0;
           }
       }
    }
    
    public void toStringDes(DoubleNode current) throws EmptyCollectionException{
       if(isEmpty()){
           throw new EmptyCollectionException();
       } else {
           System.out.println("\nNode: " + current.getContent());
           current = current.getPrevious();
           counter++;
           if(counter < size()){
               toStringDes(current);
           } else {
               counter = 0;
           }
       }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T result = head.getContent();
        head = head.getNext();
        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T result = tail.getContent();
        tail = tail.getPrevious();
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        return head.getContent();
    }

    @Override
    public T last() throws EmptyCollectionException {
        return tail.getContent();
    }

    public T[] fillArray() {
        Object[] array = new Object[size()];
        DoubleNode<T> current = head;
        int contador = 0;

        if (contador < size()) {
            while (contador < size()) {
                array[contador] = current.getContent();
                current = current.getNext();
                contador++;
            }
        } else {
            System.out.println("Nao ha elementos na lista!");
        }

        return (T[]) array;
    }

    public T[] fillArray2(int quantity) {
        Object[] array = new Object[quantity];
        DoubleNode<T> current = head;
        int contador = 0;

        if (contador < size()) {
            while (contador < quantity) {
                array[contador] = current.getContent();
                current = current.getNext();
                contador++;
            }
        } else {
            System.out.println("Nao ha elementos na lista!");
        }

        return (T[]) array;
    }

    public T[] fillArray3(int pos) throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (pos >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] array = new Object[(size() - pos)];
        DoubleNode<T> current = head;
        int contador = 0;
        int index = 0;

        if (contador < size()) {
            while (contador < size()) {
                if (contador >= pos) {
                    array[index] = current.getContent();
                    index++;
                }
                current = current.getNext();
                contador++;
            }
        } else {
            System.out.println("Nao ha elementos na lista!");
        }

        return (T[]) array;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedIterator(head, size());
    }
}
