package Exceptions;

/**
 * Exceção lançada quando uma coleção atinge sua capacidade máxima e não pode mais adicionar elementos.
 * 
 * @author Miguel e Cristiano
 */
public class FullCollectionException extends Exception{

    /**
     * Construtor padrão da exceção.
     */
    public FullCollectionException() {
    }

    /**
     * Construtor da exceção com uma mensagem de erro específica.
     * 
     * @param message A mensagem de erro a ser associada à exceção.
     */
    public FullCollectionException(String message) {
        super(message);
    }
    
}
