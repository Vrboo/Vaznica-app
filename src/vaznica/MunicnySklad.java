
package vaznica;
import vaznica.objektyvaznice.Granat;
import vaznica.objektyvaznice.Samopal;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import vynimky.VlozenieVyzbrojeException;
import vynimky.OdobratieVyzbrojeException;
import vaznica.objektyvaznice.VyzbrojitelnaJednotka;
        
/**
 *  Trieda MunicnySklad predstavuje municny sklad veznice, do ktorej sa uklada vyzbroj
 * @author Dominik Vrbovsky
 */
public class MunicnySklad {
    /**
     * mnozstvo financnych prostriedkov, ktore ma municny sklad k dispozicii
     */
    private double financneProstriedky;
    
    /**
     * maximalny pocet granat, ktore mozeme uskladnit v municnom sklade
     */
    private int maxPocetGranatov;
    
    /**
     * pocet granatov, ktore sa aktualne nachadzaju v minucnom sklade
     */
    private int aktualnyPocetGranatov;
    
    /**
     * Zoznam(kontajner) vyzbroje uskladnenej v municnom sklade
     */
    private HashMap<String, VyzbrojitelnaJednotka> vyzbroj;
    
    /**
     * Konstruktor pre triedu MunicnySklad
     * @param financneProstriedky mnozstvo financnych prostriedkov, ktore ma municny sklad k dispozicii
     * @param maxPocetGranatov pocet granatov, ktore sa aktualne nachadzaju v minucnom sklade
     */
    public MunicnySklad(double financneProstriedky, int maxPocetGranatov) {
        if (financneProstriedky < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zaporne mnozstvo financnych prostriedkov");
        }
        
        if (maxPocetGranatov < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zaporny maximalny pocet granatov");
        }
        
        this.financneProstriedky = financneProstriedky;
        this.maxPocetGranatov = maxPocetGranatov;
        this.aktualnyPocetGranatov = 0;
        this.vyzbroj = new HashMap<String, VyzbrojitelnaJednotka>();
    }
    
    /**
     * Konstruktor pre triedu MunicnySklad, ktory nacita hodnoty zo suboru
     * @param nazovSuboru nazov suboru, z ktoreho nacita hodnoty pre atributy
     * @throws IOException v pripade neexistujuceho zadaneho suboru 
     */
    public MunicnySklad(String nazovSuboru) throws IOException {
        File file = new File(nazovSuboru);
        Scanner scan = new Scanner(file);
        
        this.financneProstriedky = scan.nextDouble();
        this.maxPocetGranatov = scan.nextInt();
        this.aktualnyPocetGranatov = 0;
        this.vyzbroj = new HashMap<String , VyzbrojitelnaJednotka>();
    }
    
    /**
     * Metoda predstavuje vlozenia vyzbroje do municneho sklade, pokial ma na to veznica financne prostriedky a je este miesto pre granaty
     * @param v je vyzbroj, ktoru chceme vlozit do municneho sklad
     * @throws VlozenieVyzbrojeException v pripade neuspesneho vlozenia
     */
    public void vlozVyzbroj(VyzbrojitelnaJednotka v) throws VlozenieVyzbrojeException {
        if (v == null) {
            throw new IllegalArgumentException("V metode vloz vyzbroj ste zadali parameter s hodnotou null") ;
        }
        
        if (v.getCena() > this.financneProstriedky) {
            throw new VlozenieVyzbrojeException("Na vlozenie tejto vyzbroje nema municny sklad dostatocne mnozstvo financnych prostriedkov");
        }
        
        if (v instanceof Granat) {
            if (this.aktualnyPocetGranatov + 1 > this.maxPocetGranatov) {
                throw new VlozenieVyzbrojeException("Na vlozenie granatu uz nie je v municnom sklade miesto");
            }
        }
        
        if (this.vyzbroj.containsKey(v.getSerioveCislo())) {
            throw new VlozenieVyzbrojeException("Tato vyzbroj sa uz v municnom sklade nachadza");
        }
        
        this.vyzbroj.put(v.getSerioveCislo(), v);
        this.financneProstriedky -= v.getCena();
        if (v instanceof Granat) {
            this.aktualnyPocetGranatov += 1;
        }
        
    }
    
