package Structures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.BinarySearchTreeADT;

/**
 * Estende a classe LinkedBinaryTree e implementa a interface BinarySearchTreeADT
 * BinarySearchTreeADT
 *
 * @author Miguel e Cristiano
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Cria uma árvore binária de pesquisa vazia
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Cria uma árvore binária de pesquisa com o elemento especificado como raiz
     *
     * @param element elemento que será a raiz da nova árvore binária de
     * pesquisa
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adiciona o elemento especificado á árvore binária de pesquisa na posição
     * apropriada tem em conta o seu conteúdo. Caso os elemento sejam iguais, o
     * novo elemento é adicionado á direita
     *
     * @param element elemento especificado a ser adicionado á árvore binária de
     * pesquisa
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
        count++;
    }

    /**
     * Remove o primeiro elemento que corresponda ao elemento alvo especificado
     * da árvore binária de pesquisa e retorna a referência do mesmo. Lança a
     * excessão ElementNotFoundException se o elemento alvo especificado não for
     * encontrado na árvore binária de pesquisa
     *
     * @param targetElement elemento alvo especificado a ser removido da árvore
     * binário
     * @return referência para o elemento a ser removido da árvore binária de
     * pesquisa
     * @throws ElementNotFoundException
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;

            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if (((Comparable) targetElement).compareTo(root.element) < 0) {
                    current = root.left;
                } else {
                    current = root.right;
                }
                while (current != null && !found) {
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;
                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;
                        if (((Comparable) targetElement).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }
                    }
                }

                if (!found) {
                    throw new ElementNotFoundException("binary search tree");
                }
            }
        }
        return result;
    }

    /**
     * Remove os elementos que correspondam ao elemento alvo especificado da
     * árvore binária de pesquisa. Lança a exceção ElementNotFoundException caso
     * o elemento alvo especificado não se encontre na árvore
     *
     * @param targetElement elemento alvo a ser removido da árvore binária de
     * pesquisa
     * @throws ElementNotFoundException
     */
    @Override
    public void removeAllOccurences(T targetElement) throws ElementNotFoundException {
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
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            if (root.left == null) {
                result = root.getElement();
                root = root.right;
            } //if
            else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.left;
                while (current.left != null) {
                    parent = current;
                    current = current.left;
                } //while
                result = current.getElement();
                parent.left = current.right;
            } //else

            count--;
        } //else

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
        T result = null;

        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            if (root.right == null) {
                result = root.getElement();
                root = root.left;
            } //if
            else {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.right;

                while (current.right != null) {
                    parent = current;
                    current = current.right;
                } //while

                result = current.getElement();
                parent.right = current.left;
            } //else

            count--;
        } //else

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
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;

            while (current.left != null) {
                current = current.left;
            }

            result = current.getElement();
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
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;

            while (current.right != null) {
                current = current.right;
            }

            result = current.getElement();
        }

        return result;
    }

    /**
     * Retorna uma referência para o nó que irá substituir o nó alvo
     * especificado a ser removido da árvore binária de pesquisa. No caso de o
     * nó alvo a remover ter dois descendentes, o sucessor Em-Ordem deste é
     * utilizado como seu substituito
     *
     * @param node nó a ser removido da árvore binária de pesquisa
     * @return referência para o nó substituidor
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;

        if ((node.left == null) && (node.right == null)) {
            result = null;

        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

}
