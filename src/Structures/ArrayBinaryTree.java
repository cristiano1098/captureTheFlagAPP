package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.BinaryTreeADT;
import java.util.Iterator;

/**
 * Implementa a interface BinaryTreeADT
 *
 * @author Miguel e Cristiano
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    private static final int DEFAULT_CAPACITY = 50;
    protected int count;  //número de elementos da árvore
    protected T[] tree;   //array com todos os elementos da árvore binária

    /**
     * Cria uma árvore binária vazia
     */
    public ArrayBinaryTree() {
        this.count = 0;
        this.tree = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Cria uma árvore binária de pesquisa com o elemento especificado como raiz
     *
     * @param element elemento que será a raiz da nova árvore binária de
     * pesquisa
     */
    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree[0] = element;
    }

    /*
     * Retorna o conteúdo da raiz da árvore binária
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        return tree[0];
    }

    /*
     * Retorna verdadeiro se a árvore binária estiver vazia 
     * ou falso caso contrário
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /*
     * Retorna o número de elementos da árvore binária  
     */
    @Override
    public int size() {
        return count;
    }

    /*
    * Retorna verdadeiro se encontrar o elemento alvo 
    * especificado na árvore binária. Caso contrário,
    * retorna falso
     */
    @Override
    public boolean contains(T targetElement) {
        boolean found = false;

        for (int i = 0; i < count && !found; i++) {
            if (targetElement.equals(tree[i])) {
                found = true;
            }
        }

        return found;
    }

    /*
    * Retorna uma referência para o elemento alvo especificado
    * caso este seja encontrado na árvore primária. Lança a 
    * exceção ElementNotFoundException se o elemento alvo 
    * especificado não se encontra na árvore binária
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int i = 0; i < count && !found; i++) {
            if (targetElement.equals(tree[i])) {
                found = true;
                temp = tree[i];
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return temp;
    }

    /*
    * Executa uma travessia Em-Ordem na árvore recorrendo
    * recursivamente ao método inOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        inOrder(0, templist);
        return templist.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Em-Ordem 
    * na árvore binária
     */
    protected void inOrder(int node, ArrayUnorderedList list) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inOrder((node + 1) * 2 - 1, list);
                list.addToRear(tree[node]);
                inOrder((node + 1) * (2 + 1) - 1, list);
            }
        }
    }

    /*
    * Executa uma travessia Pré-Ordem na árvore recorrendo
    * recursivamente ao método preOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        preOrder(0, templist);
        return templist.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Em-Ordem 
    * na árvore binária
     */
    protected void preOrder(int node, ArrayUnorderedList list) {
        if (node < tree.length) {
            if (tree[node] != null) {
                list.addToRear(tree[node]);
                inOrder((node + 1) * 2 - 1, list);
                inOrder((node + 1) * (2 + 1) - 1, list);
            }
        }
    }

    /*
    * Executa uma travessia Pós-Ordem na árvore recorrendo
    * recursivamente ao método postOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        postOrder(0, templist);
        return templist.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Pós-Ordem 
    * na árvore binária
     */
    protected void postOrder(int node, ArrayUnorderedList list) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inOrder((node + 1) * 2 - 1, list);
                inOrder((node + 1) * (2 + 1) - 1, list);
                list.addToRear(tree[node]);

            }
        }
    }

    /**
     * Executa uma travessia Em-Nivel na árvore
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList list = new ArrayUnorderedList();
        for (int i = 0; i < count; i++) {
            list.addToRear(tree[i]);
        }
        return list.iterator();
    }

    protected void expandCapacity() {
        T[] temp = (T[]) new Object[tree.length * 2];
        for (int i = 0; i < tree.length; i++) {
            temp[i] = tree[i];
        }
        tree = temp;
    }
}
