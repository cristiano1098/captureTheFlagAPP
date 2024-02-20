package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.MapTypeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe que representa os menus(conj. de menus) do jogo.
 * Este menu oferece opções para iniciar um novo jogo, criar um novo mapa,
 * importar um mapa existente, criar jogadores e iniciar o jogo.
 * 
 * @author Miguel e Cristiano
 */
public class Menu {

    //Leitor de inputs do teclado
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Método que exibe o menu principal do jogo.
     * Permite ao utilizador escolher entre iniciar um novo jogo ou sair.
     * 
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     */
    public void mainMenu() throws IOException, MapTypeException, ElementNotFoundException, EmptyCollectionException, InterruptedException {
        int opt = 0;

        do {
            System.out.println("############ MENU PRINCIPAL ############\n");
            System.out.println("1. Novo Jogo");
            System.out.println("2. Sair\n");
            System.out.print("R: ");
            opt = Integer.parseInt(reader.readLine());
        } while (opt > 2 || opt < 1);

        if (opt == 1) {
            newGame();
        } else {
            System.out.println("\nA sair...");
        }
    }

    /**
     * Método que inicia um novo jogo.
     * Permite ao utilizador escolher entre criar um novo mapa ou importar um mapa existente.
     * 
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     */
    public void newGame() throws IOException, MapTypeException, ElementNotFoundException, EmptyCollectionException, InterruptedException {
        Game game = new Game();

        int opt = 0;
        do {
            System.out.println("\n\n############ NOVO JOGO ############\n");
            System.out.println("1. Novo Mapa");
            System.out.println("2. Carregar Mapa\n");
            System.out.print("R: ");
            opt = Integer.parseInt(reader.readLine());
        } while (opt > 2 || opt < 1);

        switch (opt) {
            case 1:
                newMap(game);
                break;
            case 2:
                importMap(game);
                createPlayers(game);
                startGame(game);
                break;
        }
    }

     /**
     * Método que cria um novo mapa para o jogo.
     * Permite ao usuário definir o número de locais, o tipo de caminho e a densidade do mapa.
     * 
     * @param game O jogo para o qual o mapa será criado.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     */
    public void newMap(Game game) throws IOException, MapTypeException, ElementNotFoundException, EmptyCollectionException, InterruptedException {

        int numLocals = 0, saveMap = 0, pathType;
        float density = 0;

        System.out.println("\n\n############ NOVO MAPA ############\n");

        do {
            System.out.println("Quantos locais(vertices) tera o mapa?");
            System.out.print("Numero locais: ");
            numLocals = Integer.parseInt(reader.readLine());
        } while (numLocals < 1);

        System.out.println("\n-------------------------------------");

        do {
            System.out.println("\nQue tipos de caminho tera o mapa?\n");
            System.out.println("1. Direcional (->)");
            System.out.println("2. Bidirecional (<->)");
            System.out.print("\nTipo de caminho: ");
            pathType = Integer.parseInt(reader.readLine());
        } while (pathType > 2 || pathType < 1);

        String pathString;
        if (pathType == 1) {
            pathString = "directional";
        } else {
            pathString = "bidirectional";
        }

        System.out.println("\n-------------------------------------");

        do {
            System.out.println("\nQual sera a densidade de arestas do \nmapa? (0-100%)");
            System.out.print("\nDensidade: ");
            density = Float.parseFloat(reader.readLine());
        } while (density > 100 || density < 0);

        //ABRE JANELA A MOSTRAR O MAPA
        density = density / 100;

        System.out.println("\n-------------------------------------");
        System.out.println("\nA criar o mapa...\n");

        game.createMap(numLocals, pathString, density);

        System.out.println("\n-------------------------------------");

        do {
            System.out.println("\nDeseja guardar o mapa?\n");
            System.out.println("1. Sim");
            System.out.println("2. Nao\n");
            System.out.print("R: ");
            saveMap = Integer.parseInt(reader.readLine());
        } while (saveMap > 2 || saveMap < 1);

        switch (saveMap) {
            case 1:
                exportMap(game);
                createPlayers(game);
                startGame(game);
                break;
            case 2:
                createPlayers(game);
                startGame(game);
                break;
        }

    }

