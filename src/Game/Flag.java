package Game;

/**
 * Classe que representa uma bandeira no jogo.
 * A bandeira pode ser carregada por um bot.
 * 
 * @author Cristiano Rocha e Miguel Vieira
 */
public class Flag {
    
    // O bot que carrega a bandeira
    private Bot carrier;
    
    /**
     * Construtor padrão da bandeira.
     * Inicializa a bandeira sem nenhum carrier.
     */
    public Flag(){
        this.carrier = null;
    }
    
    /**
     * Construtor da bandeira que aceita um carrier inicial.
     * 
     * @param carrier O Bot que irá inicialmente carregar a bandeira.
     */
    public Flag(Bot carrier){
        this.carrier = carrier;
    }
    
    /**
     * Define um novo carrier para a bandeira.
     * 
     * @param bot O novo Bot que irá carregar a bandeira.
     */
    public void addCarrier(Bot bot){
        this.carrier = bot;
    }
    
    /**
     * Obtém o Bot que carrega a bandeira.
     * 
     * @return O Bot que está carregando a bandeira.
     */
    public Bot getCarrier(){
        return this.carrier;
    }
    
    /**
     * Remove o carrier atual da bandeira, deixando-a sem carrier.
     */
    public void removeCarrier(){
        this.carrier = null;
    }
}
