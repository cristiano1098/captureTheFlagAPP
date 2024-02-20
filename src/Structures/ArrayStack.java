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
public class ArrayStack<T> implements StackADT<T> {
    
    private final int DEFAULT_CAPACITY = 10;
    protected T[] stack;
    protected int top;

    public ArrayStack(){
        this.top = 0;
        this.stack = (T[]) new Object[DEFAULT_CAPACITY];
    }
    
    
    @Override
    public void push(T element) {
        
        if(size() == stack.length){
            expandCapacity();
        }
        
        stack[top]= element;
        top++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        
        if(isEmpty()) throw new EmptyCollectionException("Stack vazia");
        
          
        top--;
        T item = stack[top];
        stack[top] = null;
        
        return item;
    }

    @Override
    public T peek() throws EmptyCollectionException{
        
        if(isEmpty()) throw new EmptyCollectionException("Stack vazia");
        
        return stack[top-1];
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < top; i++){
            s.append("Level " + (i+1) + ": " + stack[i] + "\n");
        }
        return s.toString();
    }
    

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }
    
    private void expandCapacity(){
        
        Object[] newArray = new Object[stack.length * 2];
        
        for(int i = 0; i < stack.length;i++){
            newArray[i] = stack[i];
        }      
        
        stack = (T[]) newArray;
    }
    
}