    /**
     * Método que importa um mapa existente para o jogo.
     * 
     * @param game O jogo para o qual o mapa será importado.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public void importMap(Game game) throws IOException {
        System.out.println("\n-------------------------------------");
        System.out.print("\nInsira o nome do ficheiro: ");
        String fileName = reader.readLine();
        fileName = fileName + ".json";
        game.getMap().importMap(fileName);
        
    }

    /**
     * Método que exporta o mapa atual do jogo.
     * 
     * @param game O jogo cujo mapa será exportado.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    public void exportMap(Game game) throws IOException {
        System.out.println("\n-------------------------------------");
        System.out.print("\nInsira o nome do ficheiro: ");
        String fileName = reader.readLine();
        game.getMap().exportMap(fileName);
    }

     /**
     * Método que cria jogadores para o jogo.
     * Permite ao usuário definir o nome, a localização da bandeira e o número de bots para cada jogador.
     * 
     * @param game O jogo para o qual os jogadores serão criados.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     */
    public void createPlayers(Game game) throws IOException, ElementNotFoundException {
        int bots1, bots2, base1, base2;
        String name1, name2;

        System.out.println("\n\n####### PERSONALIZAR JOGADORES #######");
        System.out.println("\nInserir dados Jogador 1");
        System.out.print("Nome: ");
        name1 = reader.readLine();

        game.createPlayer(name1);

        do {
            System.out.print("Localizacao Bandeira (" + game.getMap().getSize() + "): ");
            base1 = Integer.parseInt(reader.readLine());
        } while (base1 <= 0 || base1 > game.getMap().getSize());

        game.getPlayers().getHead().getContent().getBase().setId(base1 - 1);

        do {
            System.out.print("Numero de bots: ");
            bots1 = Integer.parseInt(reader.readLine());
        } while (bots1 <= 0);

        createBots(bots1, 1, game, base1 - 1);

        System.out.println("\n-------------------------------------");

        System.out.println("\nInserir dados Jogador 2");
        System.out.print("Nome: ");
        name2 = reader.readLine();

        game.createPlayer(name2);

        do {
            System.out.print("Localizacao Bandeira (" + game.getMap().getSize() + "): ");
            base2 = Integer.parseInt(reader.readLine());
        } while (base2 <= 0 || base2 > game.getMap().getSize() || base2 == base1);

        game.getPlayers().getTail().getContent().getBase().setId(base2 - 1);

        do {
            System.out.print("Numero de bots: ");
            bots2 = Integer.parseInt(reader.readLine());
        } while (bots2 <= 0);

        createBots(bots2, 2, game, base2 - 1);

        System.out.println("\n-------------------------------------");
    }

