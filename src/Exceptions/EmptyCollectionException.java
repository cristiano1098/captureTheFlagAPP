
package Exceptions;

/**
 * Exceção lançada quando uma coleção está vazia e uma operação que requer elementos é realizada.
 * 
 * @author Miguel e Cristiano
 */
public class EmptyCollectionException extends Exception{

    /**
     * Construtor padrão para a exceção EmptyCollectionException.
     */
    public EmptyCollectionException() {
    }

    /**
     * Construtor que permite passar uma mensagem personalizada para a exceção EmptyCollectionException.
     * 
     * @param message A mensagem de erro associada à exceção.
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
    
}
