package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Structures.LinkedList;

/**
 * Classe que representa um local no mapa do jogo.
 * Um local pode conter bots e indicar se possui uma bandeira ou está ocupado.
 * 
 * @author Cristiano Rocha e Miguel Vieira
 */
public class Local {
    
    // O ID único do local
    private int id;
        
    // Lista de bots presentes no local
    private LinkedList<Bot> bots;
    
    /**
     * Construtor do Local.
     * 
     * @param id O ID único do local.
     */
    public Local(int id){
        this.id = id;
        this.bots = new LinkedList();
    }
    
    /**
     * Getter de ID
     * 
     * @return id do Local
     */
    public int getId() {
        return id;
    }

    /**
     * Setter do id do Local
     * @param id id do Local
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Adiciona um bot ao local.
     * 
     * @param bot O bot a ser adicionado.
     * @throws ElementNotFoundException Se ocorrer uma exceção ao adicionar o bot à lista.
     */
    public void addBot(Bot bot) throws ElementNotFoundException{
        bots.addToTail(bot);
    }
    

    /**
     * Remove um bot do local.
     * 
     * @param bot O bot a ser removido.
     * @throws ElementNotFoundException Se o bot não for encontrado na lista.
     * @throws EmptyCollectionException Se ocorrer uma exceção ao remover o bot da lista.
     */

    public void removeBot(Bot bot) throws ElementNotFoundException, EmptyCollectionException{
        bots.remove(bot);
    }
    
    /**
     * Verifica se o local possui bots.
     * 
     * @return true se o local possuir bots, false caso contrário.
     */
    public boolean hasBots(){
        return !bots.isEmpty();
    }
    
    /**
     * Obtém a lista de bots presentes no local.
     * 
     * @return A lista de bots presentes no local.
     */
    public LinkedList<Bot> getBots(){
        return this.bots;
    }
    
    /**
     * Retorna uma representação em string do local, incluindo o seu ID, se possui bandeira e se está ocupado.
     * 
     * @return Uma string representativa do local.
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\nID: ").append(this.id);
        s.append("\n: ").append(this.id).append("\n"); 
        return s.toString();
    }
}
