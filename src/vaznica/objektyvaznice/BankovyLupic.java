
package vaznica.objektyvaznice;

import java.math.BigDecimal;
import java.math.RoundingMode;




/**
 *  Trieda BankovyLupic predstavuje bankoveho lupica, ktory je uvezneny v cele 
 * @author Dominik Vrbovsky
 */
public class BankovyLupic extends Vezen {
    /**
     * maximalny pocet povolenych vychadzok na dvor veznica pre jedneho bankoveho lupica
     */
    private static final int MAX_POCET_POVOLENYCH_VYCHADZOK_NA_DVOR = 10;
    
    /**
     * Hodinova mzda pre bankoveho lupica
     */
    private static final double HODINOVA_MZDA = 1.5;
    
    /**
     * ciastka, ktoru ukradil bankovy lupic banke; vyjadrene v eurach
     */
    private double ukradnutaCiastkaPenazi;
    
    /**
     * ciastka, ktoru bankovy lupic vratil banke, ktoru vykradol; vyjarene v eurach
     */
    private double vratenaCiastkaPenazi;
    
    /**
     * pocet povolenych vychadzok na dvor veznice pre bankoveho lupica
     */
    private int pocetPovolenychVychadzokNaDvor;

    /**
     * Konstruktor pre triedu BankovyLupic 
     * @param ukradnutaCiastkaPenazi ciastka, ktoru ukradil bankovy lupic bankeô vyjadrene v eurach
     * @param pocetPovolenychVychadzokNaDvor pocet povolenych vychadzok na dvor veznice pre bankoveho lupica
     * @param cisloVezna jedinecne cislo vezna
     * @param pocetRokovOdsudenia pocet rokov odsudenia vezna
     * @param stupenNebezpecenstva stupen nebezpecenstva vezna(nizky, stredny, vysoky)
     * @param zdravotnyStav zdravotny stav vezna vyjadreny v percentach
     */
    public BankovyLupic(double ukradnutaCiastkaPenazi,
            int pocetPovolenychVychadzokNaDvor, String cisloVezna, int pocetRokovOdsudenia, 
            StupenNebezpecenstva stupenNebezpecenstva, double zdravotnyStav) {
        super(cisloVezna, pocetRokovOdsudenia, stupenNebezpecenstva, zdravotnyStav);
        
        if (pocetPovolenychVychadzokNaDvor > MAX_POCET_POVOLENYCH_VYCHADZOK_NA_DVOR) {
            throw new IllegalArgumentException("V konstruktore ste zadali pocet povolenych navstev, ktory prekrocil maximum");
        }
        
        if (ukradnutaCiastkaPenazi < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapornu ukradnutu ciastku penazi");
        }
        this.ukradnutaCiastkaPenazi = ukradnutaCiastkaPenazi;
        this.pocetPovolenychVychadzokNaDvor = pocetPovolenychVychadzokNaDvor;
        this.vratenaCiastkaPenazi = 0;
    }
    
    /**
     * Getter na cislo bankoveho lupica, ktoremu sa automaticky pridá B na začiatok čisla
     * @return čislo bankoveho lupiča
     */
    @Override
    public String getCisloVezna() {
        return "B" + super.getCisloVezna();
    }
    
    /**
     * Getter na ciastku penazi, ktoru sa podarilo ukradnut bankovemu lupicovi
     * @return ciastku penazi vyjadrenu v eurach, ktoru ukradol banke bankovy lupic 
     */
    public double getUkradnutaCiastkaPenazi() {
        return this.ukradnutaCiastkaPenazi;
    }

    /**
     * Getter na pocet vychadzok na vezensky dvor, ktore moze bankovy lupic vyuzit
     * @return pocet povolenych vychadzok na dvor, ktore ma bankovy lupic
     */
    public int getPocetPovolenychVychadzokNaDvor() {
        return this.pocetPovolenychVychadzokNaDvor;
    }
    
    /**
     * Getter na ciastku penazi vyjadrenu v eurach, ktoru sa uz podarilo vratit bakovemu lupicovi banke, ktoru vylupil
     * @return ciastka penazi vyjadrena v eurach, ktoru bankovy lupic uz vratil banke
     */
    public double getVratenaCiastkaPenazi() {
        return this.vratenaCiastkaPenazi;
    }
    
    /**
     * Bankovy lupic ma moznost vo veznici pracovat a tak moze vratit banke ukradnutu ciastku penazi.
     * Metoda skontroluje, ci lupic nechce vratit vacsiu ciastku penazi, ako je jeho zostavajuci
     * dlh voci banke a taktiez ci nie je zadany zaporny parameter.
     * @param pocetOdpracovanychHodin pocet hodin, ktore odpracoval bankovy lupic 
     * @return uspesnost vratenia penazi
     */
    public boolean vratBankePeniaze(int pocetOdpracovanychHodin) {
        if (pocetOdpracovanychHodin <= 0) {
            throw new IllegalArgumentException("Zadany pocet odpracovanych hodin musi byt vacsi ako 0");
        }
        
        double zarobenePeniaze = pocetOdpracovanychHodin * HODINOVA_MZDA;
        
        if (zarobenePeniaze > this.getDlhBanke()) {
            return false;
        }
        
        this.vratenaCiastkaPenazi += zarobenePeniaze;
        return true;
    }
    
