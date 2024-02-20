package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 * Representa um bot no jogo.
 *
 * @author Cristiano e Miguel
 */
public class Bot {

    //Posição onde o bot se encontra no mapa do jogo
    private int local;

    //Algoritmo utilizado pelo bot para calcular os seu movimentos
    private Algorithm algorithm;

    //Contador estático para gerar id's únicos para cada bot
    private static int idCount = 0;

    //Identificador único do bot
    private int id;

    //Nome do jogador ao qual este bot está associado
    private final String playerName;

    /**
     * Construtor da classe Bot.
     *
     * @param player O jogador ao qual este bot está associado.
     * @param algorithm O algoritmo de movimentação utilizado pelo bot.
     */
    public Bot(Player player, Algorithm algorithm) {
        this.id = idCount++;
        this.local = player.getBase().getId();
        this.algorithm = algorithm;
        this.playerName = player.getName();
    }

    /**
     * Método que realiza o movimento do bot no mapa do jogo.
     * 
     * @param map O mapa do jogo.
     * @param id O identificador do bot.
     * @return A nova posição do bot após o movimento.
     * @throws ElementNotFoundException Se ocorrer uma exceção ao procurar elementos no mapa.
     * @throws EmptyCollectionException Se ocorrer uma exceção ao manipular coleções vazias.
     */
    public int move(Map map, int id) throws ElementNotFoundException, EmptyCollectionException {
        System.out.println("            Vez de " + this.playerName);
        System.out.println("-------------------------------------");
        System.out.println("Com o algoritmo " + String.valueOf(this.algorithm));
        System.out.print("O bot " + this.id + " deslocou-se de " + this.local);

        if (null != this.algorithm) {
            switch (this.algorithm) {
                case MINIMUMSPANNINGTREE:
                    this.local = BotAlgorithm.minimumSpanningTree(map, this.local, id);
                    break;
                case SHORTESTWEIGHTPATH:
                    this.local = BotAlgorithm.shortestWeightPath(map, this.local, id);
                    break;
                case RANDOMNEIGHBOR:
                    this.local = BotAlgorithm.randomNeighbor(map, id);
                    break;
                default:
                    break;
            }
        }

        System.out.println(" para " + this.local);
        return this.local;
    }

    /**
     * Getter para a posição local do bot.
     * 
     * @return A posição local do bot.
     */
    public int getLocal() {
        return local;
    }

     /**
     * Setter para a posição local do bot.
     * 
     * @param local A nova posição local do bot.
     */
    public void setLocal(int local) {
        this.local = local;
    }

    /**
     * Getter para o algoritmo utilizado pelo bot.
     * 
     * @return O algoritmo utilizado pelo bot.
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Setter para o algoritmo utilizado pelo bot.
     * 
     * @param algorithm O novo algoritmo utilizado pelo bot.
     */
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Getter para o identificador único do bot.
     * 
     * @return O identificador único do bot.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Método que retorna uma representação em string do objeto Bot.
     * 
     * @return Uma representação em string do objeto Bot.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID: ").append(this.id);
        s.append("\nAlgorithm: ").append(this.algorithm);
        return s.toString();
    }
}
