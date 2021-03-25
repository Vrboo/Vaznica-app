
package vaznica;

import vaznica.objektyvaznice.Straznik;
import vaznica.objektyvaznice.Lekar;
import vaznica.objektyvaznice.TypZamestnania;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import vynimky.PrepustenieZamestnancaException;
import vynimky.ZamestnanieZamestnancaException;
import vaznica.objektyvaznice.IZamestnatelny;

/**
 *  Trieda StraznaVeza predstavuje straznu vezu vo veznici, v ktorej su zamestnani potrebni zamestnanci
 * @author Dominik Vrbovsky
 */

public class StraznaVeza {
    /**
     * maximalny pocet zamestnancov, ktorych moze strazna veza zamestnat na polovicny uvazok
     */
    private int maxPocetZamestnancovNaPolUvazok;
    
    /**
     * pocet zamestnancov, ktory su aktualne zamestnani v straznev vezi
     */
    private int aktualnyPocetZamestnancovNaPolUvazok;
    
    /**
     * maximalny pocet lekarov, ktorych moze strazna veza ubytovat
     */
    private int maxPocetUbytovanychLekarov;
    
    /**
     * pocet lekarov, ktory su aktualne ubytovany
     */
    private int aktualnyPocetUbytovanychLekarov;
    
    /**
     * pocet lekarov, ktory su aktualne zamestnani v straznej vezi
     */
    private int aktualnyPocetLekarov;
    
    /**
     * pocet ambulancii, ktore sa nachadzaju v straznej vezi; jedna ambulancia = jeden lekar
     */
    private int pocetAmbulancii;
    
    /**
     * Zoznam(kontajner) zamestnancov straznej vezi
     */
    private HashMap<String, IZamestnatelny> zamestnanci;

    /**
     * Konstruktor pre triedu StraznaVeza
     * @param maxPocetZamestnancovNaPolUvazok maximalny pocet zamestnancov, ktorych moze strazna veza zamestnat na polovicny uvazok
     * @param maxPocetUbytovanychLekarov maximalny pocet lekarov, ktorych moze strazna veza ubytovat
     * @param pocetAmbulancii pocet ambulancii, ktore sa nachadzaju v straznej vezi; jedna ambulancia = jeden lekar
     */
    public StraznaVeza(int maxPocetZamestnancovNaPolUvazok, int maxPocetUbytovanychLekarov, int pocetAmbulancii) {
        if (this.maxPocetZamestnancovNaPolUvazok < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapaornu maximalny pocet zamestnancov na polovicny uvazok");
        }
        
        if (this.maxPocetUbytovanychLekarov < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapaornu maximalny pocet ubytovanych lekarov");
        }
        
        if (this.pocetAmbulancii < 0) {
            throw new IllegalArgumentException("V konstruktore ste zadali zapaorny pocet ambluancii");
        }
        
        this.maxPocetZamestnancovNaPolUvazok = maxPocetZamestnancovNaPolUvazok;
        this.maxPocetUbytovanychLekarov = maxPocetUbytovanychLekarov;
        this.pocetAmbulancii = pocetAmbulancii;
        this.aktualnyPocetUbytovanychLekarov = 0;
        this.aktualnyPocetZamestnancovNaPolUvazok = 0;
        this.aktualnyPocetLekarov = 0;
        this.zamestnanci = new HashMap<String, IZamestnatelny>();
    }
    
    /**
     * Konstruktor pre triedu StraznaVeza, ktory nacita hodnoty zo suboru
     * @param nazovSuboru nazov suboru, z ktoreho nacita hodnoty
     * @throws IOException v pripade neexistujuceho suboru
     */
    public StraznaVeza(String nazovSuboru) throws IOException {
        File file = new File(nazovSuboru);
        Scanner scan = new Scanner(file);
        
        
        this.maxPocetZamestnancovNaPolUvazok = scan.nextInt();
        this.maxPocetUbytovanychLekarov = scan.nextInt();
        this.pocetAmbulancii = scan.nextInt();
        
        this.aktualnyPocetLekarov = 0;
        this.aktualnyPocetUbytovanychLekarov = 0;
        this.aktualnyPocetZamestnancovNaPolUvazok = 0;
        this.zamestnanci = new HashMap<String , IZamestnatelny>();
    }

