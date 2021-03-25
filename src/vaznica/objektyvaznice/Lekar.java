
package vaznica.objektyvaznice;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 *  Trieda Lekar predstavuje lekara vo veznici, ktory je zamestnany v straznej vezi
 * @author Dominik Vrbovsky
 */
public class Lekar extends Zamestnanec {
    /**
     * priplatok k hodinovej mzde lekar za kazdych 50 osetrenych osob; vyjadrene v eurach
     */
    public static final double PRIPLATOK_ZA_50_OSETRENYCH_OSOB = 0.9; 
    
    /**
     * cena paliva za prejdenych 100 kilometrov na aute; vyjadrene v eurach
     */
    public static final double CENA_PALIVA_ZA_100_KILOMETER = 7;

    /**
     * pocet osob, ktore osetril lekar
     */
    private int pocetOsetrenychOsob;
    
    /**
     * pocet kilometrov veznice od bydliska lekara
     */
    private int vzdialenostBydliskaOdVeznice; // vyjadrene v kilometroch 
     
    /**
     * Konstruktor pre triedu Lekar
     * @param typZamestnania typ zamestnania lekara
     * @param identifikacneCislo identifikacne cislo lekara, ktore mu je udelene
     * @param pocetRokovPraxe pocet rokov praxe lekara
     * @param vzdialenostBydliskaOdVeznice pocet kilometrov veznice od bydliska lekara
     */
    public Lekar( TypZamestnania typZamestnania, String identifikacneCislo, int pocetRokovPraxe, int vzdialenostBydliskaOdVeznice) {
        super(typZamestnania, identifikacneCislo, pocetRokovPraxe);
        if (vzdialenostBydliskaOdVeznice < 0) {
            throw new IllegalArgumentException("Zadali ste zaporny vzdialenost bydliska od veznice"); 
        }
        
        this.pocetOsetrenychOsob = 0;
        
        this.vzdialenostBydliskaOdVeznice = vzdialenostBydliskaOdVeznice;
    }

    /**
     * Getter na pocet osob, ktore lekar osetril
     * @return pocet osob, ktore lekar osetril
     */
    public int getPocetOsetrenychOsob() {
        return this.pocetOsetrenychOsob;
    }
    
    /**
     * Setter na pocet osetrenych osob lekara. V pripade zadaneho zaporneho parametra, metoda vyhodi vynimku.
     * @param pocetOsetrenychOsob je pocet osob, ktore lekar osetril
     */
    public void setPocetOsetrenychOsob(int pocetOsetrenychOsob) {
        if (pocetOsetrenychOsob < 0) {
            throw new IllegalArgumentException("Zadali ste zaporny pocet osetrenych osob");
        }
        
        this.pocetOsetrenychOsob += pocetOsetrenychOsob;
    }
    
    /**
     * Metoda, ktora vypocita a vrati hrubu hodinovu mzdu lekara, na zaklade poctu osetrenech osob. 
     * Za kazdych 50 osetrenych osob dostane priplatok 0.9 eura k hodinovej mzde.
     * @return hruba hodinova mzda lekara 
     */
    @Override 
    public double getHrubuHodinovuMzdu() {
        int pocetPriplatkov = this.pocetOsetrenychOsob / 50;
        double hodnotaPriplatku = pocetPriplatkov * PRIPLATOK_ZA_50_OSETRENYCH_OSOB;
        
        return super.getHrubuHodinovuMzdu() + hodnotaPriplatku;
    }
    
    /**
     * Metoda, ktora vypocita a vrati naklady na palivo za den, v pripade, že by lekar do roboty dochadzal
     * @return denne naklady lekara na palivo, v pripade dochadzania do roboty
     */
    public double getNakladyZDochadzaniaNaPracovnyDen() {
        double naklady = 2 * ((CENA_PALIVA_ZA_100_KILOMETER / 100.00) * this.vzdialenostBydliskaOdVeznice);
        BigDecimal bD = new BigDecimal(naklady);
        bD = bD.setScale(2, RoundingMode.HALF_UP);
        return bD.doubleValue();
    }
    
    /**
     * V pripade, že lekar byva od veznice viac ako 100 kilometrov je ubytovany.
     * @return infomacia o tom, ci je lekar ubytovany(true) alebo nie(false)
     */
    public boolean jeUbytovany() {
        if (this.vzdialenostBydliskaOdVeznice >= 100) {
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Metoda, ktora poskytne celkovy stav lekara v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu lekara
     */
    @Override
    public String toString() {
        return "Lekar: identifikacne cislo = " + getIdentifikacneCislo() + ", typ zamestnania = " + getTypZamestnania().getReprezentacia() +
                ", pocet rokov praxe = " + getPocetRokovPraxe() + 
                (maNarokNaPriplatok() ? ", ma narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" : ", nema narok na priplatok za 10 rocnu prax v hodnote 0.5 € / hodina" ) 
                + ", hruba hodinova mzda = " + this.getHrubuHodinovuMzdu() + "€, pocet osetrenych osob = " + this.getPocetOsetrenychOsob() +
                (this.jeUbytovany() ? ", nema naklady spojene s dochadzanim, pretoze je ubytovany" : ", naklady spojene s dochadzanim za pracovny den = " + this.getNakladyZDochadzaniaNaPracovnyDen() + "€") ;
    }
    
    /**
     * Metóda equals na porovnavanie triedy Lekar s inym objektom
     * @param obj je porovnavany objekt
     * @return informacia, či sú porovnavané objekty identické
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Lekar)) {
            return false;
        }
        
        if (!(super.equals(obj))) {
            return false;
        }
        
        Lekar paLekar = (Lekar)obj;
        double odchylka = 0.005;
        
        if (Math.abs(this.CENA_PALIVA_ZA_100_KILOMETER - paLekar.CENA_PALIVA_ZA_100_KILOMETER) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.PRIPLATOK_ZA_50_OSETRENYCH_OSOB - paLekar.PRIPLATOK_ZA_50_OSETRENYCH_OSOB) > odchylka) {
            return false;
        }
        
        
        if (Math.abs(this.pocetOsetrenychOsob - paLekar.pocetOsetrenychOsob) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.vzdialenostBydliskaOdVeznice - paLekar.vzdialenostBydliskaOdVeznice) > odchylka) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metóda hashCode pre triedu Lekar
     * @return hashCode typu integer pre triedu Lekar
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)PRIPLATOK_ZA_50_OSETRENYCH_OSOB * 
                (int)CENA_PALIVA_ZA_100_KILOMETER * (int)this.pocetOsetrenychOsob * (int)this.vzdialenostBydliskaOdVeznice ;
    }
    
    
    
    
    
    
    
    
    
    
        
        
}
    
    
    
    
    
    
    
    
    
    
    
    

