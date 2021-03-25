
package vaznica.objektyvaznice;

/**
 *  Treida TypZamestnania predstavuje typ zamestnania zamestnancov v straznej vezi
 * @author Dominik Vrbovsky
 */
public enum TypZamestnania {
    POLOVICNYUVAZOK("polovicny uväzok"),
    PLNYUVAZOK("plny uväzok");
    
    /**
     * typ zamestnania, ktory moze mat zamestnanec veznice
     */
    private String typZamestnania;
    
    /**
     * Konstruktor pre triedu Typzamestnania
     * @param typZamestnania 
     */
    TypZamestnania(String typZamestnania) {
        this.typZamestnania = typZamestnania;
    }

    /**
     * Getter na textovu reprezentaciu typu zamestnania
     * @return textova reprezentacia typu zamestnania
     */
    public String getReprezentacia() {
        return this.typZamestnania;
    }
}
