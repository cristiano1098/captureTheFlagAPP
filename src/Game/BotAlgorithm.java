package Game;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.StackADT;
import Structures.ArrayStack;
import Structures.ArrayUnorderedList;
import Structures.LinkedList;
import Structures.LinkedQueue;
import java.util.Random;

/**
 * Classe que define algoritmos para o bot do jogo.
 * 
 * @author Cristiano e Miguel
 */
public class BotAlgorithm {

    public BotAlgorithm() {

    }

    /**
     * Calcula o próximo índice no caminho mais curto entre dois índices no mapa.
     * 
     * @param map O mapa do jogo
     * @param index1 O índice de origem
     * @param index2 O índice de destino
     * @return O próximo índice no caminho mais curto
     * @throws EmptyCollectionException lançada caso a coleção esteja vazia
     * @throws ElementNotFoundException lançada caso não exista o elemento na coleção
     */
    public static int shortestWeightPath(Map map, int index1, int index2) throws EmptyCollectionException, ElementNotFoundException {
        if (index1 == index2) {
            return index1;
        }

        // Executa o algoritmo de Dijkstra para encontrar o caminho mais curto
        ArrayUnorderedList<Integer> path = map.getNetwork().dijkstraAlgorithm(index1, index2);

        if (path.size() == 1) {
            return index1;
        }
        return path.get(1);
    }

    /**
     * Retorna um índice aleatório dos vizinhos de um determinado índice no mapa.
     * 
     * @param map O mapa do jogo
     * @param index O índice atual
     * @return Um índice aleatório dos vizinhos
     * @throws EmptyCollectionException lançada caso a coleção esteja vazia
     */
    public static int randomNeighbor(Map map, int index) throws EmptyCollectionException {
        double[][] adjMatrix = map.getNetwork().getAdjMatrix();
        ArrayUnorderedList<Integer> connectedVertices = new ArrayUnorderedList();

        // Constrói uma lista de vértices conectados
        for (int i = 0; i < adjMatrix[index].length; ++i) {
            if (adjMatrix[index][i] != 0.0) {
                connectedVertices.addToRear(i);
            }
        }

        if (connectedVertices.isEmpty()) {
            return index;
        } else {
            // Retorna um índice aleatório dos vizinhos
            Random rand = new Random();
            int randomIndex = rand.nextInt(connectedVertices.size());
            return (Integer) connectedVertices.get(randomIndex);
        }
    }

    /**
     * Calcula o próximo índice na árvore de spanning mínima entre dois índices no mapa.
     * 
     * @param map O mapa do jogo
     * @param index1 O índice de origem
     * @param index2 O índice de destino
     * @return O próximo índice na árvore de spanning mínima
     * @throws ElementNotFoundException lançada caso a coleção esteja vazia
     */
    public static int minimumSpanningTree(Map map, int index1, int index2) throws ElementNotFoundException {
        if (index1 == index2) {
            return index1;
        } else {
            int[] parent = new int[map.getNetwork().getAdjMatrix().length];
            int[] key = new int[map.getNetwork().getAdjMatrix().length];
            boolean[] mst = new boolean[map.getNetwork().getAdjMatrix().length];

            int count;
            for (count = 0; count < map.getNetwork().getAdjMatrix().length; ++count) {
                key[count] = Integer.MAX_VALUE;
                mst[count] = false;
            }

            key[index1] = 0;
            parent[index1] = -1;

            for (count = 0; count < map.getNetwork().getAdjMatrix().length - 1; ++count) {
                int u = minKey(map, key, mst);
                if (u == -1) {
                    break;
                }
                mst[u] = true;

                // Atualiza os valores-chave e os pais
                for (int v = 0; v < map.getNetwork().getAdjMatrix().length; ++v) {
                    if (map.getNetwork().getAdjMatrix()[u][v] != 0.0 && !mst[v] && map.getNetwork().getAdjMatrix()[u][v] < (double) key[v]) {
                        parent[v] = u;
                        key[v] = (int) map.getNetwork().getAdjMatrix()[u][v];
                    }
                }
            }

            LinkedList<Integer> path = new LinkedList<>();
            int vertex = index2;
            while (vertex != index1) {
                path.addToHead(vertex);
                vertex = parent[vertex];
            }
            path.addToHead(index1);

            return path.getHead().getNext().getContent();
        }
    }

