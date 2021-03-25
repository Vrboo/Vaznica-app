
package vaznica.objektyvaznice;

/**
 *  Trieda IUveznitelny je interface implementovany v triede Vezen
 * @author Dominik Vrbovsky
 */
public interface IUveznitelny {
    String getCisloVezna();
    double getZdravotnyStav();
    StupenNebezpecenstva getStupenNebezpecenstva();
    int hashCode();
    boolean equals(Object object);
    String toString();
    boolean znizStupenNebezpecenstvaZaDobreSpravanie();
}
