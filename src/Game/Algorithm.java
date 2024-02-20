
package Game;

/**
 * Enumeração que representa os algoritmos utilizados pelos bots no jogo.
 * 
 * Os algoritmos disponíveis são:
 * - MINIMUMSPANNINGTREE: Algoritmo de árvore geradora de custo minimo
 * - SHORTESTWEIGHTPATH: Algoritmo de caminho mais "barato"
 * - RANDOMNEIGHBOR: Algoritmo de escolha aleatória do vizinho
 * 
 * @author Cristiano e Miguel
 */
public enum Algorithm {
    MINIMUMSPANNINGTREE, SHORTESTWEIGHTPATH, RANDOMNEIGHBOR;
}