    /**
     * Metoda, ktora predstavuje zamestnanie zamestnanca v straznej vezi a skontruluje ci je pre daneho zamestnanca miesto
     * @param zamestnanec je zamesntnac ktoreho chceme zamestnat
     * @throws ZamestnanieZamestnancaException v pripade ze nie je miesto pre lekara
     */
    public void zamestnajZamestnanca(IZamestnatelny zamestnanec) throws ZamestnanieZamestnancaException {
        if (zamestnanec == null) {
            throw new IllegalArgumentException("V metode zamestnaj zamestnanca ste zadali parameter s hodnotu null");
        }
        
        if (zamestnanec instanceof Lekar) {
            if (this.aktualnyPocetLekarov + 1 > this.pocetAmbulancii) {
                throw new ZamestnanieZamestnancaException("Vsetky ambulancie v straznej vezi su uz obsadene. Nie je miesto pre lekara.");
            }
        }    
        
        if (zamestnanec.getTypZamestnania().equals(TypZamestnania.POLOVICNYUVAZOK)) {
            if (this.aktualnyPocetZamestnancovNaPolUvazok + 1 > this.maxPocetZamestnancovNaPolUvazok) {
                throw new ZamestnanieZamestnancaException("V straznej vezi uz nie je volne miesto pre zamestnanca na polovicny uvazok");
            }
        }    
        
        if (zamestnanec instanceof Lekar) {
            if (((Lekar)zamestnanec).jeUbytovany()) {
                if (this.aktualnyPocetUbytovanychLekarov + 1 > this.maxPocetUbytovanychLekarov) {
                    throw new ZamestnanieZamestnancaException("Nie je miesto na ubytovanie lekara. Lekar musi byvat menej ako 100km od veznice.");
                }
            }
        }
        
        if (this.zamestnanci.containsKey(zamestnanec.getIdentifikacneCislo())) {
            throw new ZamestnanieZamestnancaException("Zamestnanec s tymto identifikacnym cislom uz v straznej vezi pracuje");
        }
        
        this.zamestnanci.put(zamestnanec.getIdentifikacneCislo(), zamestnanec);
        
        if (zamestnanec.getTypZamestnania().equals(TypZamestnania.POLOVICNYUVAZOK)) {
            this.aktualnyPocetZamestnancovNaPolUvazok += 1;
        }
        
        if (zamestnanec instanceof Lekar) {
            this.aktualnyPocetLekarov += 1;
        }
        
        if (zamestnanec instanceof Lekar) {
            if (((Lekar)zamestnanec).jeUbytovany()) {
                this.aktualnyPocetUbytovanychLekarov += 1;
            }
        }
    }
    
    /**
     * Metoda, ktora predstavuje prepustenia zamestnanca zo straznej vezi
     * @param identifikacneCislo identifikacne cislo zamestnanca, ktore chceme prepustit
     * @return zamestnanec ktoreho sme prepustili
     * @throws PrepustenieZamestnancaException v priprade ze zamestnanec s tymto cislom neexistuje 
     */ 
    public IZamestnatelny prepustiZamestnanca(String identifikacneCislo) throws PrepustenieZamestnancaException {
        if (identifikacneCislo == null) {
            throw new IllegalArgumentException("V metode prepusti zamestnanca ste zadali hodnotu s parametrom null");
        }
        
        IZamestnatelny prepustenyZamestnanec = this.zamestnanci.remove(identifikacneCislo);
        
        if (prepustenyZamestnanec == null) {
            throw new PrepustenieZamestnancaException("Zamestnanec s tymto identifikacnym cislom v straznej vezi nepracuje");
        }
        
        this.zamestnanci.remove(identifikacneCislo);
        
        if (prepustenyZamestnanec instanceof Lekar) {
            this.aktualnyPocetLekarov -= 1;
        }
        
        if (prepustenyZamestnanec instanceof Lekar) {
            if (((Lekar)prepustenyZamestnanec).jeUbytovany()) {
                this.aktualnyPocetUbytovanychLekarov -= 1;
            }
        }
        
        if (prepustenyZamestnanec.getTypZamestnania().equals(TypZamestnania.POLOVICNYUVAZOK)) {
            this.aktualnyPocetZamestnancovNaPolUvazok -= 1;
        }
        
        return prepustenyZamestnanec;
    }
    
