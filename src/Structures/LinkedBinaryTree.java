package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.BinaryTreeADT;
import java.util.Iterator;

/**
 * Implementa a interface BinaryTreeADT
 *
 * @author Miguel
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;  //número de elementos da árvore
    protected BinaryTreeNode<T> root; //raiz da árvore binária

    /**
     * Cria uma árvore binária vazia
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Cria uma árvore binária de pesquisa com o elemento especificado como raiz
     *
     * @param element elemento que será a raiz da nova árvore binária de
     * pesquisa
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode(element);
    }

    /*
     * Retorna o conteúdo da raiz da árvore binária
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        return root.getElement();
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
        T temp = null;
        boolean found = false;
        try {
            temp = find(targetElement);
            found = true;
        } catch (ElementNotFoundException ex) {
            found = false;
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
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException();
        }
        return current.getElement();
    }

    /*
    * Executa uma travessia Em-Ordem na árvore recorrendo
    * recursivamente ao método inOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> list = new ArrayUnorderedList();
        inOrder(root, list);
        return list.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Em-Ordem 
    * na árvore binária
     */
    protected void inOrder(BinaryTreeNode node, ArrayUnorderedList list) {
        if (node != null) {
            inOrder(node.left, list);
            list.addToRear(node.getElement());
            inOrder(node.right, list);
        }
    }

    /*
    * Executa uma travessia Pré-Ordem na árvore recorrendo
    * recursivamente ao método preOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList();
        preOrder(root, templist);
        return templist.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Em-Ordem 
    * na árvore binária
     */
    protected void preOrder(BinaryTreeNode node, ArrayUnorderedList list) {
        if (node != null) {
            list.addToRear(node.getElement());
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }

    /*
    * Executa uma travessia Pós-Ordem na árvore recorrendo
    * recursivamente ao método postOrder que começa com a raiz.
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList();
        postOrder(root, templist);
        return templist.iterator();
    }

    /*
    * Executa, de modo recursivo, uma travessia em Pós-Ordem 
    * na árvore binária
     */
    protected void postOrder(BinaryTreeNode node, ArrayUnorderedList list) {
        if (node != null) {
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.addToRear(node.getElement());
        }
    }

    /**
     * Executa uma travessia Em-Nivel na árvore
     */
    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<BinaryTreeNode> nodes = new ArrayUnorderedList();
        ArrayUnorderedList<T> list = new ArrayUnorderedList();
        BinaryTreeNode<T> current;

        nodes.addToRear(root);

        while (!nodes.isEmpty()) {
            current = nodes.removeFirst();

            if (current != null) {
                list.addToRear(current.getElement());
                nodes.addToRear(current.left);
                nodes.addToRear(current.right);
            } else {
                list.addToRear(null);
            }
        }
        return list.iterator();
    }

    public void removeAllElements() {
        count = 0;
        root = null;
    }

    /*
     * Retorna uma string representante da árvore
     */
    public String toString() {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        preOrder(root, templist);
        return templist.toString();
    }

    /*
    * Retorna uma referência para o elemento alvo especificado caso 
    * este tenha sido encontrado
     */
    protected BinaryTreeNode findAgain(T targetElement, BinaryTreeNode next) {
        if (next == null) {
            return null;
        }
        if (next.getElement().equals(targetElement)) {
            return next;
        }
        BinaryTreeNode temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }
        return temp;
    }
}
