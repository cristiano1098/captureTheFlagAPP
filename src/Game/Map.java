package Game;

import Exceptions.ElementNotFoundException;
import Structures.Network;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Classe que representa o mapa do jogo. O mapa contém uma rede de localizações.
 *
 * @author Cristiano e Miguel 
 */
public class Map {

    // A rede de localizações do mapa
    private Network<Local> network;

    // O tamanho do mapa
    private int size;

    /**
     * Construtor que inicializa o mapa com uma nova rede e tamanho zero.
     */
    public Map() {
        this.network = new Network<Local>();
        this.size = 0;
    }

    /**
     * Define o tamanho do mapa.
     *
     * @param size O tamanho do mapa a ser definido.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Getter do tamanho do mapa.
     *
     * @return O tamanho do mapa.
     */
    public int getSize() {
        return size;
    }

    /**
     * Adiciona uma localização à rede do mapa.
     *
     * @param local A localização a ser adicionada.
     */
    public void addLocal(Local local) {
        network.addVertex(local);
        System.out.println("O vertice " + local.getId() + " foi adicionado ao mapa");
    }

    /**
     * Obtém um array de todas as localizações no mapa.
     *
     * @return Um array de localizações no mapa.
     */
    public Local[] getLocals() {
        Local[] location = new Local[network.size()];
        for (int i = 0; i < network.size(); i++) {
            location[i] = network.getVertex(i);
        }
        return location;
    }

    /**
     * Obtém o local com o ID correspondente ao fornecido como parâmetro.
     *
     * @param id O ID do local a ser procurado.
     * @return O local com o ID correspondente.
     * @throws ElementNotFoundException Se o local com o ID fornecido não for
     * encontrado.
     */
    public Local getLocal(int id) throws ElementNotFoundException {
        for (int i = 0; i < network.size(); i++) {
            Local currentLocal = network.getVertex(i);
            if (currentLocal.getId() == id) {
                return currentLocal;
            }
        }
        throw new ElementNotFoundException("Local with ID " + id + " not found.");
    }

    /**
     * Obtém um array dos vertices do mapa.
     *
     * @return Um array dos vertices do mapa.
     */
    public Integer[] getVertices() {
        Integer[] location = new Integer[network.size()];
        for (int i = 0; i < network.size(); i++) {
            location[i] = network.getVertex(i).getId();
        }
        return location;
    }

    /**
     * Obtém a rede de localizações do mapa.
     *
     * @return A rede de localizações do mapa.
     */
    public Network<Local> getNetwork() {
        return network;
    }

    /**
     * Importa um mapa a partir de um arquivo JSON.
     *
     * @param fileName o nome do ficheiro com o mapa.
     */
    public void importMap(String fileName) {

        try ( FileReader fileReader = new FileReader(fileName)) {
            JSONTokener token = new JSONTokener(fileReader);
            JSONObject json = new JSONObject(token);

            System.out.println("Ficheiro " + fileName + " encontrado com sucesso !");
            System.out.println("\n-------------------------------------");
            System.out.println("\nA criar o mapa...\n");

            JSONArray nodesArray = json.getJSONArray("Locals");
            for (int i = 0; i < nodesArray.length(); i++) {
                JSONObject nodeJson = nodesArray.getJSONObject(i);
                int localId = nodeJson.getInt("id");
                Local local = new Local(localId);         
                this.network.addVertex(local);
                this.size++;
                System.out.println("O vertice " + local.getId() + " foi adicionado ao mapa");
            }

            JSONArray edgesArray = json.getJSONArray("edges");
            for (int i = 0; i < edgesArray.length(); i++) {
                JSONObject edgeJson = edgesArray.getJSONObject(i);
                int source = edgeJson.getInt("from");
                int target = edgeJson.getInt("to");
                double weight = edgeJson.getDouble("weight");
                this.network.addEdge(source, target, weight);
            }

            System.out.println("\nMapa criado com sucesso!");
            System.out.println("\n-------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporta o mapa para um arquivo JSON.
     * 
     * @param fileName nome do ficheiro onde será armazenado o mapa.
     */
    public void exportMap(String fileName) {
        fileName = fileName + ".json";

        JSONObject json = new JSONObject();

        JSONArray nodesArray = new JSONArray();
        for (Local local : getLocals()) {
            JSONObject nodeJson = new JSONObject();
            nodeJson.put("id", local.getId());
            nodesArray.put(nodeJson);
        }
        json.put("Locals", nodesArray);

        JSONArray edgesArray = new JSONArray();
        double[][] adjacencyMatrix = this.network.getAdjMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > 0 && adjacencyMatrix[i][j] < 16) {
                    JSONObject edgeJson = new JSONObject();
                    edgeJson.put("from", i);
                    edgeJson.put("to", j);
                    edgeJson.put("weight", adjacencyMatrix[i][j]);
                    edgesArray.put(edgeJson);
                }
            }
        }
        json.put("edges", edgesArray);

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toString());
            System.out.println("\nMapa exportado com sucesso no ficheiro: " + fileName + "!");
            System.out.println("\n-------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma representação em string do mapa, incluindo suas localizações.
     *
     * @return Uma string representando o mapa.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < network.size(); i++) {
            s.append(network.getVertex(i).toString());
        }
        return s.toString();
    }
}
