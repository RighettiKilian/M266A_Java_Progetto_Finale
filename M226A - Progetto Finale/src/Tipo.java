import metodi.generali.ControlloNomi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta uno dei 18 tipi presenti in Pokemon. <br>
 * Questa classe viene utilizzata dalla classa 'Pokemon' per assegnare i tipi con 'aggiungiTipi()'. <br>
 * ==> È anche un attributo della classe 'Mossa'.
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Tipo {
    private String nome;
    private static Set<String> nomiTipi = new HashSet<>();

    /**
     * Costruttore che instanzia un 'Tipo' Pokemon. Esso viene assegnato sia alle Mosse che ai Pokemon stessi, che possono
     * possederne al massimo due alla volta. <br>
     * ==> I Tipi sono CONNESSI alle Mosse, se non esistono loro, la mossa non puô esistere se instanziata con un 'Tipo'
     * non esistente. <br>
     *
     * @param nome Il nome del Tipo (non può essere vuoto)
     */
    public Tipo(String nome) {
        try {
            if(nome.isEmpty()) {
                throw new IllegalArgumentException(" ");
            } else if(nomiTipi.contains(nome)){
                System.out.println(ANSI_YELLOW+"ERRORE: È già stata creato un Tipo di nome '"+ nome+"'"+ANSI_RESET);
            } else {
                this.nome = nome;
                nomiTipi.add(nome);
            }
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    /**
     * Metodo che controlla all'interno della lista 'nomiTipi' se è già stata creata una classe 'Tipo' con lo stesso nome
     * come primo parametro. Serve a eseguire controlli sull'univocità. <br>
     * ==> Utilizza il metodo 'controlloGenerale' della classe 'controllaNomi'. <br>
     * <br>
     * @return Ritorna 'true' se il nome del Tipo è stato trovato all'interno della Lista, altrimenti 'false'
     */
    public boolean controllaNome(){
        if(ControlloNomi.controlloGenerale(nomiTipi, nome)){ // == true è sottointeso
            return true;
        } else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipo tipo = (Tipo) o;
        return Objects.equals(nome, tipo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "Tipo{" + "nome='" + nome + '}';
    }
}