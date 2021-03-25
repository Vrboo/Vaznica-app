
package vaznica.objektyvaznice;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 *  Trieda Vezen predstavuje vezna uvezneneho v cele veznice
 * @author Dominik Vrbovsky
 */
public class Vezen implements IUveznitelny { 
    /**
     * minimalny pocet rokov, na ktore moze byt vezen odsudeny
     */
    public static final int MIN_POCET_ROKOV_ODSUDENIA = 5;
    
    /**
     * maximalny zdravotny stav, ktory moze mat vezen; vyjadrene v percentach
     */
    public static final int MAX_ZDRAVOTNY_STAV = 100; 
    
    /**
     * Po piatich rokoch vo vezeni sa prenasobi zdravotny stav vezna s tymto cislom
     */
    public static final double KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH = 0.75; // ubytok 25% zdravia po kazdych piatich rokoch vo vezeni
    
    /**
     * jedinecne cislo vezna, ktore mu bolo pridelene 
     */
    private String cisloVezna;
    
    /**
     * pocet rokov odsudenia vezna
     */
    private int pocetRokovOdsudenia;
    
    /**
     * stupen nebezpecenstva vezna(nizky, stredny, vysoky)
     */
    private StupenNebezpecenstva stupenNebezpecenstva;
    
    /**
     * zdravotny stav vezna vyjadreny v percentach
     */
    private double zdravotnyStav; // vyjadrene v percentach

    /**
     * Konstruktor pre triedu Vezen
     * @param cisloVezna jedinecne cislo vezna, ktore mu bolo pridelene
     * @param pocetRokovOdsudenia pocet rokov odsudenia vezna
     * @param stupenNebezpecenstva stupen nebezpecenstva vezna(nizky, stredny, vysoky)
     * @param zdravotnyStav zdravotny stav vezna vyjadreny v percentach
     */
    public Vezen(String cisloVezna, int pocetRokovOdsudenia, StupenNebezpecenstva stupenNebezpecenstva, double zdravotnyStav) {
        if (pocetRokovOdsudenia < MIN_POCET_ROKOV_ODSUDENIA) {
            throw new IllegalArgumentException("V konstruktore ste zadali pocet rokov odsudenia mensi ako minimalny pocet rokov odsudenia");
        }
        
        if (zdravotnyStav > MAX_ZDRAVOTNY_STAV || zdravotnyStav <= 0) {
            throw new IllegalArgumentException("V konstrutkore ste zadali zdravotny stav mimo intervalu (0,100>");
        }
        
        if (cisloVezna == null) {
            throw new IllegalArgumentException("V konstrutkore ste zadali cislo vezna ako null");
        }
        
        if (stupenNebezpecenstva == null) {
            throw new IllegalArgumentException("V konstruktore ste zadali null pre stupen nebezpecenstva");
        }
         
        
        this.cisloVezna = cisloVezna;
        this.zdravotnyStav = zdravotnyStav;
        this.pocetRokovOdsudenia = pocetRokovOdsudenia;
        this.stupenNebezpecenstva = stupenNebezpecenstva;
    }
    
    /**
     * Getter na unikatne cislo vezna
     * @return cislo vezna 
     */
    @Override
    public String getCisloVezna() {
        return this.cisloVezna;
    }
    
    /**
     * Getter na pocet rokov, na ktore je vezen odsudeny
     * @return pocet rokov odsudenia vezna
     */
    public int getPocetRokovOdsudenia() {
        return this.pocetRokovOdsudenia;
    }
    
    /**
     * Getter na vysku stupna nebezpecenstva vezna, ktory moze byt nizky, stredny, vysoky
     * @return vysku stupna nebezpecenstva vezna
     */
    @Override
    public StupenNebezpecenstva getStupenNebezpecenstva() {
        return this.stupenNebezpecenstva;
    }
    
    /**
     * Getter na zdravotny stav vezna, ktory je vyjedreny v percentach
     * @return zdravotny stav vezna vyjadreny v percentach
     */
    @Override
    public double getZdravotnyStav() {
        return this.zdravotnyStav;
    }
    
    /**
     * Setter na novy stupen nebezpecenstva vezna a v pripade zadania neplatneho stupna, metoda vyhodi vynimku
     * @param stupenNebezpecenstva je novy stupen nebezpecenstva, ktory ma byt udeleny veznovi
     */
    public void setStupenNebezpecenstva(StupenNebezpecenstva stupenNebezpecenstva) {
        if (stupenNebezpecenstva == null) {
            throw new IllegalArgumentException("V metode set stupuen nebezpecenstva ste zadali parameter ako hodnotu null");
        }
        this.stupenNebezpecenstva = stupenNebezpecenstva;
    }
    
    /**
     * Setter na zmenu poctu rokov odsudenia vezna a v pripade zadania zaporneho parametra, metoda vyhodi vynimku
     * @param pocetRokovOdsudenia je novy pocet rokov odsedenia vezna
     */
    public void setPocetRokovOdsudenia(int pocetRokovOdsudenia) {
        if (pocetRokovOdsudenia < 0) {
            throw new IllegalArgumentException("Zadali ste zaporny parameter pre novy pocet rokov odsudenia");
        }
        this.pocetRokovOdsudenia = pocetRokovOdsudenia;
    }
    
