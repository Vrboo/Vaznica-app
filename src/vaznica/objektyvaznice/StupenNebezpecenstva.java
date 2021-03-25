
package vaznica.objektyvaznice;

/**
 *  Treida StupenNebezpecenstva predstavuje stupen nebezepecenstva vezna vo veznici
 * @author Dominik Vrbovsky
 */
public enum StupenNebezpecenstva {
    VYSOKY("vysoky"),
    STREDNY("stredny"),
    NIZKY("nizky");
    
    /**
     * stupen nebezpecenstva, ktoru moze vezen nadobudnut
     */
    private String stupenNebezpecenstva;
    
    /**
     * Konstruktor pre triedu StupenNebezpecenstva
     * @param stupenNebezpecenstva stupen nebezpecenstva, ktoru moze vezen nadobudnut
     */
    StupenNebezpecenstva(String stupenNebezpecenstva) {
        this.stupenNebezpecenstva = stupenNebezpecenstva;
    }
    
    /**
     * Getter na textovu reprezentaciu stupna nebezpecnosti
     * @return textova reprezentacia stupna nebezpecnosti
     */
    public String getReprezentacia() {
        return this.stupenNebezpecenstva;
    }
    
}
