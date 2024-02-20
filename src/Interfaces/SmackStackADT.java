package Interfaces;

import Exceptions.EmptyCollectionException;

public interface SmackStackADT<T> extends StackADT<T> {
    public T smack() throws EmptyCollectionException;
}
