package Structures;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int INITIAL_CAPACITY = 5;
    private int count, front, rear;
    private T[] array;

    public CircularArrayQueue() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
        this.count = this.front = this.rear = 0;
    }

    /*
        When you add a element to the queue you are going to insert it in the rear of it.
        That´s why u need to recalculate the position on of the rear in the array, that's 
        the only thing that changes in the method
     */
    @Override
    public void enqueue(T element) {
        if (size() == array.length) {
            expandCapacity();
        }

        array[rear] = element;
        rear = (rear + 1) % array.length;
        count++;
    }

    /*
        It's the same thing that happens with the method "enqueue" but in this method it's the front
    */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T result = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        count--;
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            s.append("\n[").append(array[i]).append("]");
        }
        return s.toString();
    }

    private void expandCapacity() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[front];
            front = (front + 1) % array.length;
        }
        array = newArray;
        rear = count;
        front = 0;
    }
}
