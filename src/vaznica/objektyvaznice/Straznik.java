
package vaznica.objektyvaznice;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 *  Trieda Straznik predstavuje straznika vo veznici, ktory je zamestnany v straznej vezi
 * @author Dominik Vrbovsky
 */
public class Straznik extends Zamestnanec {
    /**
     * Priplatok, ktory dostanu iba straznici s hodnostou kapitan ku hodinovej mzda
     */
    public static final double PRIPLATOK_KU_HODINOVEJ_MZDE_KAPITAN = 1.1;
    
    /**
     * hodnost, ktoru moze mat straznik
     */
    private Hodnost hodnost;
    
    
    /**
     * pocet hodin, ktore straznik odpracoval
     */
    private int pocetOdpracovanychHodin;

    /**
     * Konstruktor pre triedu Straznik
     * @param hodnost hodnost, ktoru moze mat straznik
     * @param typZamestnania typ zamestnania straznika
     * @param identifikacneCislo identifikacne cislo zamestnanca, ktore mu je udelene
     * @param pocetRokovPraxe pocet rokov praxe zamestnanca
     */
    public Straznik(Hodnost hodnost, TypZamestnania typZamestnania, String identifikacneCislo, int pocetRokovPraxe) {
        super(typZamestnania, identifikacneCislo, pocetRokovPraxe);
        if (hodnost == null) {
            throw new IllegalArgumentException("V konstruktore ste zadali null pre hodnost");
        }
        
        this.hodnost = hodnost;
        this.pocetOdpracovanychHodin = 0;
    }

    /**
     * Getter na hodnost straznika, ktora moze byt bud kapitan alebo porucik 
     * @return hodnost straznika
     */
    public Hodnost getHodnost() {
        return this.hodnost;
    }

    /**
     * Getter na pocet odpracovanych hodin straznika
     * @return pocet odpracovanych hodin straznika
     */
    public int getPocetOdpracovanychHodin() {
        return this.pocetOdpracovanychHodin;
    }

    /**
     * Setter na pocet hodin, ktore odpracoval straznik a v pripade, že bol zadany zaporny paramater, tak metoda vyhodi vynimku
     * @param pocetOdpracovanychHodin je pocet hodin, ktore straznik odpracoval
     */
    public void pridajOdpracovaneHodiny(int pocetOdpracovanychHodin) {
        if (pocetOdpracovanychHodin < 0) {
            throw new IllegalArgumentException("Zadali ste zaporny pocet odpracovanych hodin");
        }
        
        this.pocetOdpracovanychHodin += pocetOdpracovanychHodin;
    }

    @Override
    public double getHrubuHodinovuMzdu() {
        double mzda;
        if (this.hodnost.equals(Hodnost.KAPITAN)) {
            mzda = super.getHrubuHodinovuMzdu() + PRIPLATOK_KU_HODINOVEJ_MZDE_KAPITAN;
        } else {
            mzda = super.getHrubuHodinovuMzdu();
        }
        BigDecimal bD = new BigDecimal(mzda);
        bD = bD.setScale(2, RoundingMode.HALF_UP);
        return bD.doubleValue();
        
    }
    
    /**
     * Getter na zarobene mnozsto penazi, vypocitane na zaklade hodinovej mzdy a poctu odpracovanych hodin
     * @return zarobenen mnozstvo penazi
     */
    public double getZarobeneMnozstvoPenazi() {
        return this.getHrubuHodinovuMzdu() * this.pocetOdpracovanychHodin;
    }
       
     /**
     * Metoda, ktora poskytne celkovy stav straznika v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu straznika
     */
    @Override
    public String toString() {
        return "Straznik: " + "identifikacne cislo = " + getIdentifikacneCislo() + ", typ zamestnania = " + getTypZamestnania().getReprezentacia() +
                ", pocet rokov praxe = " + getPocetRokovPraxe() + ", hodnost = " + this.hodnost.getReprezentacia() + 
                ", pocet odpracovanych hodin = " + this.pocetOdpracovanychHodin + 
                (maNarokNaPriplatok() ? ", ma narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" : ", nema narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" ) +
                ", hodinova mzda = " + this.getHrubuHodinovuMzdu() + "€, zarobene mnozstvo penazi = " + this.getZarobeneMnozstvoPenazi() + "€" ;
        
    }
    
    /**
     * Metóda equals na porovnavanie s inym objektom
     * @param obj je porovnavany objekt
     * @return informacia, či su objekty identicke
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Straznik)) {
            return false;
        }
        
        if (!(super.equals(obj))) {
            return false;
        }
        
        Straznik paStraznik = (Straznik)obj;
        double odchylka = 0.005;
        
        
        if (Math.abs(this.PRIPLATOK_KU_HODINOVEJ_MZDE_KAPITAN - paStraznik.PRIPLATOK_KU_HODINOVEJ_MZDE_KAPITAN) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.pocetOdpracovanychHodin - paStraznik.pocetOdpracovanychHodin) > odchylka) {
            return false;
        }
        
        if (!(this.hodnost.equals(paStraznik.hodnost))) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metoda hashCode pre triedu Straznik
     * @return hashCode typu integer pre triedu Straznik
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)this.PRIPLATOK_KU_HODINOVEJ_MZDE_KAPITAN 
                * (int)this.pocetOdpracovanychHodin * (int)this.hodnost.hashCode();
    }
}
