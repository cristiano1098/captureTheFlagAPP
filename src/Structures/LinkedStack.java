/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

import Interfaces.StackADT;
import Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public class LinkedStack<T> implements StackADT<T> {

    private int count;
    private LinearNode<T> top;

    public LinkedStack() {
        this.count = 0;
        this.top = null;
    }

    @Override
    public void push(T element) {

        LinearNode<T> newNode = new LinearNode(element);
        newNode.setNext(top);
        top = newNode;

        count++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack Vazia");
        }
        
        T result = top.getContent();
        top = top.getNext();
        count--;
        
        return result;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        return top.getContent();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        LinearNode<T> current = top;
        int cont = size();
        
        while(current != null){
            //System.out.println("Level " + cont + ": " + current.getContent());
            s.append("\nLevel " + cont + ": " + current.getContent());
            current = current.getNext();
            cont--;
        }
        
        return s.toString();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

}