    /**
     * Setter na zmenu zdravotneho stavu vezna a v pripade zadaneho zleho rozmedzia noveho zdravotneho stavu, metoda vyhodi vynimku
     * @param zdravotnyStav je novy zdravotny stav vezna vyjadreny v percentach
     */
    public void setZdravotnyStav(double zdravotnyStav) {
        if (zdravotnyStav > MAX_ZDRAVOTNY_STAV || zdravotnyStav <= 0) {
            throw new IllegalArgumentException("Zadany zdravotny stav musi byt v intervale (0,100>");
        }
        
        this.zdravotnyStav = zdravotnyStav;
    }
    
    /**
     * Metoda znizi stupen nebezpecenstva vezna o jeden stupen a predtym este skontroluje ci je to mozne 
     * @return uspesnost znizenia stupna nebezpecenstva  vezna
     */
    public boolean znizStupenNebezpecenstvaZaDobreSpravanie() {
        if (this.stupenNebezpecenstva.equals(StupenNebezpecenstva.NIZKY)) {
            return false;
        }        
                
        if (this.stupenNebezpecenstva.equals(StupenNebezpecenstva.VYSOKY)) {
            this.stupenNebezpecenstva = StupenNebezpecenstva.STREDNY;
            return true;
        }
        
        if (this.stupenNebezpecenstva.equals(StupenNebezpecenstva.STREDNY)) {
            this.stupenNebezpecenstva = StupenNebezpecenstva.NIZKY;
            return true;
        }
        
        return false;
    }
    
    /**
     * Metoda vypocita zdravotny stav vezna po odpykani trestu, na zaklade daneho ubytku zdravia po kazdych 5 rokoch stravenych za mrezami
     * @return zdravotny stav vezna vyjadreny v percentach po odpykani trestu 
     */
    public double getZdraviePoPrepusteni() {
        int vysl = this.pocetRokovOdsudenia / 5; 
        int zvysok = this.pocetRokovOdsudenia % 5; 
        double ubytokZvysok = 1 - (((1 - KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH) / 5.0) * zvysok);
        
        double zdraviePoPrepusteni = this.zdravotnyStav;
        
        if (vysl > 0) {
            for (int i = 0; i < vysl; i++) {
                zdraviePoPrepusteni = zdraviePoPrepusteni * KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH;
            }
        }
        
        if (ubytokZvysok > 0) {
            zdraviePoPrepusteni = zdraviePoPrepusteni * ubytokZvysok;
        }
        
        BigDecimal bD = new BigDecimal(zdraviePoPrepusteni);
        bD = bD.setScale(1, RoundingMode.HALF_UP);
        return bD.doubleValue();
    }

    /**
     * Metoda, ktora poskytne informacie o celkovom stave vezna v podobe suvisleho textoveho retazca
     * @return textova rerezentacia celkoveho stavu vezna
     */
    @Override
    public String toString() {
        return "Vezen: cislo vezna = " + this.cisloVezna + ", pocet rokov odsudenia = " + this.pocetRokovOdsudenia + ", stupen nebezpecenstva = " + this.stupenNebezpecenstva 
                + ", aktualny zdravotny stav = " + this.zdravotnyStav + " percent" + ", zdravotny stav po odpykani trestu = " + this.getZdraviePoPrepusteni() + " %.";
    }
    
    /**
     * Metoda equals na porovnavanie s inym objektom
     * @param object je porovnavany objekt
     * @return informacia, ci su objekty identicke
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        
        if (!(object instanceof Vezen)) {
            return false;
        }
        
        Vezen p = (Vezen)object;
        double odchylka = 0.005;
        
        if (!(this.cisloVezna.equals(p.cisloVezna))) {
            return false;
        }
        
        if (Math.abs(p.pocetRokovOdsudenia - this.pocetRokovOdsudenia) > odchylka) {
            return false;
        }
        
        if (!(this.stupenNebezpecenstva.equals(p.stupenNebezpecenstva))) {
            return false;
        }
        
        if (Math.abs(this.MIN_POCET_ROKOV_ODSUDENIA - p.MIN_POCET_ROKOV_ODSUDENIA) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH - p.KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.MAX_ZDRAVOTNY_STAV - p.MAX_ZDRAVOTNY_STAV) > odchylka) {
            return false;
        }
        
        if (Math.abs(this.zdravotnyStav - p.zdravotnyStav) > odchylka) {
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Metoda hashCode pre triedu Vezen
     * @return hashCode typu integer pre triedu Vezen
     */
    @Override
    public int hashCode() {
        return (int)this.pocetRokovOdsudenia *
                this.cisloVezna.hashCode() * 
                this.stupenNebezpecenstva.hashCode() * 
                (int)this.MIN_POCET_ROKOV_ODSUDENIA * 
                (int)this.zdravotnyStav *
                (int)this.KOEFICIENT_UBYTOK_ZDRAVIA_PO_5_ROKOCH *
                (int)this.MIN_POCET_ROKOV_ODSUDENIA;
    }
    
    
    
    
    
    
    
            
}
