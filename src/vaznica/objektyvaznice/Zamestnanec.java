
package vaznica.objektyvaznice;

import java.util.Objects;

/**
 *  Trieda Zamestnanec predstavuje zamestnanca veznice , ktore je zamesntany v straznej vezi
 * @author Dominik Vrbovsky
 */
public class Zamestnanec implements IZamestnatelny {
    /**
     * hruba hodinova mzda pre zamestnanca, ktore je zamestnany na polovicny uvazok
     */
    public static final double HRUBA_HODINOVA_MZDA_POLOVICNY_UVAZOK = 3.2;
    
    /**
     * hruba hodinova mzda pre zamestnanca, ktore je zamestnany na plny uvazok
     */
    public static final double HRUBA_HODINOVA_MZDA_PLNY_UVAZOK = 4.1;
    
    /**
     * za 10 rokov praxe, dostane zamestnanec priplatok danu sumu vyjadrenu v eurach
     */
    public static final double PRIPLATOK_K_HODINOVEJ_MZDE_ZA_10_ROCNU_PRAX = 0.5; 
    
    /**
     * typ zamestnania zamestnanca (polovicny uvazok, plny uvazok)
     */
    private TypZamestnania typZamestnania;
    
    /**
     * identifikacne cislo zamestnanca, ktore mu je udelene
     */
    private String identifikacneCislo;
    
    /**
     * pocet rokov praxe zamestnanca
     */
    private int pocetRokovPraxe;
   
    /**
     * Konstruktor pre triedu Zamestnanec
     * @param typZamestnania typ zamestnania zamestnanca
     * @param identifikacneCislo identifikacne cislo zamestnanca, ktore mu je pridelene
     * @param pocetRokovPraxe pocet rokov praxe zamestnanca
     */
    public Zamestnanec(TypZamestnania typZamestnania, String identifikacneCislo, int pocetRokovPraxe) {
        if (identifikacneCislo == null) {
            throw new IllegalArgumentException("V konstruktore ste zadali identifikacne cislo ako null");
        }
        
        if (pocetRokovPraxe < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zaporny pocet rokov praxe");
        }
        
        
        this.typZamestnania = typZamestnania;
        this.identifikacneCislo = identifikacneCislo;
        this.pocetRokovPraxe = pocetRokovPraxe;
        
    }
    
    /**
     * Getter na typ zamestnania zamestnania, ktore moze byt: polovicny uvazok alebo plny uvazok
     * @return typ zamestnania zamestnanca
     */
    @Override
    public TypZamestnania getTypZamestnania() {
        return this.typZamestnania;
    }
    
    /**
     * Getter na identifikacne cislo zamestnanca
     * @return identifikacne cislo zamestnanca
     */
    @Override
    public String getIdentifikacneCislo() {
        return this.identifikacneCislo;
    }

    /**
     * Getter na pocet rokov praxe, ktorou disponuje zamestnanec
     * @return pocet rokov praxe, ktorou zamestnanec disponuje
     */
    public int getPocetRokovPraxe() {
        return this.pocetRokovPraxe;
    }
    
    /**
     * Metoda, ktora vyhodnoti, ci ma zamestnanec narok na priplatok k hodinovej mzde na zaklade praxe zamestnanca
     * @return informacia, ci ma zamestnanec narok na priplatok
     */
    public boolean maNarokNaPriplatok() {
        if (this.pocetRokovPraxe >= 10) {
            return true;
        }
            return false;
        
    }
    
    /**
     * Getter na hrubu hodinu mzdu, ktoru vyhodnoti na zakladne typu zamestnania a praxe
     * @return hrubu hodinuvu mzdu zamestnanca
     */
    public double getHrubuHodinovuMzdu() {
        double hodinovaMzda = 0.0;
        
        if (this.typZamestnania.equals(TypZamestnania.POLOVICNYUVAZOK)) {
            hodinovaMzda = HRUBA_HODINOVA_MZDA_POLOVICNY_UVAZOK;
        }
        
        if (this.typZamestnania.equals(TypZamestnania.PLNYUVAZOK)) {
            hodinovaMzda = HRUBA_HODINOVA_MZDA_PLNY_UVAZOK;
        }
        
        if (this.maNarokNaPriplatok()) {
            hodinovaMzda += PRIPLATOK_K_HODINOVEJ_MZDE_ZA_10_ROCNU_PRAX;
        }
        
        return hodinovaMzda;
    }

    /**
     * Metoda, ktora poskytne celkovy stav zamestnanca v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu zamestnanca
     */
    @Override
    public String toString() {
        return "Zamestnanec: " + "typ zamestnania = " + this.typZamestnania.getReprezentacia() + ", identifikacne cislo = " + this.identifikacneCislo +
                ", pocet rokov praxe = " + this.pocetRokovPraxe + 
                (maNarokNaPriplatok() ? ", ma narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" : ", nema narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" )
                + ", hodinova mzda = " + getHrubuHodinovuMzdu() + '€';
    }
    
    /**
     * Metoda equals na porovnavanie s inym objektom
     * @param o je porovnavany objekt
     * @return infomacia, ci su objekty identicke
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof Zamestnanec)) {
            return false;
        }
        
        Zamestnanec paZam = (Zamestnanec)o;
        double odchylka = 0.005;
        
        if (!(this.typZamestnania.equals(paZam.typZamestnania))) {
            return false;
        }
        
        if (!(this.identifikacneCislo.equals(paZam.identifikacneCislo))) {
            return false;
        }
        
        if ((Math.abs(this.pocetRokovPraxe - paZam.pocetRokovPraxe)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.HRUBA_HODINOVA_MZDA_POLOVICNY_UVAZOK - paZam.HRUBA_HODINOVA_MZDA_POLOVICNY_UVAZOK)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.HRUBA_HODINOVA_MZDA_PLNY_UVAZOK - paZam.HRUBA_HODINOVA_MZDA_PLNY_UVAZOK)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.PRIPLATOK_K_HODINOVEJ_MZDE_ZA_10_ROCNU_PRAX - paZam.PRIPLATOK_K_HODINOVEJ_MZDE_ZA_10_ROCNU_PRAX)) > odchylka) {
            return false;
        }
        
        return true;
    }

    /**
     * Metoda hashCode pre triedu Zamestnanec
     * @return hashCode typu integer pre triedu Zamestnanec
     */
    @Override
    public int hashCode() {
        return (int)this.identifikacneCislo.hashCode() * (int)this.pocetRokovPraxe *
                (int)this.typZamestnania.hashCode() * (int)this.HRUBA_HODINOVA_MZDA_POLOVICNY_UVAZOK * (int)this.HRUBA_HODINOVA_MZDA_PLNY_UVAZOK *
                (int)this.PRIPLATOK_K_HODINOVEJ_MZDE_ZA_10_ROCNU_PRAX;
    }
}
