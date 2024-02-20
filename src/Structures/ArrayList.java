package Structures;

import Interfaces.ListADT;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

public abstract class ArrayList<T> implements ListADT<T> {

    private static final int DEFAULT_CAPACITY = 10;
    protected int rear;
    protected T[] array;

    public ArrayList() {
        this.rear = 0;
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        this.rear = 0;
        this.array = (T[]) new Object[initialCapacity];
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }

        T result = array[0];
        rear--;

        for (int i = 0; i < size(); i++) {
            array[i] = array[i + 1];
        }

        array[size()] = null;
        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }
        rear--;

        T result = array[size()];
        array[size()] = null;

        return result;
    }

    @Override
    public T remove(T object) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }
        int position = find(object);
        if (position == -1) {
            throw new ElementNotFoundException();
        }
        T result = array[position];
        rear--;
        for (int i = position; i < size(); i++) {
            array[i] = array[i + 1];
        }
        array[size()] = null;
        return result;
    }
    
    @Override
    public boolean contains(T targetElement) throws EmptyCollectionException{
         if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }
         int position = find(targetElement);
         return position == -1;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }
        return array[0];
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty() == true) {
            throw new EmptyCollectionException();
        }
        return array[size()];
    }

    @Override
    public boolean isEmpty() throws EmptyCollectionException {
        return size() == 0;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(array, size());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            s.append("\n\n[" + array[i] + "]\n\n");
        }
        return s.toString();
    }

    protected void expandCapacity() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private int find(T element) throws EmptyCollectionException {
        int current = 0, notFound = -1;
        boolean found = false;
        if (!isEmpty()) {
            while (current < size() && !found) {
                if (array[current].equals(element)) {
                    found = true;
                } else {
                    current++;
                }
            }
        }

        if (found) {
            return current;
        }

        return notFound;
    }
    
    public void set(int i, T element) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("ArrayList index out of range.");
        }
        array[i] = element;
    }
    
    public T get(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("ArrayList index out of range.");
        }
        return array[i];
    }
}
