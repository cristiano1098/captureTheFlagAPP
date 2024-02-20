package Structures;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> front, rear;
    private int count;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode(element);

        if (isEmpty()) {
            front = rear = newNode;
            count++;
        } else {
            rear.setNext(newNode);
            rear = newNode;
            count++;
        }
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T result = front.getContent();
        front = front.getNext();
        count--;
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        return front.getContent();
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
        LinearNode<T> current = front;
        while (current != null) {
            if (current.equals(rear)) {
                s.append("\n").append(current.getContent()).append("(TAIL)");
            } else if (current.equals(front)) {
                s.append("\n").append(current.getContent()).append("(FRONT)(TAIL)");
            } else {
                s.append("\n").append(current.getContent());
            }
            current = current.getNext();
        }
        return s.toString();
    }

}
