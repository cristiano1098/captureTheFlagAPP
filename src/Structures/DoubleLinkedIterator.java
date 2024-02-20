
package Structures;

import Exceptions.NoSuchElementException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DoubleLinkedIterator<T> implements Iterator<T> {
    
    private int count; //numero de elementos na colecao
    private DoubleNode<T> current; //posicao atual na iteracao
    
    /**
    *   Cria o iterador para a colecao especifica
    */
    public DoubleLinkedIterator(DoubleNode firstNode,int count){
        this.current = firstNode;
        this.count = count; 
    }
    
    /**
    *   Retorna verdadeiro se o iterador tiver mais 
    *   elementos para devolver na iteracao
    */
    @Override
    public boolean hasNext() {
     return (current != null);   
    }

    /**
     *  Retorna o próximo objeto da coleção, se não existir,
     *  é lançada a exceção NoSuchElementException.
     */
    @Override
    public T next() {
        if(!hasNext()){
            try {
                throw new NoSuchElementException();
            } catch (NoSuchElementException ex) {
                Logger.getLogger(DoubleLinkedIterator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        T result = current.getContent();
        current = current.getNext();
        return result;
    }    
}
