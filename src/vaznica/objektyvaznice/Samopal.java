
package vaznica.objektyvaznice;



/**
 *  Trieda Samopal predstavuje samopal, ktory je uskladneny v municnom sklade veznice
 * @author Dominik Vrbovsky
 */
public class Samopal extends VyzbrojitelnaJednotka {
    /**
     * priemerna cena za jeden samopal; vyjadrene v eurach
     */
    public static final double STANDARNA_CENA_SAMOPALU = 300.0;
    
    /**
     * maximalny pocet nabojov, ktore sa zmestia do zasobnika samopalu
     */
    private int kapacitaZasobnika;

    /**
     * Konstruktor pre triedu Samopal
     * @param kapacitaZasobnika maximalny pocet nabojov, ktore sa zmestia do zasobnika samopalu
     * @param serioveCislo cena samopalu v eurach
     * @param cena jedinecne seriove cislo samopalu 
     */
    public Samopal(int kapacitaZasobnika, String serioveCislo, double cena) {
        super(serioveCislo, cena);
        if (kapacitaZasobnika < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapornu kapacitu zasobnika");
        }
        this.kapacitaZasobnika = kapacitaZasobnika;
    }
    
    /**
     * Getter na kapacitu zasobnika v samopale
     * @return kapacita zasobnika v samopale
     */
    public int getKapacitaZasobnika() {
        return this.kapacitaZasobnika;
    }
    
    /**
     * Getter na krajinu, z ktorej pochadza samopal. 
     * V pripade, že seriove cislo ma 0 az 6 cislic, samopal pochadza z Japonska, ak 7 cislic tak z Ruska, a ak viac ako 7 cislic tak z USA.
     * @return krajina povodu samopalu
     */
    @Override
    public String getKrajinaPovodu() {
        int pocetCislic = this.getSerioveCislo().length();
        if (pocetCislic <= 6) {
            return "Japonsko";
        }
        
        if (pocetCislic == 7) {
            return "Rusko";
        }
        
        if (pocetCislic > 7) {
            return "USA";
        }
        
        return "Krajina povodu je neznama";
    }
    
    /**
     * Metoda, ktora zisti, ci samopal bol drahsi ako je standarna cena samopalu
     * @return informuje o predrazeni samopalu(true - je to predrazene) 
     */
    @Override
    public boolean jeToPredrazene() {
        if (getCena() > STANDARNA_CENA_SAMOPALU) {
            return true;
        }
        return false;
    }
    
    /**
     * Metoda, ktora vypocita a vrati vysku predrazenia samopalu, v pripade že samopal bol predrazeny
     * @return vyska predrazenia samopalu vyjadrena v eurach
     */
    @Override
    public double getVyskaPredrazenia() {
        if (this.jeToPredrazene()) {
            return (getCena() - STANDARNA_CENA_SAMOPALU);
        }
        return 0;
    }
    
    /**
     * Metoda, ktora poskytuje celkovy stav samopalu v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu samopalu
     */
    @Override
    public String toString() {
        return "Samopal: seriove cislo = " + getSerioveCislo() + ", krajina povodu = " + this.getKrajinaPovodu() + 
                ", kapacita zasobnika = " + this.kapacitaZasobnika + ", cena = " + getCena() + "€, " 
                + (this.jeToPredrazene() ? " samopal je predrazeny o " + this.getVyskaPredrazenia() + "€ " : " nie je predrazeny ");
    }
    
    /**
     * Metoda equals na porovnavanie triedy Samopal s inym objektom
     * @param o je porovnavany objekt 
     * @return informacia, či sú objekty identicke
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof Samopal)) {
            return false;
        }
        
        if (!(super.equals(o))) {
            return false;
        }
        
        Samopal s = (Samopal)o;
        double odchylka = 0.005;
        
        if (Math.abs(this.STANDARNA_CENA_SAMOPALU - s.STANDARNA_CENA_SAMOPALU) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.kapacitaZasobnika - s.kapacitaZasobnika) > odchylka) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metoda hashCode pre triedu Samopal
     * @return hashCode typu integer pre triedu Samopal
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)STANDARNA_CENA_SAMOPALU * (int)this.kapacitaZasobnika;
    }

    
    
    
    
}