    /**
     * Método que cria bots para um jogador.
     * Permite ao usuário escolher o algoritmo de movimentação para cada bot.
     * 
     * @param numBots O número de bots a serem criados.
     * @param playerNum O número do jogador para o qual os bots serão criados.
     * @param game O jogo ao qual os bots serão adicionados.
     * @param baseLocation A localização da base do jogador.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     */
    public void createBots(int numBots, int playerNum, Game game, int baseLocation) throws IOException, ElementNotFoundException {

        int opt = 0;

        if (playerNum == 1) {
            for (int i = 1; i <= numBots; i++) {

                do {
                    System.out.println("\n========== BOT " + i + " ==========");
                    System.out.println("Algoritmo para o bot " + i + ": \n");
                    System.out.println("1. Caminho mais curto");
                    System.out.println("2. Arvore custo minimo");
                    System.out.println("3. Vizinho Aleatorio\n");
                    System.out.print("Algoritmo: ");
                    opt = Integer.parseInt(reader.readLine());
                } while (opt > 3 || opt < 1);

                Algorithm algorithm = Algorithm.SHORTESTWEIGHTPATH; //algoritmo padrao

                switch (opt) {
                    case 1 ->
                        algorithm = Algorithm.SHORTESTWEIGHTPATH;
                    case 2 ->
                        algorithm = Algorithm.MINIMUMSPANNINGTREE;
                    case 3 ->
                        algorithm = Algorithm.RANDOMNEIGHBOR;
                }

                Bot bot = new Bot(game.getPlayers().getHead().getContent(), algorithm);
                game.getPlayers().getHead().getContent().addBot(bot);
                game.getMap().getLocal(baseLocation).addBot(bot);
            }
        } else {
            for (int i = 1; i <= numBots; i++) {

                do {
                    System.out.println("\n========== BOT " + i + " ==========");
                    System.out.println("Algoritmo para o bot " + i + ": \n");
                    System.out.println("1. Caminho mais curto");
                    System.out.println("2. Arvore custo minimo");
                    System.out.println("3. Vizinho Aleatorio\n");
                    System.out.print("Algoritmo: ");
                    opt = Integer.parseInt(reader.readLine());
                } while (opt > 3 || opt < 1);

                Algorithm algorithm = Algorithm.SHORTESTWEIGHTPATH; //algoritmo padrao

                switch (opt) {
                    case 1 ->
                        algorithm = Algorithm.SHORTESTWEIGHTPATH;
                    case 2 ->
                        algorithm = Algorithm.MINIMUMSPANNINGTREE;
                    case 3 ->
                        algorithm = Algorithm.RANDOMNEIGHBOR;
                }

                Bot bot = new Bot(game.getPlayers().getTail().getContent(), algorithm);
                game.getPlayers().getTail().getContent().addBot(bot);
                game.getMap().getLocal(baseLocation).addBot(bot);
            }
        }
    }

    /**
     * Método que inicia o jogo.
     * Permite ao usuário decidir se deseja começar o jogo ou sair.
     * 
     * @param game O jogo que será iniciado.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     */
    public void startGame(Game game) throws IOException, ElementNotFoundException, EmptyCollectionException, InterruptedException, MapTypeException {
        int opt = 0;

        do {
            System.out.println("\nComecar o jogo?\n");
            System.out.println("1. Sim");
            System.out.println("2. Nao\n");
            System.out.print("R: ");
            opt = Integer.parseInt(reader.readLine());
        } while (opt > 2 || opt < 1);

        System.out.println("\n-------------------------------------\n");

        switch (opt) {
            case 1:
                play(game);
                break;
            case 2:
                System.out.println("\nA sair...");
                
                break;
        }

    }

    /**
     * Método que inicia a execução do jogo.
     * Este método continua a execução do jogo até que um jogador vença.
     * 
     * @param game O jogo que será jogado.
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     */
    public void play(Game game) throws ElementNotFoundException, EmptyCollectionException, InterruptedException, IOException, MapTypeException {
        game.selectStartingPlayer();

        int roundCounter = 1;
        while (true) {
            System.out.println("============== RONDA " + roundCounter + " ==============");
            try {
                int winner = game.turn(roundCounter);
                System.out.println("======================================\n\n");
                if (winner != 0) {
                    System.out.println("O jogo finalizado. A voltar para o menu inicial...");
                    break;
                }
            } catch (ElementNotFoundException | EmptyCollectionException e) {
                System.out.println("Erro: " + e.getMessage());
                return;
            }
            Thread.sleep(1000);
            roundCounter++;
        }
        System.out.println("\n-------------------------------------\n");
        mainMenu();
    }

    /**
     * Método principal que inicia o programa.
     * 
     * @param args Os argumentos da linha de comando (não utilizados).
     * @throws ElementNotFoundException Se um elemento não for encontrado durante a execução do jogo.
     * @throws EmptyCollectionException Se uma coleção estiver vazia durante a execução do jogo.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws MapTypeException Se o tipo de mapa não for válido.
     * @throws InterruptedException Se a execução do programa for interrompida enquanto o jogo estiver em execução.
     */
    public static void main(String[] args) throws ElementNotFoundException, EmptyCollectionException, IOException, MapTypeException, InterruptedException {

        Menu menu = new Menu();

        menu.mainMenu();

    }
}
