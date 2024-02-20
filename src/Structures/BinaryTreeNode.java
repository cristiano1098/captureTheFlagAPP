
package Structures;

/**
 * Representa um nó numa árvore binária com um filho a 
 * esquerda e outro a direita
 * 
 * @author Miguel, Cristiano
 */
public class BinaryTreeNode<T> {
    
    protected T element;  //conteúdo do nó
    protected BinaryTreeNode<T> right, left; //nós filhos direito e esquerdo
    
    /**
     * Cria um novo nó de árvore com o conteúdo especificado
     * 
     * @param element conteúdo do nó de árvore
     */
    public BinaryTreeNode(T element){
        this.element = element;
        this.right = this.left = null;
    }
    
    /**
     * Retorna o número de filhos do nó de árvore.
     * 
     * @return número de filhos do nó de árvore
     */
    public int numChildren(){
        int counter = 0;
        if(left != null){
            counter = 1 + left.numChildren();
        }
        if(right != null){
            counter = counter + 1 + right.numChildren();
        }
        return counter;
    }

    /**
     * Retorna o conteúdo do nó
     * 
     * @return  conteúdo do nó
     */
    public T getElement() {
        return element;
    }

    /**
     * Define qual o conteúdo do nó
     * 
     * @param element conteúdo do nó
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Retorna o nó de árvore binário filho direito do nó
     * 
     * @return nó de árvore binário filho direito
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Define o nó de árvore binário filho direito do nó
     * 
     * @param right nó de árvore binário filho direito
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Retorna o nó de árvore binário filho esquerdo do nó
     * 
     * @return nó de árvore binário filho esquerdo
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Define o nó de árvore binário filho esquerdo do nó
     * 
     * @param right nó de árvore binário filho esquerdo
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }   
}