    /**
     * Metoda, ktora predstavuje odobratie vyzbroje z municneho skladu na zaklade zadaneho parametru
     * @param serioveCislo je seriove cislo vyzbroje, ktoru odobrat z minucneho skladu
     * @return vyzbroj, ktoru sme odobral z minucneho skladu
     */
    public VyzbrojitelnaJednotka odoberVyzbroj(String serioveCislo) throws OdobratieVyzbrojeException {
        if (serioveCislo == null) {
            throw new IllegalArgumentException("V metode odober vyzbroj ste zadali parameter s hodnotou null");
        }
        
        VyzbrojitelnaJednotka odobrataVyzbroj = this.vyzbroj.remove(serioveCislo);
        
        if (odobrataVyzbroj == null) {
            throw new OdobratieVyzbrojeException("Vyzbroj s tymto seriovym cislom sa v sklade nenachadza");
        }
        
        this.vyzbroj.remove(serioveCislo);
        
        if (odobrataVyzbroj instanceof Granat) {
            this.aktualnyPocetGranatov -= 1;
        }
        
        return odobrataVyzbroj;
    } 
    
    /**
     * Getter na vyzbroj, s ktorou chceme pracovat na zaklade zadaneho parametra
     * @param serioveCislo je seriove cislo vyzbrojeô s ktorou chceme pracovat 
     * @return vyzbroj, ktoru sme si vyziadali
     */
    public VyzbrojitelnaJednotka getVyzbroj(String serioveCislo) {
        if (serioveCislo == null) {
            throw new IllegalArgumentException("V metode get vyzbroj ste zadali parameter s hodnotou null");
        }
        
        if (!(this.vyzbroj.containsKey(serioveCislo))) {
            throw new IllegalArgumentException("V municnom sklade nie je vyzbroj s tymto seriovym cislom");
        }
        
        VyzbrojitelnaJednotka v = this.vyzbroj.get(serioveCislo);
        
        return v;
    }
    
    /**
     * Getter na pocet samopalov, ktore sa nachdzaju v municnom sklade
     * @return pocet samopalov nachadzajucich sa v minucnom sklade
     */
    public int getPocetSamopalov() {
        int pocetSamopalov = 0;
        for (VyzbrojitelnaJednotka v : this.vyzbroj.values()) {
            if (v instanceof Samopal) {
                pocetSamopalov += 1;
            }
        }
        return pocetSamopalov;
    }
    
    /**
     * Getter na pocet granat, ktore sa nachadzaju v minucnom sklade
     * @return pocet granatov nachadzajucich sa v minucnom sklade
     */
    public int getPocetGranatov() {
        return this.aktualnyPocetGranatov;
    }
    
    
    /**
     * Metoda, ktora poskytuje aktualny stav municneho skladu v podobe textoveho retazca
     * @return textova reprezentacia aktualneho stavu municneho skladu
     */
    @Override
    public String toString() {
        String vysl = "Municny sklad: maximalny pocet granatov = " + 
                this.maxPocetGranatov + ", aktualne financne prostriedky = " + this.financneProstriedky + 
                " €, aktualny pocet granatov = " + this.aktualnyPocetGranatov + ", aktualny pocet samopalov = " + this.getPocetSamopalov() + ": \n";
        
        if (this.aktualnyPocetGranatov > 0) {
            vysl += "\nGranaty: \n";
            for (VyzbrojitelnaJednotka v : this.vyzbroj.values()) {
                if (v instanceof Granat) {
                    vysl += v.toString() + "\n";
                }
            }       
        }
    
        
        if (this.getPocetSamopalov() > 0) {
            vysl += "\nSamopaly: \n";
            for (VyzbrojitelnaJednotka v : this.vyzbroj.values()) {
                if (v instanceof Samopal) {
                    vysl += v.toString() + "\n";
                }  
            }
        }
        
        
        return vysl;
    }
}
