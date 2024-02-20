package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Structures.LinearNode;
import Structures.LinkedList;

/**
 * Classe que representa um jogador no jogo.
 * Um jogador possui um nome, uma lista de bots e uma base associada.
 * 
 * @author Cristiano e Miguel 
 */
public class Player {
    
    // O nome do jogador
    private String name;
    
    // Lista de bots do jogador
    private LinkedList<Bot> bots;
    
    // A base associada ao jogador
    private Base base;
    
    /**
     * Construtor do jogador.
     * 
     * @param name O nome do jogador.
     * @param base A base associada ao jogador.
     */
    public Player(String name, Base base){
        this.name = name;
        this.bots = new LinkedList();
        this.base = base;
    }

    /**
     * Getter de nome
     * 
     * @return nome do Jogador
     */
    public String getName() {
        return name;
    }

    /**
     * Setter de nome
     * 
     * @param name nome a associar ao Jogador
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter da lista de Bots
     * 
     * @return lista de Bots
     */
    public LinkedList<Bot> getBots() {
        return bots;
    }

    /**
     * Setter da lista de Bots
     * 
     * @param bots bots a associar ao Jogador
     */
    public void setBots(LinkedList<Bot> bots) {
        this.bots = bots;
    }

    /**
     * Getter da Base do Jogador
     * 
     * @return base do jogador
     */
    public Base getBase() {
        return base;
    }

    /**
     * Setter da base do Jogador
     * 
     * @param base base a associar ao Jogador
     */
    public void setBase(Base base) {
        this.base = base;
    }
    
    /**
     * Adiciona um bot à lista de bots do jogador.
     * 
     * @param bot O bot a ser adicionado.
     * @throws ElementNotFoundException Se ocorrer uma exceção ao adicionar o bot à lista.
     */
    public void addBot(Bot bot) throws ElementNotFoundException{
        this.bots.addToTail(bot);
    }
    
    /**
     * Remove um bot da lista de bots do jogador.
     * 
     * @param bot O bot a ser removido.
     * @throws ElementNotFoundException Se o bot não for encontrado na lista.
     * @throws EmptyCollectionException Se ocorrer uma exceção ao remover o bot da lista.
     */
    public void removeBot(Bot bot) throws ElementNotFoundException, EmptyCollectionException{
        this.bots.remove(bot);
    }
    
    /**
     * Obtém o último bot da lista de bots do jogador.
     * 
     * @return O último bot da lista.
     */
    public LinearNode<Bot> getLastBot(){
        return this.bots.getTail();
    }
    
    /**
     * Realiza uma jogada usando um bot contra um inimigo.
     * 
     * @param map Mapa do jogo.
     * @param enemy Jogador inimigo.
     * @throws ElementNotFoundException Se o bot não for encontrado na lista.
     * @throws EmptyCollectionException Se ocorrer uma exceção ao remover o bot da lista.
     */
    public void useTurn(Map map, Player enemy) throws ElementNotFoundException, EmptyCollectionException {
        Bot bot = bots.getHead().getContent();

        Base enemyBase = enemy.getBase();

        int local = bot.getLocal();

        map.getNetwork().getVertex(local).removeBot(bot);

        if (bot == enemyBase.getFlag().getCarrier()) {
            local = bot.move(map, base.getId());
        } else {
            local = bot.move(map, enemyBase.getId());
        }

        Local l1 = map.getNetwork().getVertex(local);
        if (l1.hasBots()) {
            for (int i = 0; i < l1.getBots().size(); i++) {
                if (l1.getBots().get(i) == base.getFlag().getCarrier()) {
                    System.out.println("O bot " + base.getFlag().getCarrier().getId() + " perdeu a bandeira!");
                    base.getFlag().removeCarrier();
                    if (bot == enemyBase.getFlag().getCarrier()) {
                        System.out.println("O bot " + bot.getId() + " perdeu a bandeira!!");
                        enemyBase.getFlag().removeCarrier();
                    }
                }
            }
        }

        if (local == enemyBase.getId() && enemyBase.getFlag().getCarrier()== null) {
            enemyBase.getFlag().addCarrier(bot);
            System.out.println("O bot " + bot.getId() + " capturou a bandeira!");
        }

        bots.remove(bots.getHead().getContent());
        bots.addToTail(bot);
 
        l1.addBot(bot);
    }
    
    /**
     * Cria uma string com a lista de bots do jogador.
     * @return string com a lista de todos os jogadores
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\nName: ").append(this.name);
        s.append("\n========== BOTS ===========");
        s.append(this.bots.toString());
        return s.toString();
    }         
}
