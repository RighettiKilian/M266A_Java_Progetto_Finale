import metodi.generali.ControlloNomi;

import java.util.*;

import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta un'abilità che può essere assegnata ad un Pokemon. <br>
 * Viene assegnata attraverso il metodo 'aggiungiAbilita()', ogni Pokemon può possederne solo una alla volta.
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Abilita {
    private String nome;
    private String effetto;
    private static Set<String> nomiAbilita = new HashSet<>(); // Per qualche motivo SERVE che sia statico altrimenti mi dà errore
    // [CHTGPT] - Invece di creare le varie abilità nel Pogram, le creo qui per metterle tutte in una Lista e implementare un metodo di ricerca
    private static List<Abilita> listaAbilita = Arrays.asList(
            new Abilita("Accelleratore", "La velocità del Pokemon aumenta ad ogni turno"),
            new Abilita("Prepotenza", "Abbassa l'attacco del nemico quando il Pokémon entra in battaglia."),
            new Abilita("Levitazione", "Il Pokémon è immune agli attacchi di tipo Terra."),
            new Abilita("Cartavetro", "Infligge danno al nemico ogni volta che lo colpisce fisicamente."),
            new Abilita("Assorbivolt", "Recupera energia ogni volta che il Pokémon viene colpito da un attacco di tipo Elettro."),
            new Abilita("Mantelneve", "Incrementa la difesa del Pokémon quando la neve è in corso."),
            new Abilita("Burla", "Il Pokémon ha sempre priorità nelle mosse."),
            new Abilita("Cambiacolore", "Il Pokémon assume il tipo dell'avversario per un turno."),
            new Abilita("Raccolta", "Raccoglie oggetti dopo ogni attacco che fallisce."),
            new Abilita("Sconforto", "Smezza la difesa quando il Pokémon ha meno della metà della salute.")
    );

    /**
     * Override del costruttore 'Abilita', non ha alcun parametro
     */
    public Abilita(){}

    /**
     * Costruttore che crea un'Abilità. <br>
     * ==> Entrambi i suoi parametri NON possono essere vuoti. <br>
     * ==> Ogni abilità viene creata all'interno della Lista 'listaAbilita', a ognuna di loro sono applicabili i metodi sottostanti.
     * <br>
     * @param nome Il nome dell'abilità
     * @param effetto L'effetto dell'abilità sul campo di battaglia
     */
    public Abilita(String nome, String effetto) {
        try {
            if((nome.isEmpty()) || (effetto.isEmpty())) {
                throw new IllegalArgumentException("ERRORE: Uno dei PARAMETRI dell'abilità è vuoto, impossibile creare lo strumento");
            }
            if(nomiAbilita.contains(nome)){
                System.out.println(ANSI_YELLOW+"ERRORE: È già stata creata un'abilità con il nome '"+ nome+"'"+ANSI_RESET);
            } else {
                this.nome = nome;
                this.effetto = effetto;
                nomiAbilita.add(nome);
            }
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    /**
     * Metodo che controlla all'interno della lista 'nomiABilità' se è già stata creata una classe con un nome impostato
     * come parametro. Serve a eseguire controlli sull'univocità. <br>
     * ==> Utilizza il metodo 'controlloGenerale' della classe 'controllaNomi'. <br>
     * <br>
     * @return Ritorna 'true' se il nome dell'Abilità è stato trovato all'interno della Lista, altrimenti 'false'
     */
    public boolean controllaNome(){
        if(ControlloNomi.controlloGenerale(nomiAbilita, nome)){ // == true è sottointeso
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo che permette di controllare direttamente se un'Abilità esista o meno.<br>
     * ==> Usato solo nella clsse 'Program' per stampare a schermo. <br>
     *
     * @param nomeAbilita Il nome dell'abilità che si vuole ricercare.
     * @return restituisce l'abilitâ (con relativi parametri) se esiste, altrimenti restituisce 'null'
     */
    public Abilita cercaAbilita(String nomeAbilita) {
        for (Abilita abilita : listaAbilita) {
            if (abilita.getNome().equalsIgnoreCase(nomeAbilita)) {
                return abilita;
            }
        }
        return null; // Se l'abilità non esiste
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEffetto(String effetto) {
        this.effetto = effetto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abilita abilita = (Abilita) o;
        return Objects.equals(nome, abilita.nome) && Objects.equals(effetto, abilita.effetto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, effetto);
    }

    @Override
    public String toString() {
        return "Abilita{" + "nome=" + nome + ", effetto=" + effetto + '}';
    }
}