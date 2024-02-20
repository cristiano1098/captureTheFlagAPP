
package Interfaces;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 * Interface para a implementacao de uma árvore binária
 * 
 * @author Miguel e Cristiano
 */
public interface BinaryTreeADT<T> {
    
    /**
     * Lança uma exceção caso a árvore não contenha 
     * elementos. Caso contrário retorna a referência 
     * para o elemento da raiz
     * 
     * @return referência para o elemento da raiz
     * @throws 
     */
    public T getRoot() throws EmptyCollectionException;
    
    /**
     * Retorna verdadeiro se a árvore estiver vazia 
     * e falso caso contrário
     * 
     * @return verdade se a árvore estiver vazia
     */
    public boolean isEmpty();
    
    /**
     * Retorna o número de elementos na árvore
     * 
     * @return número de elementos da árvore
     */
    public int size();
    
    /**
     * Retorna verdadeiro se algum elemento da 
     * árvore contiver o elemento especificado ou
     * falso caso contrário
     * 
     * @param targetElement elemento a ser procurado 
     * na árvore
     * @return verdade se a árvore contém o elemento
     */
    public boolean contains(T targetElement);
    
    /**
     * Retorna uma referência para elemento especificado 
     * se este for encontrado na árvore. Lança uma exceção
     * caso o elemento encontrado não seja expecificado
     * 
     * @param targetElement elemento a ser procurado
     * @return referência para o elemento especificado
     * @throws ElementNotFoundException 
     */
    public T find(T targetElement) throws ElementNotFoundException;
    
    /**
     * Retorna uma string representante da árvore
     * 
     * @return string representante da árvore
     */
    public String toString();
    
    /**
     * Executa uma travessia Em-Ordem na árvore recorrendo
     * recursivamente ao método Em-Ordem que começa com a raiz.
     * 
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorInOrder();
    
    /**
     * Executa uma travessia Pré-Ordem na árvore recorrendo
     * recursivamente ao método Pré-Ordem que começa com a raiz.
     * 
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorPreOrder();
    
    /**
     * Executa uma travessia Pós-Ordem na árvore recorrendo
     * recursivamente ao método Pós-Ordem que começa com a raiz.
     * 
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorPostOrder();
    
    /**
     * Executa uma travessia Em-Nivel na árvore
     * 
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException;
    
    
    
}
