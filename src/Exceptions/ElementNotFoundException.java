
package Exceptions;

/**
 * Exceção lançada quando um elemento não é encontrado.
 * Esta exceção é usada quando um elemento esperado não é encontrado em uma coleção ou estrutura de dados.
 * 
 * @author Cristiano e Miguel
 */
public class ElementNotFoundException extends Exception{

    /**
     * Construtor padrão da exceção ElementNotFoundException.
     */
    public ElementNotFoundException() {
    }

    /**
     * Construtor da exceção ElementNotFoundException com uma mensagem específica.
     * 
     * @param message A mensagem que descreve a exceção.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
    
}
