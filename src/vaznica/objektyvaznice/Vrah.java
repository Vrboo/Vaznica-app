
package vaznica.objektyvaznice;

/**
 *  Trieda Vrah predstavuje Vraha uvezneho v cele veznice, ktora dedi z triedy Vezen
 * @author Dominik Vrbovsky
 */
public class Vrah extends Vezen {
    /**
     * maximalny pocet povolenych navstev pre vraha, ktore moze za mesiac vyuzit
     */
    public static final int MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC = 5;
    
    /**
     * Ak sa vrah dostane do bitky so straznikom, jeho zdravotny stav sa prenasobi danym cislom resp. znizi sa o 10 percent
     */
    public static final double KOEFICIENT_UBYTOK_ZDRAVIA_ZA_BITKU = 0.9;
    
    /**
     * Ak sa vrah dostane do bitky so straznikom, jeho vyska trestu sa zvysi o dany pocet rokov
     */
    public static final int TREST_ZA_BITKU = 4; 
    
    /**
     * pocet navstev, ktore moze vrah za mesiac prijat
     */
    private int pocetPovolenychNavstevZaMesiac;
    
    

    /**
     * Kontruktor pre triedu Vrah
     * @param pocetPovolenychNavstevZaMesiac pocet navstav, ktore moze vrah za mesiac prijat
     * @param cisloVezna jedinecne cislo vezna, ktore mu je pridelene
     * @param pocetRokovOdsudenia pocet rokov odsudenia vraha
     * @param stupenNebezpecenstva stupen nebezpecenstva vraha
     * @param zdravotnyStav zdravotny stav vezna vyjadreny v percentach
     */
    public Vrah(int pocetPovolenychNavstevZaMesiac, String cisloVezna, int pocetRokovOdsudenia, StupenNebezpecenstva stupenNebezpecenstva, double zdravotnyStav) {
        super(cisloVezna, pocetRokovOdsudenia, stupenNebezpecenstva, zdravotnyStav);

        if (pocetPovolenychNavstevZaMesiac > MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC) {
            throw new IllegalArgumentException("V konstruktore ste zadali vacsi pocet povolenych navstev ako je maximum");
        }
        
        
        
        this.pocetPovolenychNavstevZaMesiac = pocetPovolenychNavstevZaMesiac;
        
    }
    
    /**
     * Getter na cislo vraha, ktory automaticky priradi V na začiatok cisla
     * @return cislo vraha
     */
    @Override
    public String getCisloVezna() {
        return "V" + super.getCisloVezna();
    }
    
    /**
     * Getter na pocet povolenych navstev za mesiac, ktore moze vezen vyuzit
     * @return pocet povolenych navstev za mesiac, ktore moze vrah vyuzit
     */
    public int getPocetPovolenychNavstevZaMesiac() {
        return this.pocetPovolenychNavstevZaMesiac;
    }
    
    /**
     * Metoda, ktora udeli jednu povolenu navstevu vrahovi v pripade, ze jeho 
     * zdravotny stav je pod 50%, stupen nebezpecenstva nie je vysoky a udelena povolena navsteva neprekroci maximalny povoleny pocet.
     * @return uspesnost pridania povolenej navstevy
     */
    public boolean pridajPovolenuNavstevu() {
        if (this.pocetPovolenychNavstevZaMesiac + 1 > MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC) {
            return false;
        }


        if (getStupenNebezpecenstva().equals(StupenNebezpecenstva.VYSOKY)) {
            return false;
        }

        this.pocetPovolenychNavstevZaMesiac += 1;
        return true;

    }
    
    /**
     * Metoda, ktora predstavuje, že vrah prijal navstevu a nasledne sa mu odrata jedna povolena navsteva a
     * v pripade, že už ma vycerpane vsetky povolene navstevy, metoda vrati false.
     * @return uspesnost prijatia navstevy
     */
    public boolean prijmiNavstevu() {
        if (this.pocetPovolenychNavstevZaMesiac < 1) {
            return false;
        }
        
        this.pocetPovolenychNavstevZaMesiac -= 1;
        return true;
    }
    
    /**
     * Metoda predstavuje, že vrah sa dostane do fyzickeho konfliktu so 
     * straznikom a nasledne sa mu zhorsi zdravvotny stav o polovicu 
     * a taktiez mu je udeleny trest v podobe zvysenia stupna nebezpecenstva, zvysenia poctu rokov odsudenia
     */
    public void pobiSaSoStraznikom() {
        setStupenNebezpecenstva(StupenNebezpecenstva.VYSOKY);
        setPocetRokovOdsudenia(getPocetRokovOdsudenia() + TREST_ZA_BITKU);
        setZdravotnyStav(getZdravotnyStav() * KOEFICIENT_UBYTOK_ZDRAVIA_ZA_BITKU);
        this.pocetPovolenychNavstevZaMesiac = 0;
    }
    
    /**
     * Metoda, ktora poskytne informacie o celkovom stave vraha
     * @return textova reprezentacia celkoveho stavu vraha
     */
    @Override
    public String toString() {
        return "Vrah : cislo vezna = " + this.getCisloVezna() + ", pocet rokov odsudenia = " + getPocetRokovOdsudenia() + ", stupen nebezpecenstva = "
                + getStupenNebezpecenstva().getReprezentacia() + ", zdravotny stav = " + getZdravotnyStav() + "%, zdravotny stav po odpykani trestu = " + getZdraviePoPrepusteni() + "%, pocet povolenych navstev za mesiac = "
                + this.pocetPovolenychNavstevZaMesiac + ".";

    }
    
    /**
     * Metoda equals na porovnavania s inym objektom
     * @param o je porovnavany objekt
     * @return informacia, či su objekty identicke
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof Vrah)) {
            return false;
        }
        
        if (!(super.equals(o))) {
            return false;
        }
        
        Vrah paVrah = (Vrah)o;
        double odchylka = 0.005;
        
        if ((Math.abs(MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC - paVrah.MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(TREST_ZA_BITKU - paVrah.TREST_ZA_BITKU)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(KOEFICIENT_UBYTOK_ZDRAVIA_ZA_BITKU - paVrah.KOEFICIENT_UBYTOK_ZDRAVIA_ZA_BITKU)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.pocetPovolenychNavstevZaMesiac - paVrah.pocetPovolenychNavstevZaMesiac)) > odchylka) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metoda hashCode pre triedu Vrah
     * @return hashCode typu integer pre triedu Vrah
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)MAX_POCET_POVOLENYCH_NAVSTEV_ZA_MESIAC * (int)KOEFICIENT_UBYTOK_ZDRAVIA_ZA_BITKU *
                (int)TREST_ZA_BITKU * (int)this.pocetPovolenychNavstevZaMesiac;
    }

}
