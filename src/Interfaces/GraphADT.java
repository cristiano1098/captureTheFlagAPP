
package Interfaces;

import java.util.Iterator;

/**
 * Interface que define as operações básicas de um grafo.
 * 
 * @author Miguel e Cristiano
 */
public interface GraphADT<T> {
    
     /**
    * Adiciona um vértice ao grafo.
    * 
    * @param vertex O vértice a ser adicionado.
    */
   public void addVertex (T vertex);

    /**
    * Remove um vértice do grafo.
    * 
    * @param vertex O vértice a ser removido.
    */
   public void removeVertex (T vertex);

    /**
    * Adiciona uma aresta entre dois vértices do grafo.
    * 
    * @param vertex1 O primeiro vértice da aresta.
    * @param vertex2 O segundo vértice da aresta.
    */
   public void addEdge (T vertex1, T vertex2);

    /**
    * Remove uma aresta entre dois vértices do grafo.
    * 
    * @param vertex1 O primeiro vértice da aresta.
    * @param vertex2 O segundo vértice da aresta.
    */
   public void removeEdge (T vertex1, T vertex2);

   /**
    * Retorna um iterador para realizar a travessia do grafo em largura (BFS).
    * 
    * @param startVertex O vértice inicial da travessia.
    * @return Um iterador para a travessia em largura.
    */
   public Iterator<T> iteratorBFS(T startVertex);

   /**
    * Retorna um iterador para realizar a travessia do grafo em profundidade (DFS).
    * 
    * @param startVertex O vértice inicial da travessia.
    * @return Um iterador para a travessia em profundidade.
    */
   public Iterator<T> iteratorDFS(T startVertex);

   /**
    * Retorna um iterador para encontrar o caminho mais curto entre dois vértices.
    * 
    * @param startVertex O vértice inicial do caminho.
    * @param targetVertex O vértice alvo do caminho.
    * @return Um iterador para o caminho mais curto.
    */
   public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex);

   /**
    * Verifica se o grafo está vazio.
    * 
    * @return true se o grafo estiver vazio, caso contrário, false.
    */
   public boolean isEmpty();

   /**
    * Verifica se o grafo é conexo, ou seja, se todos os vértices estão conectados.
    * 
    * @return true se o grafo for conexo, caso contrário, false.
    */
   public boolean isConnected();

   /**
    * Retorna o número de vértices no grafo.
    * 
    * @return O número de vértices no grafo.
    */
   public int size();

   /**
    * Retorna uma representação em string do grafo.
    * 
    * @return Uma representação em string do grafo.
    */
   public String toString();
}
