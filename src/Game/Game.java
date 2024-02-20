package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.MapTypeException;
import Structures.LinkedList;
import java.util.Random;

/**
 * Classe que representa o jogo.
 * O jogo possui um mapa, uma lista de jogadores e um contador de rodadas.
 * 
 * @author Miguel e Cristiano
 */
public class Game {

    // O mapa do jogo
    private Map map;
    
    // A lista de jogadores
    private LinkedList<Player> players;
    
    // O contador de rodadas
    private int roundCont;

    /**
     * Construtor do jogo que inicializa o mapa, a lista de jogadores e o contador de rodadas.
     */
    public Game() {
        this.map = new Map();
        this.players = new LinkedList();
        this.roundCont = 0;
    }

    /**
     * Gera um número inteiro aleatório até o valor máximo especificado.
     * 
     * @param max O valor máximo para o número aleatório.
     * @return O número inteiro aleatório gerado.
     */
    private int randomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * Cria um mapa com o tamanho, tipo e densidade especificados.
     * 
     * @param size O tamanho do mapa.
     * @param type O tipo do mapa ("directional" ou "bidirectional").
     * @param density A densidade do mapa.
     * @throws MapTypeException Se o tipo de mapa especificado for inválido.
     */
    public void createMap(int size, String type, float density) throws MapTypeException {
        map.setSize(size);
        for (int i = 0; i < size; i++) {
            Local local = new Local(i);
            map.addLocal(local);
        }
        int numArestas = 0;
        if (type.equals("directional")) {
            numArestas = (int) (density * (size * (size - 1)));
        } else if (type.equals("bidirectional")) {
            numArestas = (int) (density * (size * (size - 1)) / 2);
        } else {
            throw new MapTypeException("Tipo de mapa inválido");
        }
        int count = 0;
        while (count < numArestas) {
            Random random = new Random();
            int r1 = random.nextInt(size);
            int r2 = random.nextInt(size);
            if (r1 != r2) {
                if (!map.getNetwork().hasEdge(r1, r2)) {
                    double distance = randomInt(15) + 1;
                    if (type.equals("bidirecional")) {
                        map.getNetwork().addEdge(r2, r1, distance);
                    } else {
                        map.getNetwork().addEdgeDiretional(r1, r2, distance);
                    }
                    count++;
                }
            }
        }
        
        System.out.println("\nMapa criado com sucesso!");
    }

    /**
     * Executa um turno do jogo para o jogador atual.
     *
     * @param roundNumber O número da rodada atual.
     * @return 1 se o jogador ganhou, 0 se o jogo continua.
     */
    public int turn(int round) throws ElementNotFoundException, EmptyCollectionException {
        Player player = players.get(round % 2);
        Player enemy = players.get((round + 1) % 2);
        player.useTurn(map, enemy);
        if (player.getLastBot().getContent().getLocal() == player.getBase().getId() && player.getLastBot().getContent() == enemy.getBase().getFlag().getCarrier()) {
            System.out.println("O bot de " + player.getName() + " chegou a sua base com a \nbandeira de " + enemy.getName() + " vencendo o jogo!");
            return 1;
        }
        return 0;
    }

    /**
     * Getter da lista de Jogadores.
     * 
     * @return A lista de jogadores.
     */
    public LinkedList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Realiza uma "moeda ao ar" para decidir a ordem dos jogadores.
     * 
     * @throws ElementNotFoundException Se ocorrer uma exceção ao remover o jogador da lista.
     * @throws EmptyCollectionException Se a lista de jogadores estiver vazia.
     */
    public void selectStartingPlayer() throws ElementNotFoundException, EmptyCollectionException {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 1) {
            Player player = players.getHead().getContent();
            players.remove(players.getHead().getContent());
            players.addToTail(player);
        } else {
        }
    }

    /**
     * Cria um novo jogador com o nome especificado e adiciona-o à lista de jogadores.
     * 
     * @param playerName O nome do novo jogador.
     * @throws ElementNotFoundException Se ocorrer uma exceção ao adicionar o jogador à lista.
     */
    public void createPlayer(String playerName) throws ElementNotFoundException {
        Flag flag = new Flag();
        Base base = new Base(flag);
        Player player = new Player(playerName, base);
        players.addToTail(player);
    }

    /**
     * Obtém o mapa do jogo.
     * 
     * @return O mapa do jogo.
     */
    public Map getMap() {
        return map;
    }
}
