/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaznica;

import java.util.logging.Level;
import java.util.logging.Logger;
import vaznica.objektyvaznice.BankovyLupic;
import vaznica.objektyvaznice.Granat;
import vaznica.objektyvaznice.Hodnost;
import vaznica.objektyvaznice.IUveznitelny;
import vaznica.objektyvaznice.IZamestnatelny;
import vaznica.objektyvaznice.Lekar;
import vaznica.objektyvaznice.Samopal;
import vaznica.objektyvaznice.Straznik;
import vaznica.objektyvaznice.StupenNebezpecenstva;
import vaznica.objektyvaznice.TypZamestnania;
import vaznica.objektyvaznice.Vrah;
import vynimky.OdobratieVyzbrojeException;
import vynimky.PrepustenieVeznaException;
import vynimky.PrepustenieZamestnancaException;
import vynimky.UveznenieVeznaException;
import vynimky.VlozenieVyzbrojeException;
import vynimky.ZamestnanieZamestnancaException;

/**
 * Trieda Veznica predstavuje veznicu, v ktorej sa uviduju vsetky operacie vykonavanej vo veznici
 * @author Dominik Vrbovsky
 */
public class Veznica {
    /**
     * Atribut cela predstavuje celu vo väznici
     */
    private Cela cela;
    
    /**
     * Atribut straznaVeza predstavuje straznu vezu vo väznici
     */
    private StraznaVeza straznaVeza;
    
    /**
     * Atribut municnySklad predstavuje muničný sklad vo väznici
     */
    private MunicnySklad municnySklad;

    /**
     * Konstrutkor pre triedu Veznica
     * @param maxKapacita je maximalne mnozstvo väznov v cele
     * @param minZdravotnyStav je minimalny zdravotny stav vezna na to, aby mohol byt uväzneny v cele
     * @param financneProstriedky je mnozstvo penazi v eurach, ktore ma k dispozicii municny sklad n nakup vyzbroje
     * @param maxPocGranatov je maximalny pocet granatov, ktory sa zmesti do municneho skladu
     * @param maxPocZamestnancovNaPolUvazok je maximalny pocet zamestnancov zamestnanych na polovicny uvazok, ktorí možu byt zamestnaní
     * @param maxPocUbytLekarov je maximalny pocet lekarov, ktorí možu byť ubytovaný 
     * @param pocetAmbulancii je pocet ambulancii vo vaznici, kde plati že pre jedneho lekara je jedna ambulancia
     */
    public Veznica(int maxKapacita, double minZdravotnyStav, double financneProstriedky, int maxPocGranatov, int maxPocZamestnancovNaPolUvazok,
            int maxPocUbytLekarov, int pocetAmbulancii) {
        this.cela = new Cela(maxKapacita, minZdravotnyStav);
        this.municnySklad = new MunicnySklad(financneProstriedky, maxPocGranatov);
        this.straznaVeza = new StraznaVeza(maxPocZamestnancovNaPolUvazok, maxPocUbytLekarov, pocetAmbulancii);
    }
    
