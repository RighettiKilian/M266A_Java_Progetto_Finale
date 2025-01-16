import metodi.generali.ControlloNomi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static metodi.generali.Colori.ANSI_RESET;
import static metodi.generali.Colori.ANSI_YELLOW;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Rappresenta un mossa che può essere appresa da un Pokemon. <br>
 * Una mossa viene assegnata attraverso il metodo 'aggiungiMossa()', ogni Pokemon può possederne fino a 4. <br>
 * <br>
 * [!!!] Ogni Pokemon in questo programma può apprendere QUALSIASI MOSSA, i controlli sono troppo specifici e pertanto non sono presenti. [!!!]
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class Mossa {
    private final String nome;
    private int potenza;
    private int PP;
    private int precisione;
    private Tipo tipo; // Tipo va ereditato da classe Tipo
    private static Set<String> nomiMosse = new HashSet<>();

    // https://stackoverflow.com/questions/5429273/how-to-convert-int-to-integer
    Integer IPotenza = Integer.valueOf(potenza);
    Integer IPP = Integer.valueOf(PP);
    Integer IPrecisione = Integer.valueOf(precisione);

    /**
     * Costruttore che instanzia una 'Mossa' Pokemon. <br>
     * ==> Sono effetuatu diversi controlli affinchè i dati siano corretti, il codice NON viene mai interrotto con un'
     * Eccezione oer garantirne la fluidità. <br>
     * ==> OGNUNO dei parametri numeri NON può essere negativo e/o nullo. <br>
     * <br>
     * @param nome Il nome della Mossa
     * @param potenza Quando danno fa la mossa
     * @param PP Punti potenza della mossa, ovvero il numero di utilizzi della mossa
     * @param precisione La precisione della Mossa
     * @param tipo Il Tipo della Mossa
     */
    public Mossa(String nome, int potenza, int PP, int precisione, Tipo tipo) {
        try {
            this.nome = nome;
            this.tipo = tipo;
            if((nome.isEmpty()) || (IPotenza.equals("")) ||  (IPP.equals("")) || (IPrecisione.equals(""))) {
                throw new Exception("Uno dei PARAMETRI della mossa è vuoto, impossibile creare la mossa");
            }
            if(nomiMosse.contains(nome)){
                System.out.println(ANSI_YELLOW+"ERRORE: È già stata creata una Mossa con il nome '"+ nome+"'"+ANSI_RESET);
            } else { nomiMosse.add(nome); }
            if((potenza < 0) || (potenza > 250)) {
                System.out.println(ANSI_YELLOW+"La potenza della mossa "+this.nome+" dev'essere compresa tra 0 e 250, valore di Default impostato"+ANSI_RESET);
                this.potenza = 0;
            } else { this.potenza = potenza; }
            if((PP < 5) || (PP > 40)){
                System.out.println(ANSI_YELLOW+"I PP della mossa "+this.nome+" devono essere compresi tra 5 e 40, valore di Default impostato"+ANSI_RESET);
                this.PP = 5;
            } else { this.PP = PP;}
            if((precisione < 0) || (precisione > 100)){
                System.out.println(ANSI_YELLOW+"La precisione della mossa "+this.nome+" dev'essere compresa tra 0 e 100, valore di Default impostato"+ANSI_RESET);
                this.precisione = 0;
            } else { this.precisione = precisione; }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che controlla all'interno della lista 'nomiMosse' se è già stata creata una classe 'Mossa' con un nome impostato
     * come parametro. Serve a eseguire controlli sull'univocità. <br>
     * ==> Utilizza il metodo 'controlloGenerale' della classe 'controllaNomi'. <br>
     * <br>
     * @return Ritorna 'true' se il nome della 'Mossa' è stato trovato all'interno della Lista, altrimenti 'false'
     */
    public boolean controllaNome(){
        if(ControlloNomi.controlloGenerale(nomiMosse, nome)){ // == true è sottointeso
            return true;
        } else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void getTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getPrecisione() {
        return precisione;
    }

    public void setPrecisione(int precisione) {
        this.precisione = precisione;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mossa mossa = (Mossa) o;
        return potenza == mossa.potenza && PP == mossa.PP && precisione == mossa.precisione && Objects.equals(nome, mossa.nome) && Objects.equals(tipo, mossa.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, potenza, PP, precisione, tipo);
    }

    @Override
    public String toString() {
        return "Mossa{" + "nome=" + nome + ", potenza=" + potenza + ", PP=" + PP +
                ", precisione=" + precisione + "%, tipo=" + tipo.getNome() + '}';
    }
}