    /**
     * Retorna o índice com a chave mínima.
     * 
     * @param map O mapa do jogo
     * @param key O array de chaves
     * @param mst O array de vértices já incluídos na árvore de spanning mínima
     * @return O índice com a chave mínima
     */
    public static int minKey(Map map, int[] key, boolean[] mst) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < map.getNetwork().getAdjMatrix().length; ++v) {
            if (!mst[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    /**
     * Retorna o próximo índice no caminho encontrado pela BFS.
     * 
     * @param map O mapa do jogo
     * @param index1 O índice de origem
     * @param index2 O índice de destino
     * @return O próximo índice no caminho encontrado pela BFS
     */
    public static int getNextPositionBFS(Map map, int index1, int index2) {
        int vertices = map.getNetwork().getAdjMatrix().length;
        if (index1 == index2) {
            return index1;
        } else {
            int[] predecessor = new int[vertices];
            boolean[] visited = new boolean[vertices];
            LinkedQueue<Integer> queue = new LinkedQueue();
            queue.enqueue(index1);
            visited[index1] = true;

            while (!queue.isEmpty()) {
                int current = 0;

                try {
                    current = (Integer) queue.dequeue();
                } catch (EmptyCollectionException var10) {
                    var10.printStackTrace();
                }

                if (current == index2) {
                    int next;
                    for (next = predecessor[index2]; predecessor[next] != index1; next = predecessor[next]) {
                    }

                    return next;
                }

                double[] adj = map.getNetwork().getAdjMatrix()[current];

                for (int i = 0; i < vertices; ++i) {
                    if (adj[i] != 0.0 && !visited[i]) {
                        queue.enqueue(i);
                        visited[i] = true;
                        predecessor[i] = current;
                    }
                }
            }

            return index1;
        }
    }

    /**
     * Retorna o próximo índice no caminho encontrado pela DFS.
     * 
     * @param map O mapa do jogo
     * @param index1 O índice de origem
     * @param index2 O índice de destino
     * @return O próximo índice no caminho encontrado pela DFS
     * @throws ElementNotFoundException lançado caso o elemento não exista na coleção
     * @throws EmptyCollectionException lançado caso a coleção esteja vazia
     */
    public static int getNextPositionDFS(Map map, int index1, int index2) throws ElementNotFoundException, EmptyCollectionException {
        if (index1 == index2) {
            return index1;
        } else {
            int[] parent = new int[map.getNetwork().getAdjMatrix().length];
            boolean[] visited = new boolean[map.getNetwork().getAdjMatrix().length];

            int count;
            int[] key = new int[map.getNetwork().getAdjMatrix().length];
            for (count = 0; count < map.getNetwork().getAdjMatrix().length; ++count) {
                key[count] = Integer.MAX_VALUE;
                visited[count] = false;
            }

            key[index1] = 0;
            parent[index1] = -1;

            StackADT<Integer> stack = new ArrayStack<>();
            stack.push(index1);

            while (!stack.isEmpty()) {
                int current = stack.pop();
                if (current == index2) {
                    LinkedList<Integer> path = new LinkedList<>();
                    for (int u = index2; u != index1; u = parent[u]) {
                        path.addToHead(u);
                    }
                    path.addToHead(index1);
                    return (Integer) path.getHead().getNext().getContent();
                }
                visited[current] = true;

                for (int v = 0; v < map.getNetwork().getAdjMatrix().length; ++v) {
                    if (map.getNetwork().getAdjMatrix()[current][v] != 0 && !visited[v] && map.getNetwork().getAdjMatrix()[current][v] < key[v]) {
                        parent[v] = current;
                        key[v] = (int) map.getNetwork().getAdjMatrix()[current][v];
                        stack.push(v);
                        visited[v] = true; // Marca o vértice como visitado ao ser adicionado à stack
                    }
                }
            }

            // Se não houver caminho encontrado
            return -1;
        }
    }
}
