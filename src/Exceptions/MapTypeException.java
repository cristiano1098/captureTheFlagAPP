package Exceptions;

/**
 * Exceção lançada quando ocorre um erro relacionado ao tipo de mapa.
 * Por exemplo, quando um método espera um tipo específico de mapa e recebe outro.
 * 
 * @author Cristiano e Miguel
 */
public class MapTypeException extends Exception {

    /**
     * Construtor padrão para a exceção MapTypeException.
     */
    public MapTypeException() {
        super();
    }

    /**
     * Construtor que aceita uma mensagem personalizada para a exceção MapTypeException.
     * 
     * @param msg A mensagem de erro específica.
     */
    public MapTypeException(String msg) {
        super(msg);
    }
}
