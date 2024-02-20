
package Structures;

import Exceptions.NoSuchElementException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArrayIterator<T> implements Iterator<T> {

    private int count; //numero de elementos na colecao
    private int current; //posicao atual na iteracao
    private Object[] array;
    
    /*
    *   Cria o iterador para a colecao especifica
    */
    public ArrayIterator(Object[] array, int count){
        this.array = array;
        this.count = count;
        this.current = 0;
    }
    
    /*
    *   Retorna verdadeiro se o iterador tiver mais 
    *   elementos para devolver na iteracao
    */
    @Override
    public boolean hasNext() {
        return (current < count);
    }

    /*
    *   Retorna o próximo objeto na iteração.
    *   Se não exitir um próximo elemento na iteracao
    *   é lançada a excecao NoSuchElementException
    */
    @Override
    public T next() {
          if(!hasNext()){
              try {
                  throw new NoSuchElementException();
              } catch (NoSuchElementException ex) {
                  Logger.getLogger(ArrayIterator.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
          T result = (T) array[current];
          current++;
          return result;
    }
}