    /**
     * Getter na zamestnanca, ktoreho si vyziadame zo straznev vezi a chceme s nim pracovat
     * @param identifikacneCislo je identifikacne cislo zamestnanca, ktoreho si chceme vyziadat a pracovat s nim
     * @return zamestnanec, ktoreho sme si vyziadali 
     */
    public IZamestnatelny getZamestnanca(String identifikacneCislo) {
        if (identifikacneCislo == null) {
            throw new IllegalArgumentException("V metode get zamestnanca ste zadali parameter s hodnotou null");
        }
        
        if (!(this.zamestnanci.containsKey(identifikacneCislo))) {
            throw new IllegalArgumentException("Strazna veza nema zamestnanca s tymto identifikacnym cislom");
        }
        
        IZamestnatelny zam = this.zamestnanci.get(identifikacneCislo);
        return zam;
    }
    
    /**
     * Getter na pocet lekarov, ktory su zamestnany v straznej vezi
     * @return pocet zamestnanych lekarov v straznej vezi
     */
    public int getPocetLekarov() {
        int pocetLekarov = 0;
        
        for (IZamestnatelny z : this.zamestnanci.values()) {
            if (z instanceof Lekar) {
                pocetLekarov += 1;
            }
        }
        
        return pocetLekarov;
    }
    
    /**
     * Getter na pocet straznik, ktory su zamestnany v straznej vezi
     * @return pocet zamestnanych straznikov v straznej vezi
     */
    public int getPocetStraznikov() {
        int pocetStraznikov = 0;
        
        for (IZamestnatelny z : this.zamestnanci.values()) {
            if (z instanceof Straznik) {
                pocetStraznikov += 1;
            }
        }
        
        return pocetStraznikov;
    }
    
    /**
     * Metoda, ktora poskytuje informacie o aktualnom stave straznej vezi v podobe textoveho retazca
     * @return textova reprezentacia aktualneho stavu straznev vezi 
     */
    @Override
    public String toString() {
        String vysl = "Strazna veza: pocet ambulancii = " + this.pocetAmbulancii + ", pocet volnych pracovnych miest na polovicny uvazok = " + (this.maxPocetZamestnancovNaPolUvazok - this.aktualnyPocetZamestnancovNaPolUvazok) + 
                " pocet volnych ubytovacich miest pre lekarov = " + (this.maxPocetUbytovanychLekarov - this.aktualnyPocetUbytovanychLekarov) 
                + ". Pocet lekarov v straznej vezi = " + this.getPocetLekarov() + 
                ", pocet straznikov = " + this.getPocetStraznikov() + ".\n";
        
        if (this.getPocetLekarov() > 0) {
            vysl += "\nLekari: \n";
            for (IZamestnatelny z : this.zamestnanci.values()) {
                if (z instanceof Lekar) {
                    vysl += z.toString() + "\n";
                }       
            }
        }
        
        if (this.getPocetStraznikov() > 0) {
            vysl += "\nStraznici: \n";
            for (IZamestnatelny z : this.zamestnanci.values()) {
                if (z instanceof Straznik) {
                    vysl += z.toString() + "\n";
                }       
            }
        }
        
        vysl = vysl + "\n*******************************************************";
        
        return vysl;
        
        
    }
    
    /**
     * Metoda, ktora ulozi do suboru identifikacne cisla lekarov, ktori su ubytovani
     * @param nazovSuboru je nazov suboru, do ktoreho chcem ulozit identifikacne cisla ubytovanych lekarov
     * @return uspesnost ulozenia identifikacnych cisel ubytovanych lekarov do suboru (true - uspech)
     */
    public boolean ulozDoSuboruIdenCislaUbytovanychLekarov(String nazovSuboru) {
        File subor = new File(nazovSuboru);
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(subor);
            writer.println("Identifikacne cisla ubytovanych lekarov:");
            
            for (IZamestnatelny z : this.zamestnanci.values()) {
                if (z instanceof Lekar) {
                    if (((Lekar)z).jeUbytovany()) {
                        writer.println(z.getIdentifikacneCislo());
                    }
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