    /**
     * Metod, ktora vytvori bankoveho lupica a zaroven ho uvezni do cely
     * @param ukradCiasPen ciastka, ktoru ukradil bankovy lupic bankeô vyjadrene v eurach
     * @param pocPovVychadzokNaDvor pocet povolenych vychadzok na dvor veznice pre bankoveho lupica
     * @param cisloVezna jedinecne cislo vezna
     * @param pocRokOds pocet rokov odsudenia vezna
     * @param stNeb stupen nebezpecenstva vezna(nizky, stredny, vysoky)
     * @param zdravStav zdravotny stav vezna vyjadreny v percentach
     */
    public void uvezniBankovehoLupica(double ukradCiasPen, int pocPovVychadzokNaDvor, String cisloVezna, int pocRokOds, StupenNebezpecenstva stNeb, double zdravStav) {
        BankovyLupic bankovyLupic = new BankovyLupic(ukradCiasPen, pocPovVychadzokNaDvor, cisloVezna, pocRokOds, stNeb, zdravStav);
        try {
            this.cela.pridajVeznaDoCely(bankovyLupic);
        } catch (UveznenieVeznaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metóda, ktorá vytvorí vraha a zaroveň ho uvezni do cely
     * @param pocPovNav pocet navstav, ktore moze vrah za mesiac prijat
     * @param cisloVezna jedinecne cislo vezna, ktore mu je pridelene
     * @param pocRokOds pocet rokov odsudenia vraha
     * @param stupenNebezpecenstva stupen nebezpecenstva vraha
     * @param zdravStav zdravotny stav vezna vyjadreny v percentach
     */
    public void uvezniVrahaDoCely(int pocPovNav, String cisloVezna, int pocRokOds, StupenNebezpecenstva stupenNebezpecenstva, double zdravStav) {
        Vrah vrah = new Vrah(pocPovNav, cisloVezna, pocRokOds, stupenNebezpecenstva, zdravStav);
        try {
            this.cela.pridajVeznaDoCely(vrah);
        } catch (UveznenieVeznaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora vytvori straznika a zaroven ho zamestna v straznej vezi
     * @param hodnost je hodnost straznika
     * @param typZamestnania je typ zamestnania straznika
     * @param identifikacneCislo je jedinecne identifikacne cislo
     * @param pocRokPrax je pocet rokov praxe straznika
     */
    public void zamestnajStraznikaDoCely(Hodnost hodnost, TypZamestnania typZamestnania, String identifikacneCislo, int pocRokPrax) {
        Straznik straznik = new Straznik(hodnost, typZamestnania, identifikacneCislo, pocRokPrax);
        try {
            this.straznaVeza.zamestnajZamestnanca(straznik);
        } catch (ZamestnanieZamestnancaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora vytvori vytvori lekara a zaroven ho zamestna v straznej vezi
     * @param typZamestnania je typ zamestnania lekara
     * @param identifikacneCislo je jedicne identifikacne cislo lekara
     * @param pocRokPrax je pocet rokov praxe lekara
     * @param vzdialBydl je pocet kilometrov bydliska lekara od väznice
     */
    public void zamestnajLekaraVStraznejVezi(TypZamestnania typZamestnania, String identifikacneCislo, int pocRokPrax, int vzdialBydl) {
        Lekar lekar = new Lekar(typZamestnania, identifikacneCislo, pocRokPrax, vzdialBydl);
        try {
            this.straznaVeza.zamestnajZamestnanca(lekar);
        } catch (ZamestnanieZamestnancaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora vytvori granat a zaroven ho ulozi do municneho skladu
     * @param serioveCislo je jedicne seriove cislo granatu
     * @param cena je cena granatu v eurach
     * @param hm je hmotnost granatu v kilogramoch
     */
    public void vlozGranatDoMunicnehoSkladu(String serioveCislo, double cena, double hm) {
        Granat granat = new Granat(serioveCislo, cena, hm);
        try {
            this.municnySklad.vlozVyzbroj(granat);
        } catch (VlozenieVyzbrojeException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora vytvori samopal a zaroven ho vlozi do municneho skladu 
     * @param kapZas je mnozstvo nabojov v zabodniku samopalu
     * @param serioveCislo je jedinecne seriove cislo samopalu
     * @param cena je cena samopalu v eurach
     */
    public void vlozSamopalDoMunicnehoSkladu(int kapZas, String serioveCislo, double cena) {
        Samopal samopal = new Samopal(kapZas, serioveCislo, cena);
        try {
            this.municnySklad.vlozVyzbroj(samopal);
        } catch (VlozenieVyzbrojeException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metoda, ktora prepusti vezna z cely
     * @param cisloVezna je cislo vezna, ktoreho chceme prepustit
     */
    public void prepustiVeznaZCely(String cisloVezna) {
        try {
            this.cela.prepustiVeznaZCely(cisloVezna);
        } catch (PrepustenieVeznaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora prepusti zamesntnanca zo straznej vezi
     * @param identifikacneCislo je identifikacne cislo zamestnanca, ktoreho chceme prepustit
     */
    public void prepustiZamestnancaZoStraznejVezi(String identifikacneCislo) {
        try {
            this.straznaVeza.prepustiZamestnanca(identifikacneCislo);
        } catch (PrepustenieZamestnancaException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora odoberie vyzbroj z municneho skladu
     * @param serioveCislo je serivoe cislo vybroje, ktoru chceme odobrať z municneho skladu
     */
    public void odoberVyzbrojZMunicnehoSkladu(String serioveCislo) {
        try {
            this.municnySklad.odoberVyzbroj(serioveCislo);
        } catch (OdobratieVyzbrojeException ex) {
            Logger.getLogger(Veznica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda, ktora znizi stupen nebezpecenstva vezna, pokial je to možne
     * @param cisloVezna je cislo vezna , ktoremu chceme znizit stupen nebezpecentsva
     * @return informacia o uspesnosti znizenia stupna nebezpecnosti
     */
    public boolean znizStupenNebezpecenstva(String cisloVezna) {
        return this.cela.getVeznaVCela(cisloVezna).znizStupenNebezpecenstvaZaDobreSpravanie();
    }
    
    public void osetriOsobyLekar(String identifikacneCislo, int pocOsOs) {
        IZamestnatelny zamestnanec = this.straznaVeza.getZamestnanca(identifikacneCislo);
        if (zamestnanec instanceof Straznik) {
            throw new IllegalArgumentException("Zadali ste cislo straznika");
        }
        Lekar lekar = (Lekar)zamestnanec;
        lekar.setPocetOsetrenychOsob(pocOsOs);
    }
    
    /**
     * Metoda, ktora prida straznikovi odpracovane hodiny
     * @param identifikacneCislo je cislo pracujuceho straznika
     * @param pocHod je pocet odpracovanych hodin straznika
     */
    public void pridajHodinyPraceStraznik(String identifikacneCislo, int pocHod) {
        IZamestnatelny zamestnanec = this.straznaVeza.getZamestnanca(identifikacneCislo);
        if (zamestnanec instanceof Lekar) {
            throw new IllegalArgumentException("Zadali ste cislo lekara");
        }
        Straznik straznik = (Straznik)zamestnanec;
        straznik.pridajOdpracovaneHodiny(pocHod);
    }
    
    /**
     * Metoda, ktora zaeviduje pracu bankoveho lupica a vrati zarobene peniaze banke, ktoru vykradol
     * @param cisloLupica cislo pracujuceho bankoveho lupica
     * @param pocHod pocet odpracovanych hodin bankoveho lupica
     */
    public void pracujBankovyLupic(String cisloLupica, int pocHod) {
        IUveznitelny vezen = this.cela.getVeznaVCela(cisloLupica);
        if (vezen instanceof Vrah) {
            throw new IllegalArgumentException("Zadali ste cislo vraha");
        }
        BankovyLupic bL = (BankovyLupic)vezen;
        bL.vratBankePeniaze(pocHod);
    }
    
    /**
     * Metoda, ktora ulozi do suboru veznov s vysokym stupnom nebezpecenstva
     * @param nazovSuboru je nazov suboru, ktory bude uloženy
     * @return informacia o uspesnosti ulozenia
     */
    public boolean ulozDoSuboruNebezpecnychVeznov(String nazovSuboru) {
        return this.cela.ulozDoSuboruNebezpecnychVeznov(nazovSuboru);
    }
    
    /**
     * Metoda, ktora ulozi do suboru cisla ubytovanych lekarov
     * @param nazovSuboru je nazov suboru, do ktoreho ulozime cisla lekarov
     * @return informacia o uspesnosti ulozenia
     */
    public boolean ulozDoSuboruCislaUbytLekarov(String nazovSuboru) {
        return this.straznaVeza.ulozDoSuboruIdenCislaUbytovanychLekarov(nazovSuboru);
    }
    
    /**
     * Metoda, ktora nasimuluje bitku vraha so straznikom a nasledne postihne vraha trest 
     * @param idenCislo cislo vraha, ktorý sa pobil so straznikom
     */
    public void pobiSaSoStraznikomVrah(String idenCislo) {
        IUveznitelny vezen = this.cela.getVeznaVCela(idenCislo);
        if (vezen instanceof Straznik) {
            throw new IllegalArgumentException("Zadali ste cislo bankoveho lupica");
        }
        Vrah vrah = (Vrah)vezen;
        vrah.pobiSaSoStraznikom();
    }
    
    /**
     * Metoda, ktora prida povolenu navstevu vrahovi
     * @param cisloVraha je cislo vraha, ktoremu chceme pridať povolenu navstevu
     * @return informacia o uspesnosti pridania povolenej navstevy
     */
    public boolean pridajPovolenuNavstevuVrahovi(String cisloVraha) {
        IUveznitelny vezen = this.cela.getVeznaVCela(cisloVraha);
        if (vezen instanceof Straznik) {
            throw new IllegalArgumentException("Zadali ste cislo bankoveho lupica");
        }
        Vrah vrah = (Vrah)vezen;
        return vrah.pridajPovolenuNavstevu();
    }
    
    /**
     * Meotda, ktora simuluje, že vrah prijme navstevu 
     * @param cisloVraha je cislo vraha, ktory prijme navstevu
     * @return informacia o uspesnosti prijatia navstevi
     */
    public boolean prijmiNavstevuVrah(String cisloVraha) {
        IUveznitelny vezen = this.cela.getVeznaVCela(cisloVraha);
        if (vezen instanceof Straznik) {
            throw new IllegalArgumentException("Zadali ste cislo straznika");
        }
        Vrah vrah = (Vrah)vezen;
        return vrah.prijmiNavstevu();
    }
    
    /**
     * Metoda, ktora vypise celkovy stav veznice
     * @return znakove reprezentacia stavu väznice
     */
    public String toString() {
        return this.cela.toString() + "\n" + this.straznaVeza.toString() + "\n" + this.municnySklad.toString();
    }
}
