
package vaznica.objektyvaznice;

/**
 *  Trieda Hodnost predstavuje hodnost straznikov vo veznici
 * @author Dominik Vrbovsky
 */
public enum Hodnost {
    KAPITAN("kapitan"),
    PORUCIK("porucik");
    
    /**
     * hodnost, ktora moze byt pridelena straznikovi
     */
    private String hodnost;
    
    /**
     * Konstruktor pre triedu Hodnost
     * @param hodnost hodnost, ktora moze byt pridelena straznikovi
     */
    Hodnost(String hodnost) {
        this.hodnost = hodnost;
    }
    
    /**
     * Getter na textovu reprezentaciu hodnosti
     * @return textova reprezentacia hodnosti
     */
    public String getReprezentacia() {
        return this.hodnost;
    }
    
    
}