    /**
     * Getter na ciastku penazi vyjadrenu v eurach, ktoru bankovy lupic este nevratil banke, ktoru vykradol
     * @return ciastku penazi vyjadrenu v eurach, ktoru bankovy lupic este nevratil banke, ktoru vykradol
     */
    public double getDlhBanke() {
        return this.ukradnutaCiastkaPenazi - this.vratenaCiastkaPenazi;
    }
    
    /**
     * Sukromna metoda, ktora vypocita kolko minut este musi bankovy lupic odpracovat pri danej hodinove mzde, aby vratil celu ukradnutu ciastku banke
     * @return pocet minut prace potrebnych na vratenia ukradnutej ciastky banke
     */
    private double vypocitajPotrebnyPocetOdpracovnychMinutNaSplatenieDlhu() {
        double p = ((float)this.getDlhBanke() / HODINOVA_MZDA);
        double pa = p * 60;
        return pa;
    }
    
    /**
     * Getter na pocet zvysnych minut v praci po odpocitani celych hodin, aby bankovy lupic vratil ukradnutu ciastku penazi banke
     * @return pocet zvysnych minut v praci po odpocitani celych hodin, aby bankovy lupic vratil ukradnutu ciastku penazi banke
     */
    public double getMinuty() {
        double minuty =  this.vypocitajPotrebnyPocetOdpracovnychMinutNaSplatenieDlhu() % 60;
        BigDecimal bD = new BigDecimal(minuty);
        bD = bD.setScale(0, RoundingMode.HALF_UP);
        return bD.doubleValue();
    }
    
    /**
     * Getter na pocet celych hodin, ktore musi bankovy lupic este odpracovat, aby vratil ukradnutu ciastku penazi banke
     * @return pocet celych hodin, ktore musi bankovy lupic este odpracovat, aby vratil ukradnutu ciastku penazi banke
     */
    public double getHodiny() {
        double hodiny = this.vypocitajPotrebnyPocetOdpracovnychMinutNaSplatenieDlhu() / 60;
        BigDecimal bD = new BigDecimal(hodiny);
        bD = bD.setScale(0, RoundingMode.HALF_UP);
        return bD.doubleValue();
    }
    
    /**
     * Metoda, ktora poskytne aktualny celkovy stav bankoveho lupica v podobe textoveho retazca
     * @return textova reprezentacia celkoveho stavu bankoveho lupica
     */
    @Override
    public String toString() {
        return "Bankovy lupic :" + " cislo bankoveho lupica = " + this.getCisloVezna() + ", aktualny zdravotny stav =" + getZdravotnyStav() +
                "%, zdravotny stav po odpykani trestu = " + getZdraviePoPrepusteni() +
                "%, stupen nebezpecenstva = " + getStupenNebezpecenstva().getReprezentacia() + 
                ", pocet rokov odsudenia = " + getPocetRokovOdsudenia()  + ", ukradnuta ciastka penazi = " 
                + this.ukradnutaCiastkaPenazi + "€, vratena ciastka penazi banke = " + this.vratenaCiastkaPenazi + "€, zostavajuci dlh voci banke = " + this.getDlhBanke() 
                + "€, na celkove splatenie dlhu potrebuje odpracovat este " + this.getHodiny() + " hodin a " + this.getMinuty() 
                + " minut, pocet povolenych vychadzok na dvor = " + this.pocetPovolenychVychadzokNaDvor + ".";
    }
    
    /**
     * Metóda equals pre porovnavanie s iným objektom
     * @param o je objekt, ktorý sa bude porovnavať 
     * @return výsledok porovnávania 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof BankovyLupic)) {
            return false;
        }
        
        if (!(super.equals(o))) {
            return false;
        }
        
        BankovyLupic paBankLupic = (BankovyLupic)o;
        double odchylka = 0.005;
        
        if ((Math.abs(this.MAX_POCET_POVOLENYCH_VYCHADZOK_NA_DVOR - paBankLupic.MAX_POCET_POVOLENYCH_VYCHADZOK_NA_DVOR)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(HODINOVA_MZDA - paBankLupic.HODINOVA_MZDA)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.pocetPovolenychVychadzokNaDvor - paBankLupic.pocetPovolenychVychadzokNaDvor)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.vratenaCiastkaPenazi - paBankLupic.vratenaCiastkaPenazi)) > odchylka) {
            return false;
        }
        
        if ((Math.abs(this.ukradnutaCiastkaPenazi - paBankLupic.ukradnutaCiastkaPenazi)) > odchylka) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metóda hashCode pre triedu BankovyLupic
     * @return hashCode typu integer pre triedu BankovyLupic
     */
    @Override
    public int hashCode() {
        return super.hashCode() * (int)HODINOVA_MZDA * 
                (int)MAX_POCET_POVOLENYCH_VYCHADZOK_NA_DVOR *
                (int)this.vratenaCiastkaPenazi *
                (int)this.ukradnutaCiastkaPenazi *
                (int)this.pocetPovolenychVychadzokNaDvor;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
            
            
}
