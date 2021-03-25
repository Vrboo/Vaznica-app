
package vaznica;

import java.util.HashMap;
import vaznica.objektyvaznice.BankovyLupic;
import vaznica.objektyvaznice.Vrah;
import vaznica.objektyvaznice.StupenNebezpecenstva;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import vynimky.UveznenieVeznaException;
import vynimky.PrepustenieVeznaException;
import vaznica.objektyvaznice.IUveznitelny;


/**
 *  Trieda Cela predstavuje celu vo veznici, do ktorej su uveznovani vezni
 * @author Dominik Vrbovsky
 */
public class Cela {
    /**
     * maximalny pocet veznov v cele
     */
    private int maxKapacita;
    
    /**
     * aktualny pocet veznov v cele
     */
    private int aktualnyPocetVeznov;
    
    /**
     * minimalny potrebny zdravotny stav vezna na uveznenia do tejto cely vyjadreny v percentach
     */
    private double minZdravotnyStavVeznov; 
    
    /**
     * Zoznam(kontajner) veznov v cele 
     */
    private HashMap<String , IUveznitelny> vezniVCele;

    /**
     * Konstruktor pre triedu Cela
     * @param maxKapacita je maximalny pocet veznov v cele
     * @param minZdravotnyStavVeznov je Zoznam(kontajner) veznov v cele
     */
    public Cela(int maxKapacita, double minZdravotnyStavVeznov) {
        if (maxKapacita < 0) {
            throw new IllegalArgumentException("V kontruktore ste zadali zapornu maximalnu kapacitu cely");
        }
        
        if (minZdravotnyStavVeznov < 0 || minZdravotnyStavVeznov > 100) {
            throw new IllegalArgumentException("Zadanu zdravotny stav musi byt v intervale (0,100>");
        }
        
        
        this.maxKapacita = maxKapacita;
        this.aktualnyPocetVeznov = 0;
        this.minZdravotnyStavVeznov = minZdravotnyStavVeznov;
        this.vezniVCele = new HashMap<String , IUveznitelny>();
        
    }
    
    public Cela(String nazovSuboru) throws IOException {
        File file = new File(nazovSuboru);
        Scanner scan = new Scanner(file);
        
        
        this.maxKapacita = scan.nextInt();
        this.minZdravotnyStavVeznov = scan.nextDouble();
        this.aktualnyPocetVeznov = 0;
        this.vezniVCele = new HashMap<String , IUveznitelny>();
    }
    
    /**
     * Metoda, ktora predstavuje priadnie vezna do cely, skontruluje ci je to mozne, vzhladom na minimalny zdravotny stav cely a kapacity cely. 
     * @param vezen je trieda vezna, ktoreho chceme uvaznit do cely
     * @throws UveznenieVeznaException v pripade neuspesneho pridania
     */
    public void pridajVeznaDoCely (IUveznitelny vezen) throws UveznenieVeznaException {
        if (vezen == null) {
            throw new IllegalArgumentException("V metode pridaj vezna do cely ste zadali parameter s hodnotou null");
        }
        
        if (this.aktualnyPocetVeznov + 1 > this.maxKapacita) {
            throw new UveznenieVeznaException("Pridanim vezna do cely sa prekroci kapacita cely");
        }
        
        if (vezen.getZdravotnyStav() < this.minZdravotnyStavVeznov) {
            throw new UveznenieVeznaException("Vezen ma prilis nizky zdravotny stav");
        }
        
        if (this.vezniVCele.containsKey(vezen.getCisloVezna())) {
            throw new UveznenieVeznaException("Tento vezen uz je v cele uvezneny");
        }
        
        this.vezniVCele.put(vezen.getCisloVezna() , vezen);
        this.aktualnyPocetVeznov += 1;
        
    }
    
    
    /**
     * Metoda, ktora predstavuje prepustenia a vyhodenia vezna z cely na zaklade zadaneho parametra
     * @param cisloVezna je cislo vezna, ktoreho chceme prepustit 
     * @return vezen, ktoreho sme prepustili z cely
     * @throws PrepustenieVeznaException v priprade neuspesneho prepustenia
     */
    public IUveznitelny prepustiVeznaZCely(String cisloVezna) throws PrepustenieVeznaException {
        if (cisloVezna == null) {
            throw new IllegalArgumentException("V metode prepusti vezna z celz ste zadali parameter s hodnotou null");
        }
        
        IUveznitelny prepustenyVezen = this.vezniVCele.remove(cisloVezna);
        
        if (prepustenyVezen == null) {
            throw new PrepustenieVeznaException("Vezen s tymto cislom sa v cele nenachadza");
        }
        
        
        this.vezniVCele.remove(cisloVezna);
        this.aktualnyPocetVeznov -= 1;
        return prepustenyVezen;
        
    }
    
