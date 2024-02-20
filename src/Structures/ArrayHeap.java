package Structures;

import Exceptions.EmptyCollectionException;
import Interfaces.HeapADT;

/**
 * Extende a classe ArrayBinaryTree e implementa a interface HeapADT
 *
 * @author Miguel e Cristiano
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    /**
     * Cria uma heap vazia
     */
    public ArrayHeap() {
        super();
    }

    /**
     * Adiciona o elemento especificado a heap na posição apropriada de acordo
     * com o seu valor
     *
     * @param element
     */
    @Override
    public void addElement(T element) {
        if (count == size()) {
            expandCapacity();
        }
        tree[count] = element;
        count++;

        if (count > 1) {
            heapAdd();
        }
    }

    /**
     * Remove o elemento com o menor valor na heap e 
     * retorna uma referência para o mesmo. Lança a 
     * exceção EmptyCollectionException se a heap não 
     * contiver elementos
     * 
     * @return referência para o elemento removido da heap
     * @throws EmptyCollectionException 
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        Comparable minElement = (Comparable) tree[0];

        tree[0] = tree[count - 1];
        heapRemove();
        count--;

        return (T) minElement;
    }

    @Override
    public T findMin() throws EmptyCollectionException{
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        
        return tree[0];
    }

    /**
     * Reordena a heap para manter a sua ordem (minHeap)
     */
    private void heapAdd() {
        Comparable temp;
        int next = count - 1;
        while ((next != 0) && (((Comparable) tree[next]).compareTo(tree[(next - 1) / 2]) < 0)) {
            temp = (Comparable) tree[next];
            tree[next] = tree[(next - 1) / 2];
            tree[(next - 1) / 2] = (T) temp;
            next = (next - 1) / 2;
        }
    }

    /**
     * Reordena a heap para manter a sua ordem (minHeap)
     */
    private void heapRemove() {
        Comparable temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = count;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        while ((next < count) && (((Comparable) tree[next]).compareTo(tree[node]) < 0)) {
            temp = (Comparable) tree[node];
            tree[node] = tree[next];
            tree[next] = (T) temp;
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) {
                next = count;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
    }

}
