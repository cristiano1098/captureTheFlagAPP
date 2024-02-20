package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.UnorderedListADT;
import java.util.Iterator;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Create an empty list using the default capacity
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Create an empty list using the specified capacity
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void addToFront(T element) {
        if (size() == array.length) {
            expandCapacity();
        }
        //shift elements  
        for (int i = size(); i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = element;
        rear++;
    }

    @Override
    public void addToRear(T element) {
        if (size() == array.length) {
            expandCapacity();
        }
        array[rear] = element;
        rear++;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException, EmptyCollectionException {
        
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        
        boolean found = false;
        
        if (size() == array.length) {
            expandCapacity();
        }

        
        for (int i = 0; i < size(); i++) {
            if(array[i].equals(target)){
                for ( int pos = size(); pos > (i+1) ;pos--){ //deslocamento dos elementos
                    array[pos] = array[pos-1];
                }
                
                array[i + 1] = element;
                rear++;
                found = true;
                break;
            }
        }
        
        if(!found){
            throw new ElementNotFoundException();
        }
        
    }
}
