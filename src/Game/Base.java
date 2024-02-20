package Game;

/**
 *
 * Classe de Base que tem um id associado e uma Flag
 * 
 * @author Cristiano Rocha e Miguel Vieira
 */
public class Base {
    
    /**
     * id de Base
     */
    private int id;
    
    /**
     * Flag de Base
     */
    private Flag flag;
    
    
    /**
     * Método construtor de Base
     * 
     * @param flag flag a ser adicionada
     */
      public Base(Flag flag) {
        this.id = -1;
        this.flag = flag;
    }

    /**
     * Getter de Flag
     * 
     * @return flag da Base
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Setter de Flag
     * 
     * @param flag flag a atribuir à base
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
    
    /**
     * Getter de ID
     * 
     * @return id da Base
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Define o índice desta base para o valor especificado.
     *
     * @param index O novo índice para esta base.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Método toString de Base
     * @return String com a informação da Base
     */
    public String toSring(){
        return "Base " + this.getId() + "\nFlag" + this.getFlag().toString();
    }
}
