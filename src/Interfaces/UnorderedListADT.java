
package Interfaces;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;


public interface UnorderedListADT<T> extends ListADT<T> {
    /**
    * Adds the specified element to this list at the proper location
    *
    * @param element the element to be added to this list
    */
    void addToFront(T element);
    void addToRear(T element);
    void addAfter(T element, T target) throws ElementNotFoundException, EmptyCollectionException;
}
