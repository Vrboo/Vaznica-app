
package vaznica.objektyvaznice;

/**
 *  Trieda Granat predstavuje vybusny granat, ktory je uskladneny v municnom sklade veznice
 * @author Dominik Vrbovsky
 */
public class Granat extends VyzbrojitelnaJednotka {
    /**
     * priemerna cena za jeden granat
     */
    public static final double STANDARNA_CENA_GRANATU = 80.0;
    
    /**
     * hmotnost granatu vyjadrena v kilogramoch
     */
    private double hmotnost;

    /**
     * Konstruktor pre triedu Granat
     * @param serioveCislo jedinecne seriove cislo granatu
     * @param cena cena granatu v eurach
     * @param hmotnost hmotnost granatu vyjadrena v kilogramoch
     */
    public Granat(String serioveCislo, double cena, double hmotnost) {
        super(serioveCislo, cena);
        
        if (hmotnost <= 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali hmotnost granatu mensiu ako 0");
        }
        
        this.hmotnost = hmotnost;
    }

    /**
     * Getter na hmotnost granatu vyjadrenu v kilogramoch
     * @return hmotnost granatu v kg
     */
    public double getHmotnost() {
        return this.hmotnost;
    }
    
    /**
     * Getter na krajinu, z ktorej pochadza granata. 
     * V pripade, že ide o parne cislo, granat pochadza Ruska a ak nie, tak granat pochadza z USA.
     * @return krajina povodu granatu
     */
    @Override
    public String getKrajinaPovodu() {
        int intSerioveCislo = Integer.parseInt(getSerioveCislo());
        int vysl = intSerioveCislo % 2;
        
        if (vysl == 0) {
            return "Rusko";
        } else {
            return "USA";
        }
    }
    
    /**
     * Metoda, ktora zisti, ci dany granat bol drahsi ako je standarna cena granatu
     * @return informuje o predrazeni granatu(true - je to predrazene) 
     */
    @Override
    public boolean jeToPredrazene() {
        if (getCena() > STANDARNA_CENA_GRANATU) {
            return true;
        }
        return false;
    }
    
    /**
     * Metoda, ktora vypocita a vrati vysku predrazenia granatu, v pripade že granat bol predrazeny
     * @return vyska predrazenia granatu vyjadrena v eurach
     */
    @Override
    public double getVyskaPredrazenia() {
        if (this.jeToPredrazene()) {
            return getCena() - STANDARNA_CENA_GRANATU;
        }
        return 0;
    }
    
    /**
     * Metoda, ktora informuje o celkovom stave granatu v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu granatu
     */
    @Override
    public String toString() {
        return "Granat: seriove cislo = " + this.getSerioveCislo() + ", krajina povodu = " + this.getKrajinaPovodu() + ", hmotnost = " + this.hmotnost + "kg, cena = " + getCena() + "€, " 
                + (this.jeToPredrazene() ? "granat je predrazeny o " + this.getVyskaPredrazenia() + "€ " : " nie je predrazeny ");
    }
    
    /**
     * Metóda equals na porovavanie triedy Granat s iným objektom
     * @param o je porovnávaný objekt
     * @return informácia, či sa objekty identické
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof Granat)) {
            return false;
        }
        
        if (!(super.equals(o))) {
            return false;
        }
        
        Granat p = (Granat)o;
        double odchylka = 0.005;
        
        if (Math.abs(this.STANDARNA_CENA_GRANATU - p.STANDARNA_CENA_GRANATU) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.hmotnost - p.hmotnost) > odchylka) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metóda hashCode pre triedu Granat
     * @return hasCode typu integer pre triedu Granat
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)STANDARNA_CENA_GRANATU * (int)this.hmotnost;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    

