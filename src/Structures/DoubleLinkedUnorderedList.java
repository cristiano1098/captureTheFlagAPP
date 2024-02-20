package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.UnorderedListADT;


public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode(element);

        if (isEmpty()) {
            tail = head = newNode;
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        count++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode(element);

        if (isEmpty()) {
            tail = head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        count++;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException, EmptyCollectionException {
       boolean found = false;
       DoubleNode<T> current = head;
       DoubleNode<T> newNode = new DoubleNode(element);
       
       if(isEmpty()){
          throw new EmptyCollectionException();
       }
       
       while(current != null && ! found){
           if(current.getContent().equals(target)){
               newNode.setNext(current.getNext());
               newNode.setPrevious(current);
               current.setNext(newNode);
               current.getNext().setPrevious(newNode);
               if(current.equals(tail)){
                   tail = newNode;
               }
               found = true;
               count++;
           } 
           current = current.getNext();
       }
       
       if(!found){
           throw new ElementNotFoundException();
       }
    }
    
}
