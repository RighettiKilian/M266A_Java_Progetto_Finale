import metodi.generali.ControlloNomi;

import java.util.*;

import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta un Oggetto che può essere assegnata ad un Pokemon. <br>
 * Viene assegnata attraverso il metodo 'aggiungiStrumento', ogni Pokemon può possederne solo una alla volta.
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Strumento {
    private String nome;
    private String descrizione;
    private static Set<String> nomiStrumenti = new HashSet<>(); // Dev'essere static altrimenti mi dà errore
    static List<Strumento> listaStrumenti = Arrays.asList(
            new Strumento("Avanzi", "Oggetto che fa recuperare un po' di PS a ogni turno"),
            new Strumento("Bendascelta", "Oggetto che aumenta l'Attacco, ma consente di usare solo una mossa"),
            new Strumento("Assorbisfera", "Aumenta la potenza delle mosse, ma consuma PS a ogni utilizzo"),
            new Strumento("Focus Band", "Permette al Pokémon di resistere a un colpo che lo manderebbe KO"),
            new Strumento("Pietralieve", "Rende più leggeri i Pokémon, influenzando la loro velocità"),
            new Strumento("Magmatore", "Potenzia le mosse di tipo Fuoco"),
            new Strumento("Acqua Magica", "Potenzia le mosse di tipo Acqua"),
            new Strumento("Velenago", "Potenzia le mosse di tipo Veleno"),
            new Strumento("Terreorpanno", "Potenzia le mosse di tipo Spettro"),
            new Strumento("Mirino", "Aumenta la precisione delle mosse del Pokémon"),
            new Strumento("Rapidartigli", "Consente di agire più velocemente in battaglia"),
            new Strumento("Perlacroce", "Oggetto che potenzia le mosse di tipo Drago"),
            new Strumento("Bitorzolelmo", "Infligge danni al contatto fisico con il Pokémon"),
            new Strumento("Cintura Nera", "Aumenta leggermente la potenza delle mosse fisiche"),
            new Strumento("Saviocchiali", "Aumenta leggermente la potenza delle mosse speciali")
    );

    /**
     * Override del costruttore 'Strumento(...)', non ha alcun parametro
     */
    public Strumento(){};

    /**
     * Csotruttore che instanzia uno 'Strumento' da assegnare a un 'Pokemon'. <br>
     * NOTA: Ognu 'Pokemon' può possedere UN solo strumento alla volta.
     * ==> ENTRAMBI gli attributi NON possono essere vuoti, in caso viene sollevata un'eccezione.
     *
     * @param nome Il nome dello Strumento
     * @param descrizione La descrizione dello Strumento
     */
    public Strumento(String nome, String descrizione) {
        try {
            if((nome.isEmpty()) || (descrizione.isEmpty())) {
                throw new IllegalArgumentException("ERRORE: Uno dei PARAMETRI dello strumento è vuoto, impossibile creare lo strumento");
            }
            if(nomiStrumenti.contains(nome)){
                System.out.println(ANSI_YELLOW+"ERRORE: È già stato creata un Oggetto con il nome '"+ nome+"'"+ANSI_RESET);
            } else {
                this.nome = nome;
                this.descrizione = descrizione;
                nomiStrumenti.add(nome);
            }
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    /**
     * Metodo che permette di controllare direttamente se un oggetto esista o meno.<br>
     * ==> Usato solo nella clsse 'Program' per stampare a schermo. <br>
     *
     * @param nomeStrumento Il nome dell'abilità che si vuole ricercare.
     * @return restituisce lo Strumento (con relativi parametri) se esiste, altrimenti restituisce 'null'
     */
    public Strumento cercaStrumento(String nomeStrumento) {
        for (Strumento strumento : listaStrumenti) {
            if (strumento.getNome().equalsIgnoreCase(nomeStrumento)) {
                return strumento;
            }
        }
        return null; // Se l'abilità non esiste
    }

    /**
     * Metodo che controlla all'interno della lista 'nomiStrumenti' se è già stata creata una classe 'Strumento' con lo stesso nome
     * come primo parametro. Serve a eseguire controlli sull'univocità. <br>
     * ==> Utilizza il metodo 'controlloGenerale' della classe 'controllaNomi'. <br>
     * <br>
     * @return Ritorna 'true' se il nome dello Strumento è stato trovato all'interno della Lista, altrimenti 'false'
     */
    public boolean controllaNome(){
        if(ControlloNomi.controlloGenerale(nomiStrumenti, nome)){ // == true è sottointeso
            return true;
        } else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strumento strumento = (Strumento) o;
        return Objects.equals(nome, strumento.nome) && Objects.equals(descrizione, strumento.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descrizione);
    }

    @Override
    public String toString() {
        return "Strumento{" + "nome=" + nome +", descrizione=" + descrizione + '}';
    }
}
