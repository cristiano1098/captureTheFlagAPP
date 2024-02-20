package Exceptions;

/**
 * Exceção lançada quando não é possível encontrar um elemento em uma coleção.
 * Esta exceção é uma subclasse de Exception.
 * 
 * @author Miguel e Cristiano
 */
public class NoSuchElementException extends Exception{

    /**
     * Construtor padrão da exceção NoSuchElementException.
     */
    public NoSuchElementException() {
    }

    /**
     * Construtor da exceção NoSuchElementException com uma mensagem específica.
     * 
     * @param message A mensagem detalhando a exceção.
     */
    public NoSuchElementException(String message) {
        super(message);
    }
    
}
