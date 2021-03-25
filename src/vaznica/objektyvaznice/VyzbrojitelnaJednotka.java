
package vaznica.objektyvaznice;

/**
 *  Abstraktna trieda VyzbrojitelnaJednotka predstavuje vyzbroj veznice
 * @author Dominik Vrbovsky
 */
public abstract class VyzbrojitelnaJednotka {
    /**
     * jedinecne seriove cislo vyzbrojitelnej jednotky 
     */
    private String serioveCislo;
    
    /**
     * cena vyzbrojitelnej jednotky v eurach
     */
    private double cena;

    /**
     * Kontruktor pre abstraktnu triedu VyzbrojitelnaJednotka
     * @param serioveCislo jedinecne seriove cislo vyzbrojitelnej jednotky 
     * @param cena cena vyzbrojitelnej jednotky v eurach
     */
    public VyzbrojitelnaJednotka(String serioveCislo, double cena) {
        if (serioveCislo == null) {
            throw new IllegalArgumentException("V konstruktore ste zadali seriove cislo null");
        }
        
        if (cena < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapornu cenu");
        }
        
        this.serioveCislo = serioveCislo;
        this.cena = cena;
    }
    
    /**
     * Getter na seriove cislo vyzbroje
     * @return seriove cislov vyzbroje
     */
    public String getSerioveCislo() {
        return this.serioveCislo;
    }

    /**
     * Getter na cenu vyzbroje vyjadrenu v eurach
     * @return cena vyzbroje vyjadrena v eurach
     */
    public double getCena() {
        return this.cena;
    }
    
    /**
     * Getter na krajinu, z ktorej pochadza vybroj
     * @return krajina povodu vyzbroje
     */
    public abstract String getKrajinaPovodu();
    
    /**
     * Metoda, ktora zisti, ci dana vyzbroj bola drahsia ako je standarna cena danej vyzbroje
     * @return informuje o predrazeni vyzbroje(true - je to predrazene) 
     */
    public abstract boolean jeToPredrazene();
    
    /**
     * Metoda, ktora vypocita a vrati vysku predrazenia vyzbroje, v pripade že vybroj bola predrazena
     * @return vyska predrazenia vyzbroje vyjadrena v eurach
     */
    public abstract double getVyskaPredrazenia();
    
    /**
     * Metoda, ktora informuje a celkovom stave vyzbroje v podobe textoveho retazca
     * @return textova reprezentacia celkoveho reprezentacie vyzbroje
     */
    @Override
    public String toString() {
        return "Vyzbrojitelna jednotka: seriove cislo = " + this.getSerioveCislo() + ", cena = " + this.getCena() + " eur";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof VyzbrojitelnaJednotka)) {
            return false;
        }
        
        double odchylka = 0.005;
        VyzbrojitelnaJednotka vJ = (VyzbrojitelnaJednotka)o;
        
        if (!this.serioveCislo.equals(vJ.serioveCislo)) {
            return false;
        }
        
        if (Math.abs(this.cena - vJ.cena) > odchylka) {
            return false;
        }
        return true;
        
        
    }
    
    @Override
    public int hashCode() {
        return (int)this.serioveCislo.hashCode() * (int)this.cena;
    
    }
}
