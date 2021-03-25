
package vaznica.objektyvaznice;

/**
 *Trieda IZamestnatelny je interface implementovany v triede Zamestnanec
 * @author Dominik Vrbovsky
 */
public interface IZamestnatelny {
    TypZamestnania getTypZamestnania();
    String getIdentifikacneCislo();
    String toString();
    boolean equals(Object o);
    int hashCode();
}
