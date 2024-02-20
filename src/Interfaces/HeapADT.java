
package Interfaces;

import Exceptions.EmptyCollectionException;

/**
 * Interface para a implementação de uma heap. 
 * Extende a interface BinaryTreeADT
 * 
 * @author Miguel e Cristiano
 */
public interface HeapADT<T> extends BinaryTreeADT<T>{
    
    /**
     * Adiciona o elemento especificado a heap
     * 
     * @param element elemento a ser adicionado a heap
     */
    public void addElement(T element);
    
    /**
     * Remove da heap o elemento com o valor mais baixo
     * 
     * @return o elemento com o valor mais baixo da heap
     * @throws EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException;
    
    /**
     * Retorna a referência para o elemento com o valor
     * mais baixo na heap
     * 
     * @return referência para o elemento com o valor
     * mais baixo na heap
     * @throws EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;
}
