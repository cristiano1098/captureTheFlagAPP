
package Interfaces;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 * Interface para a implementacao de uma árvore binária de pesquisa
 * 
 * @author Miguel e Cristiano
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {
    
    /**
     * Adiciona o elemento especificado a árvore na localização adequada
     * 
     * @param element elemento a ser adicionado a árvore
     */
    public void addElement(T element);
    
    /**
     * Remove o elemento especificado da árvore e retorna-o. 
     * 
     * @param targetElement elemento a ser removido da árvore
     * @return elemento removido da árvore
     * @throws ElementNotFoundException, EmptyCollectionException
     */
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException;
    
    /**
     * Remove todas as ocorrências do elemento especificado da árvore
     * 
     * @param targetElement elemento que terá 
     * todas as suas ocorrências removidas
     * @throws ElementNotFoundException, EmptyCollectionException
     */
    public void removeAllOccurences(T targetElement) throws ElementNotFoundException, EmptyCollectionException;
    
    /**
     * Remove e retorna o elemento mais pequeno da árvore
     * 
     * @return elemento mais pequeno da árvore
     * @throws EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException;
    
    /**
     * Remove e retorna o maior elemento da árvore
     * 
     * @return maior elemento da árvore
     * @throws EmptyCollectionException
     */
    public T removeMax() throws EmptyCollectionException;
    
    /**
     * Retorna uma referência para o elemento mais pequeno da árvore
     * 
     * @return referência para o elemento mais pequeno da árvore
     * @throws EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;
    
    /**
     * Retorna uma referência para o maior elemento da árvore
     * 
     * @return referência para o maior elemento da árvore
     * @throws EmptyCollectionException
     */
    public T findMax() throws EmptyCollectionException;
}