    /**
     * Getter na vezna, ktoreho si vyziadame na zaklade zadaneho parametra
     * @param cisloVezna je cislo vezna, s ktorym chcem pracovat
     * @return vezen, s ktorym chcem pracovat 
     */
    public IUveznitelny getVeznaVCela(String cisloVezna) {
        if (cisloVezna == null) {
            throw new IllegalArgumentException("V metode get vezna v cele ste zadali parameter s hodnotou null");
        }
        
        if (!(this.vezniVCele.containsKey(cisloVezna))) {
            throw new IllegalArgumentException("V cele sa vezen s tymto cislom nenachadza");
        }
        
        IUveznitelny vezenVCele = this.vezniVCele.get(cisloVezna);
        
        return vezenVCele;
    }
    
     /**
      * Metoda, ktora vyhodi z cely veznov, ktory maju nizsi zdravotny stav ako je minimalny zdravotny stav cely
      */
    public void vyhodZCelyVeznaSNizkymZdravStavom() {
        Iterator<Map.Entry<String, IUveznitelny>> iter = this.vezniVCele.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, IUveznitelny> pair = iter.next();
            IUveznitelny obj = pair.getValue();
            if (obj.getZdravotnyStav() < this.minZdravotnyStavVeznov) {
                iter.remove();
            }
        }
        
    }
    
    /**
     * Getter na pocet vrahov, ktory sa nachadzaju v cele
     * @return pocet vrahov cele
     */
    public int getPocetVrahovVCele() {
        int pocetVrahov = 0;
    
        for (IUveznitelny v : this.vezniVCele.values()) {
            if (v instanceof Vrah) {
                pocetVrahov += 1;
            }
        }
        
        return pocetVrahov;
    }
    
    /**
     * Getter na pocet bankovych lupicov, ktory sa nachadzaju v cele
     * @return pocet bankovych lupicov v cele
     */
    public int getPocetBankovychLupicovVCele() {
        int pocetBankLupicov = 0;
        
        for (IUveznitelny v : this.vezniVCele.values()) {
            if (v instanceof BankovyLupic) {
                pocetBankLupicov += 1;
            }
        }
        
        return pocetBankLupicov;
    }
    
    /**
     * Metoda, ktora poskytuje aktualny stav cely v podobe textoveho retazca
     * @return textova reprezentacia aktualneho stavu cely
     */
    @Override
    public String toString() {
        String pom = "{Poziadavky cely: Minimalny zdravotny stav vezna = " + this.minZdravotnyStavVeznov + "%." +
                " Maximalny pocet veznov v cele = " + this.maxKapacita + ".} \n" +
                "Aktualny pocet vaznov v cele je " + this.aktualnyPocetVeznov + ", z toho pocet vrahov je " + this.getPocetVrahovVCele() + " a pocet bankovych lupicov je " + 
                this.getPocetBankovychLupicovVCele() + ":\n";
        
        if (this.getPocetVrahovVCele() > 0) {
            pom = pom + "\nVrahovia: \n";
            for (IUveznitelny v : this.vezniVCele.values()) {
                if (v instanceof Vrah) {
                    pom = pom + v.toString() + "\n";
                }
            }
        }
        
        if (this.getPocetBankovychLupicovVCele() > 0) {
            pom = pom + "\nBankovy lupici: \n";
            for (IUveznitelny v : this.vezniVCele.values()) {
                if (v instanceof BankovyLupic) {
                    pom = pom + v.toString() + "\n";
                }
            }
        }
        
        pom = pom + "\n*******************************************************";
        
        return pom;
    }
    
    /**
     * Metoda, ktora ulozi do suboru veznov, ktory maju vysoky stupen nebezpecentsva
     * @param nazovSuboru je nazov suboru, do ktoreho chceme aby sa ulozili nebezpecni vezni
     * @return uspesnost ulozenia veznov do suboru (true - uspesne)
     */
    public boolean ulozDoSuboruNebezpecnychVeznov(String nazovSuboru) {
        File subor = new File(nazovSuboru);
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(subor);
            writer.println("Vezni s vysokym stupnom nebezpecenstva: ");
            for (IUveznitelny u : this.vezniVCele.values()) {
                if (u.getStupenNebezpecenstva().equals(StupenNebezpecenstva.VYSOKY)) {
                    writer.println(u.toString());
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        
        return true;
        
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    

