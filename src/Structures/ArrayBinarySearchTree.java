package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.BinarySearchTreeADT;
import java.util.Iterator;

/**
 * Estende a classe ArrayBinaryTree e implementa a interface BinarySearchTreeADT
 *
 * @author Miguel e Cristiano
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {

    private int height;
    private int maxIndex;

    /**
     * Cria uma árvore binária de pesquisa vazia
     */
    public ArrayBinarySearchTree() {
        super();
        this.height = 0;
        this.maxIndex = -1;
    }

    /**
     * Cria uma árvore binária de pesquisa com o elemento especificado como raiz
     *
     * @param element elemento que será a raiz da nova árvore binária de
     * pesquisa
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        this.height = 1;
        this.maxIndex = 0;
    }

    /**
     * Adiciona o elemento especificado á árvore binária de pesquisa na posição
     * apropriada dado o seu valor. De destacar que para elementos de igual
     * valor são adicionados a direita
     *
     * @param element elemento a ser adicionado á árvore binária de pesquisa
     */
    @Override
    public void addElement(T element) {
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        Comparable<T> tempelement = (Comparable<T>) element;

        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) { //vai esquerda
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else { // vai direita  
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;

                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    /**
     * Remove o primeiro elemento que corresponder com o elemento alvo
     * especificado da árvore binária de pesquisa e retorna uma referência para
     * este. Lança a exceção ElementNotFoundException caso o elemento alvo
     * especificado não se encontre na árvore binária de pesquisa. Lança a
     * exceção EmptyCollectionException caso a árvore binária de pesquisa esteja
     * vazia.
     *
     * @param targetElement elemento alvo especificado a remover da árvore
     * binária de pesquisa
     * @return referência para o elemento removido da árvore binária de pesquisa
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        T result = null;
        boolean found = false;

        if (isEmpty()) {
            throw new EmptyCollectionException("binary tree is empty");
        }

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(((BinaryTreeNode) tree[i]).element)) {
                found = true;
                result = tree[i];
                replace(i);
                count--;
            }
        }

        if (!found) {
            throw new ElementNotFoundException("element not found in the binary tree");
        }

        int temp = maxIndex;
        maxIndex = -1;
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return result;
    }

    /**
     * Remove todos os elementos que correspondam ao elemento alvo especificado
     * da árvore binária. Lança a exceção ElementNotFoundException caso o
     * elemento alvo especificado não se encontre na árvore binária de pesquisa.
     * Lança a exceção EmptyCollectionException caso a árvore binária de
     * pesquisa esteja vazia.
     *
     * @param targetElement elementos cujo todas as ocorrencias seráo removidas
     * da árvore binária de pesquisa
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    @Override
    public void removeAllOccurences(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        removeElement(targetElement);

        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    /**
     * Remove o nó com o valor mais baixo da árvore binária de pesquisa e e
     * retorna a referência para este. Lança a exceção EmptyCollectionException
     * se a árvore binária de pesquisa estiver vazia
     *
     * @return referência para o elemento a ser removido da árvore binária de
     * pesquisa
     * @throws EmptyCollectionException
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("binary tree");
        }

        // Encontra o nó com o valor mais baixo (o mais à esquerda na árvore)
        int currentIndex = 0;
        while (tree[currentIndex * 2 + 1] != null) {
            currentIndex = currentIndex * 2 + 1;
        }

        T result = ((BinaryTreeNode<T>) tree[currentIndex]).element;
        replace(currentIndex); // Remove o nó encontrado

        return result;
    }

    /**
     * Remove o nó com o valor mais alto da árvore binária de pesquisa e e
     * retorna a referência para este. Lança a exceção EmptyCollectionException
     * se a árvore binária de pesquisa estiver vazia
     *
     * @return referência para o elemento a ser removido da árvore binária de
     * pesquisa
     * @throws EmptyCollectionException
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        // Encontra o nó com o valor mais alto (o mais à direita na árvore)
        int currentIndex = 0;
        while (tree[currentIndex * 2 + 2] != null) {
            currentIndex = currentIndex * 2 + 2;
        }

        T result = ((BinaryTreeNode<T>) tree[currentIndex]).element;
        replace(currentIndex); // Remove o nó encontrado

        return result;
    }

    /**
     * Retorna o elemento com o valor mais baixo na árvore binária de pesquisa.
     * Não remove o nó da árvore binária de pesquisa. Lança a exceção
     * EmptyCollectionException se a árvore binária de pesquisa estiver vazia
     *
     * @return elemento com o valor mais baixo da árvore binária de pesquisa
     * @throws EmptyCollectionException
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("binary tree");
        } else {
            int currentIndex = 0;
            while ((currentIndex * 2 + 1 <= maxIndex) && (tree[currentIndex * 2 + 1] != null)) {
                currentIndex = currentIndex * 2 + 1;
            }
            result = tree[currentIndex];
        }
        return result;
    }

    /**
     * Retorna o elemento com o valor mais alto na árvore binária de pesquisa.
     * Não remove o nó da árvore binária de pesquisa. Lança a exceção
     * EmptyCollectionException se a árvore binária de pesquisa estiver vazia
     *
     * @return elemento com o valor mais alto da árvore binária de pesquisa
     * @throws EmptyCollectionException
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException("binary tree");
        } else {
            int currentIndex = 0;
            while ((currentIndex * 2 + 2 <= maxIndex) && (tree[currentIndex * 2 + 2] != null)) {
                currentIndex = currentIndex * 2 + 2;
            }
            result = tree[currentIndex];
        }
        return result;
    }

    protected void replace(int targetIndex) throws EmptyCollectionException {
        int currentIndex, parentIndex, temp, oldIndex, newIndex;
        ArrayUnorderedList oldlist = new ArrayUnorderedList();
        ArrayUnorderedList newlist = new ArrayUnorderedList();
        ArrayUnorderedList templist = new ArrayUnorderedList();
        Iterator oldIt, newIt;

        // se o elemento alvo é uma folha
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } // se o elemento alvo é uma folha
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } // se o elemento alvo tem apenas um filho à esquerda
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {

            // preenche a newlist com os indices do nós que vão substituir os indices correspondentes na oldlist
            // preenche a newlist
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // preenche a oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // faz a reposição
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = ((Integer) oldIt.next()).intValue();
                newIndex = ((Integer) newIt.next()).intValue();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // se o nó alvo apenas tem um filho direito
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {

            // preenche a newlist com os indices do nós que vão substituir os indices correspondentes na oldlist
            // preenche a newlist
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                newlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // preenche a oldlist
            currentIndex = targetIndex;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
                currentIndex = ((Integer) templist.removeFirst()).intValue();
                oldlist.addToRear(new Integer(currentIndex));
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(new Integer(currentIndex * 2 + 1));
                    templist.addToRear(new Integer(currentIndex * 2 + 2));
                }
            }

            // faz a reposição
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = ((Integer) oldIt.next()).intValue();
                newIndex = ((Integer) newIt.next()).intValue();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } // o nó alvo tem dois filhos
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            // substituição do indice da raiz da sub-árvore
            int currentRoot = currentIndex;

            // se o currentIndex tiver um filho direito
            if (tree[currentRoot * 2 + 2] != null) {

                // preenche a newlist com os indices do nós que vão substituir os indices correspondentes na oldlist
                // preenche a newlist
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    newlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // preenche a oldlist
                currentIndex = currentRoot;
                templist.addToRear(new Integer(currentIndex));
                while (!templist.isEmpty()) {
                    currentIndex = ((Integer) templist.removeFirst()).intValue();
                    oldlist.addToRear(new Integer(currentIndex));
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(new Integer(currentIndex * 2 + 1));
                        templist.addToRear(new Integer(currentIndex * 2 + 2));
                    }
                }

                // faz a reposição
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = ((Integer) oldIt.next()).intValue();
                    newIndex = ((Integer) newIt.next()).intValue();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }

    }
}
