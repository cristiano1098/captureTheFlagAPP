
package Structures;


public class LinearNode<T> {
    
    private T content;
    private LinearNode<T> next;

    
    public LinearNode(T content){
        this.content = content;
        this.next = null;
    }
    
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public LinearNode<T> getNext() {
        return next;
    }

    public void setNext(LinearNode<T> next) {
        this.next = next;
    }
    
    
}
