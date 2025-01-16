import metodi.generali.ControlloNomi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta una Natura di Pokemon, viene assegnata ad ogni Pokemon ed influisce sulla crescita delle sue statisctiche.
 * Ciò ogni volta che il pokemon sale di livello con il metodo 'aumentaLivello()'. <br>
 * ==> L'idea è che UNA statistica venga aumentata ed un'ALTRA venga diminuita, entrambe di un numero tra -5 e 5.
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Natura {
    private String nome;
    private int[] aumentoStatistiche;
    private Set<String> nomiNature = new HashSet<>();

    /**
     * Override del costruttore 'Natura(...)', non ha alcun parametro
     */
    public Natura(){};

    /**
     * Costruttore che instanzia una 'Natura' Pokemon. <br>
     * ==> Sono presenti molti controlli affinchè la classe sia creata correttamente, pertato vengono lanciate molte Eccezioni. <br>
     * <br>
     * @param nome Il nome della natura.
     * @param aumentoStatistiche Un Array di int[] che l'influenza l'andamento delle statistiche, PUÒ avere solo 6 elementi.
     */
    public Natura(String nome, int[] aumentoStatistiche) {
        try {
            this.nome = nome;
            if((nome.isEmpty())) {
                throw new IllegalArgumentException("Il nome della natura '"+this.nome+"' è vuoto, impossibile crearla");
            }
            if(nomiNature.contains(nome)){
                System.out.println(ANSI_YELLOW+"ERRORE: È già stata creato un Tipo di nome '"+ nome+"'"+ANSI_RESET);
            } else { nomiNature.add(nome); }
            if((aumentoStatistiche.length != 6)) {
                throw new IllegalArgumentException("L'Array della natura '"+this.nome+"' ha più o meno di 6 elementi, impossibile creare la natura");
            } else {
                this.aumentoStatistiche = aumentoStatistiche;
            }
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    /**
     * Metodo che controlla all'interno della lista 'nomiNature' se è già stata creata una classe 'Natura' con un nome impostato
     * come parametro. Serve a eseguire controlli sull'univocità. <br>
     * ==> Utilizza il metodo 'controlloGenerale' della classe 'controllaNomi'. <br>
     * <br>
     * @return Ritorna 'true' se il nome della 'Natura' è stato trovato all'interno della Lista, altrimenti 'false'
     */
    public boolean controllaNome(){
        if(ControlloNomi.controlloGenerale(nomiNature, nome)){ // == true è sottointeso
            return true;
        } else { return false; }
    }

    public String getNome() {
        return nome;
    }

    public int[] getAumentoStatistiche() {
        return aumentoStatistiche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Natura natura = (Natura) o;
        return Objects.equals(nome, natura.nome) && Objects.deepEquals(aumentoStatistiche, natura.aumentoStatistiche);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, Arrays.hashCode(aumentoStatistiche));
    }

    @Override
    public String toString() {
        return "Natura{" + "nome=" + nome + ", aumentoStatistiche=" + Arrays.toString(aumentoStatistiche) + '}';
    }
}