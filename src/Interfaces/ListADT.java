package Interfaces;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {
    
   /**
    * Remove e retorna o primeiro elemento da lista
    * 
    * @return primeiro elemento da lista
    * @throws EmptyCollectionException 
    */
   public T removeFirst() throws EmptyCollectionException;
   
   /**
    * Remove e retorna o último elemento da lista
    * 
    * @return o último elemento da lista
    * @throws EmptyCollectionException 
    */
   public T removeLast() throws EmptyCollectionException;
   
   /**
    * Remove e retorna o elemento especificado da lista
    * 
    * @param object elemento a ser removido da lista
    * @return elemento especificado removido da lista
    * @throws EmptyCollectionException
    * @throws ElementNotFoundException 
    */
   public T remove(T object) throws EmptyCollectionException, ElementNotFoundException;
   
   /**
    * Retorna a referência para o primeiro elemento da lista
    * 
    * @return referência para o primeiro elemento da lista
    * @throws EmptyCollectionException 
    */
   public T first() throws EmptyCollectionException;
   
   /**
    * Retorna a referência para o último elemento da lista
    * 
    * @return referência para o último elemento da lista
    * @throws EmptyCollectionException 
    */
   public T last() throws EmptyCollectionException;
   
   /**
    * Retorna verdadeiro se a lista não contiver elementos. 
    * Retorna falso caso contrário
    * 
    * @return verdadeiro se a lista estiver vazia
    * @throws EmptyCollectionException 
    */
   public boolean isEmpty() throws EmptyCollectionException;
   
   /**
    * Retorna o número de elementos da lista
    * 
    * @return número de elementos da lista
    */
   public int size();
   
   /**
    * Retorna verdadeiro se a lista contiver o elemento especificado.
    * Caso contrário retorna falso
    * 
    * @param targetElement elemento a ser encontrado na lista
    * @return verdadeiro se o elemento pertencer a lista
    * @throws EmptyCollectionException
    */
   public boolean contains(T targetElement) throws EmptyCollectionException;
   
   /**
    * Retorna um iterador para os elementos da lista
    * 
    * @return iterador para os elementos da lista
    */
   public Iterator<T> iterator();
   
   /**
    * Retorna uma string representante da lista
    * 
    * @return string representante da lista
    */
   public String toString();
}
