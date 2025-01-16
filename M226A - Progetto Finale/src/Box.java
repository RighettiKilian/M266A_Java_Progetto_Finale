import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta i Box nella quale possono venir inseriti i Pokemon. <br>
 * È possibile inserire un Pokemon nel box SIA con '<nomeBox>.aggiungiPokemon()' che con '<nomePokemon>.setBox()'. <br>
 * ==> Ogni Box può contenere al massimo 32 Pokemon. <br>
 * <br>
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Box {
    private String nome;
    private ArrayList<Pokemon> pokemons;

    /**
     * Override del costruttore 'Box(...)', non ha alcun parametro
     */
    public Box() {}

    /**
     * Costruttore che crea un oggetto 'Box'. <br>
     * Ad ogni Box creato viene automaticamente assegnato un Arraylist con la quale sarà possibile aggiungere degli oggetti
     * di tipo 'Pokemon' al Box. <br>
     *
     * @param nome Il nome del Box
     */
    public Box(String nome) {
        this.nome = nome;
        this.pokemons = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che permette, tramite l'arrayList 'Pokemons' di rimuovere un Pokemon da esso (AKA dal Box stesso). <br>
     * Se il Pokemon NON è presente, riporta un messaggio d'avvertenza, invece se è presente lo rimuove. <br>
     * ==> Grazie all'ID viene eliminato solo il Pokemon voluto e non tutti della stessa specie.
     *
     * @param p Il NOME del pokemon da rimuovere
     */
    public void rimuoviPokemon(Pokemon p) { // Devo scrivere p perchè pokemon è già nel FOREACH sotto
        for (Pokemon pokemon : pokemons){
            if(p.getIdentificatore().equals(pokemon.getIdentificatore())){
                this.pokemons.remove(p);
                System.out.println("Pokemon '"+p+"' rimosso con successo!");
                break;
            } else {
                System.out.println(ANSI_YELLOW+"Nel Box "+nome+" non è contenuto alcun Pokemon '"+p+"'"+ANSI_RESET);
            }
        }
    }

    /**
     * Metodo che permette, tramite l'ArrayList 'Pokemons' di aggiungere un Pokemon al Box. <br>
     * SE il Box contiene più di 32 Pokemon, il metodo resituisce un'avvertenza e blocca il codice.
     * ==> Il metodo esegue un Foreach all'interno dell'ArrayList 'Pokemons' e ne controlla gli identificatori; se il
     * 'Pokemon' è già presente nel Box, restituisce una stringa d'avvertenza. <br>
     * Se invece esso non ê presente, lo aggiinge con 'this.pokemons.add(p)'
     *
     * @param p Il nome del 'Pokemon' da aggiungere al Box
     */
    public void aggiungiPokemon(Pokemon p){
        if(pokemons.size() > 32) {
            System.out.println(ANSI_YELLOW+"Il Box "+nome+" non può contenere più di 32 POkemon "+ANSI_RESET);
        } else {
            boolean pokemonNonPresente = true;
            for (Pokemon pokemon : pokemons) {
                if(p.getIdentificatore().equals(pokemon.getIdentificatore())){
                    System.out.println(ANSI_YELLOW+"Pokemon "+pokemon.getNome()+" già presente in "+this.nome+ANSI_RESET);
                    pokemonNonPresente = false;
                    break;
                }
            }
            if(pokemonNonPresente){
                this.pokemons.add(p);
            }
        }
    }

    /**
     * Metodo che funge da .toString() avanzato, permette di stampare tutti i 'Pokemon' contenuti all'interno del Box. <br>
     * ==> Utilizza lo String Builder per unire tra loro i singoli 'Pokemon'. <br>
     *
     * @return Ritorna l'output dello String Builder trasformato in stringa.
     */
    // Funge da toString
    public String getPokemons() {
        StringBuilder sb = new StringBuilder();
        for(Pokemon pokemon : this.pokemons){
            sb.append(pokemon.getNome()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Box{" + "nome=" + nome + ", pokemon contenuti=" + getPokemons() + '}';
    }

    // QUESTO Equals mi permette di non effettuare alcun controllo su rimuoviPokemon
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(nome, box.nome) && Objects.equals(pokemons, box.pokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, pokemons);
    }
}