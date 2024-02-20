
package Structures;

/**
 * Extende a classe BinaryTreeNode
 * 
 * @author Miguel e Cristiano
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    
    protected HeapNode<T> parent;
    
    /**
     * Cria uma nova heap com o elemento especificado
     * 
     * @param element conteúdo a ser contido nos novos
     * nós de heap
     */
    public HeapNode(T element){
        super(element);
        this.parent = null;
    }
}
