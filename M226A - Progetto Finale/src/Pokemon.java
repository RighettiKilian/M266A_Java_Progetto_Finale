import java.util.*;

import metodi.generali.GeneratoreID;
import static metodi.generali.Colori.*;
/*
 * Rappresenta unn Pokemon, è caratterizzato da attributi impostati nel costruttore ed altri facoltativi. <br>
 *  > 'Soprannome' può essere nullo. <br>
 *  > 'natura', 'abilita', 'strumento', 'tipo1' e 'tipo2' sono FACOLTATIVI, non asseganrli al Pokemon blocca alcune
 * funzioni (come l'aumento dei livelli). <br>
 *  > Ogni Pokemon è dotato di un 'identificatore' generato dalla classe 'GeneratoreID'.
 * <br>
 * [!!!] È consigliabile NON rimuovere i vari controlli della classe, in quanto impediscono diversi errori e garntiscono
 * che i dati siano sempre validi.
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
@SuppressWarnings("SpellCheckingInspection")
public class Pokemon {
    private final String nome;
    private String soprannome; // Può essere NULL
    private final int numeroPokedex;
    private int[] statistiche;
    private int livello;
    private final String identificatore;
    // Parametri da aggiungere tramite metodo
    private Abilita abilita;
    private Tipo tipo1;
    private Tipo tipo2;
    private Strumento strumento;
    private Natura natura;
    private final Mossa[] mosse;
    private Box box;
    private static final HashMap<Integer, String> registroPokedex = new HashMap<>(); // Registro dei numeri Pokedex e nomi associati
    private int mossePresenti;

    /**
     * Costruttore che permette di Instanziare un 'Pokemon'.
     * È presente un controllo dei Duplicati con HasMap, viene generato un IDENTIFICATORE grazie alla classe 'GeneratoreID' in maniera AUTOMATICA. <br>
     * ==> La NATURA incluenza in maniera positiva o negativa la crescita delle statistiche del Pokemon. <br>
     * ==> NESSUNO dei parametri può essere vuoto, in alcuni casi il codice solleva delle Eccezioni, in altri meno gravi
     * imposta un valore di Default con un messaggio d'avvertenza.
     *
     * @param nome Il nome del Pokemon
     * @param livello Il livello del Pokemon (Compreso tra 1 e 100)
     * @param statistiche Array di 6 posizioni che include le statistiche del Pokemon.
     * @param numeroPokedex Il numero di Pokedex, ê UNIVOCO della SPECIE DI POKEMON, ovvero 'Pokemon' che condividono lo stesso nome
     * @param soprannome Il soprannome del Pokemon, PUÒ essere nullo
     */
    public Pokemon(String nome, int livello, int[] statistiche, int numeroPokedex, String soprannome) {
        try {
            this.nome = nome;
            this.statistiche = new int[6];  // prima questo = new int[6] era sotto e non poteva copiare le statistiche
            if (nome == null) {
                throw new IllegalArgumentException("Nome del Pokemon vuoto, impossibile instanziarlo");
            }
            if (numeroPokedex <= 0) {
                throw new IllegalArgumentException("Numero di Pokedex di '"+nome+"' inferiore a 0, impossibile instanziarlo");
            }
            if ((livello < 0) || (livello > 100)) {
                throw new IllegalArgumentException("Livello del Pokemon '"+nome+"' non compreso tra 0 e 100, impossibile instanziarlo");
            }
            if (statistiche.length != 6) {
                throw new IllegalArgumentException("Le statitische del Pokemon '"+nome+"' sono più O meno di 6, impossibile instanziarlo");
            } else {
                for(int i = 0; i < statistiche.length; i++){
                    if((statistiche[i] <= 0) || (statistiche[i] > 200)){
                        statistiche[i] = 0;
                        System.out.println(ANSI_YELLOW+"ERRORE: La statistica del Pokemon '"+nome+"' alla posizione "+(++i)+" non compresa tra 1 e 200. Valore di Defaul impostato"+ANSI_RESET);
                    }
                }
            }

            // Controllo duplicati -----> [!!!] QUESTO l'ho preso da ChatGpt
            if (registroPokedex.containsKey(numeroPokedex)) {
                String nomeRegistrato = registroPokedex.get(numeroPokedex);
                if (!nomeRegistrato.equals(nome)) {
                    throw new IllegalArgumentException("Il Pokemon '"+nome+"' ha come numero di Pokédex "+numeroPokedex+", già assegnato al Pokemon: '"+nomeRegistrato+"'");
                }
            } else {
                registroPokedex.put(numeroPokedex, nome); // Aggiungi il numero Pokédex e il nome associato
            }

            this.livello = livello;
            this.numeroPokedex = numeroPokedex;
            this.soprannome = soprannome;
            if(natura != null){
                for (int j = 0; j != natura.getAumentoStatistiche().length; j++){ // Controllo per eliminare numeri troppo alti
                    if(natura.getAumentoStatistiche()[j] > 5 || natura.getAumentoStatistiche()[j] < -5){
                        System.out.println(ANSI_YELLOW+"La natura '"+natura+"' aumenta la statistica alla posizione '"+j+"' di un numero NON COMPRESO tra -5 e 5, valore di Default impostato"+ANSI_RESET);
                        natura.getAumentoStatistiche()[j] = 0;
                    }
                }
            }

            this.statistiche = statistiche;

            // Crea un identificatore unico usando GeneratoreID  ===> [QUESTO l'ho copiato con Chat]
            // Questo generatore è fondamentale perchè mi crea un valore UNIVOCO tra i Pokemon che mi permette d'eliminare
            // SOLO quello, ad esempio, già aggiunto a un Box. (con 'getNome()' me li avrebbe eliminati tutti)
            GeneratoreID generatore = new GeneratoreID();
            this.identificatore = generatore.creazioneIdentificatore();
            this.mosse = new Mossa[4]; // Evito i controlli sul NUMERO di elementi
        } catch (InputMismatchException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setSoprannome(String soprannome) {
        this.soprannome = soprannome;
    }

    public String getIdentificatore(){
        return identificatore;
    }

    public String getSoprannome() {
        return soprannome;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public int[] getStatistiche() {
        return statistiche;
    }

    public int getLivello() {
        return livello;
    }

    /**
     * Metodo che permette di aggiungere livelli al corrente livello del 'Pokemon'. <br>
     * [!!!] SE il livello del Pokemon (max. 100) ed il parametro 'aumento' superano il 100, il codice NON viene esgeuito. <br>
     * ==> Vengono memorizzate le vecchie statistiche (pre aumento) al fine di mostarle alla fine del Metodo. <br>
     *
     * @param aumento Quantitaivo di livelli da aggiungere all'attuale livello del Pokemon.
     * @return Restituisce dei messaggi di avvertenza quando il 'Pokemon' supererebbe il livello 100, altrimenti ritorna il Nuovo livello.
     */
    public String aumentaLivello(int aumento){ // Tecnicamente mancano IVs, EVs e la % che scende con i Livelli, ma è quasi impossibile aggiungere tutto QUI
        try{
            if(this.natura == null){
                throw new IllegalArgumentException("ERRORE: Il Pokemon '"+nome+"' non possiede una natura, impossibile aumentarne il livello");
            }
            if(this.livello + aumento > 100) {
                return ANSI_YELLOW+"Il Pokemon '"+nome+"' è di livello '"+livello+"' ed aggiungendo '"+aumento+"' livelli supererebbe il 100, inserire un altro numero"+ANSI_RESET;
            } else {
                Random rand = new Random();
                int VecchioLIvello = livello;
                int[] Vecchiestatistiche = Arrays.copyOf(statistiche, statistiche.length);

                for(int i = 0; i < aumento-livello; i++){ // Esegue l'aumento delle statistiche tante volte quanti livelli aumenta
                    for(int j = 0; j < statistiche.length; j++){
                        int rand_aumentoStatistiche = rand.nextInt(6);
                        statistiche[j] += rand_aumentoStatistiche + natura.getAumentoStatistiche()[j];
                    }
                }
                livello += aumento;
                return "Livello aumentato da '"+VecchioLIvello+"' a '"+livello+"'. Statistiche aumentate da: "+Arrays.toString(Vecchiestatistiche)+" a: "+Arrays.toString(statistiche);
            }
        } catch (IllegalArgumentException ie){
            throw new IllegalArgumentException(ie);
        }
    }

    // ALTRI METODI

    /**
     * Metodo che permette di assegnare due Tipi ad un 'Pokemon'. Non è obbligatorio che esso ne abbia due.
     * ==> I due tipi NON possono essere identici, per ovvi motivi. <br>
     *
     * @param tipoPrimario Il Tipo primario
     * @param tipoSecondario Il Tipo Secondario
     */
    public void aggiungiTipi(Tipo tipoPrimario, Tipo tipoSecondario){
        this.tipo1 = tipoPrimario;
        if((tipoPrimario == null) && (tipoSecondario == null)) {
            throw new IllegalArgumentException("ERRORE: Entrambi i tipi di aggiungiTipi() del Pokemon '"+nome+"' sono vuoti, impossibile aggiungerli.");
        }
        if(this.tipo1.equals(this.tipo2)){
            throw new IllegalArgumentException("ERRORE: I tipi (Primario e Secondario) del Pokemon '"+nome+"' NON possono essere uguali");
        }
        if(tipoSecondario == null){
            this.tipo2 = null;
            System.out.println(ANSI_CYAN+"Al Pokemon "+nome+" non è stato impostato un tipo secondario"+ANSI_RESET);
        } else{
            this.tipo2 = tipoSecondario;
        }
    }

    public String getTipi(){
        if((this.tipo1 == null) && (this.tipo2 == null)){
            return ANSI_YELLOW+"Il Pokemon '"+nome+"' non ha dei Tipi, per assegnarli usare 'aggiungiTipi()'"+ANSI_RESET;
        }

        if((this.tipo2 == null) && (this.tipo1 != null)) {
            return "Tipo Unico: "+ tipo1.getNome();
        }
        else {
            return "Tipo Primario: "+ tipo1.getNome() +", Tipo Secondario: "+ tipo2.getNome();
        }
        // Apparentamente "return tipo1.toString() + tipo2.toString();" non va perchè il valore può essere NULL
    }

    public void aggiungiAbilita(Abilita abilita){
        if(abilita == null){
            throw new IllegalArgumentException("ERRORE: L'Abilità assegnata al Pokemon '"+nome+"' è null, impossibile aggiungerla");
        } else {
            this.abilita = abilita;
        }
    }

    public String getAbilita() {
        if(this.abilita == null){
            return ANSI_YELLOW+"Il Pokemon "+nome+" non ha un'abilità, per assegnarla usare 'aggiungiAbilita()'"+ANSI_RESET;
        } else {
            return abilita.getNome();
        }
    }

    public void aggiungiStrumento(Strumento strumento){
        if(strumento == null){
            throw new IllegalArgumentException("ERRORE: Lo strumento assegnato al Pokemon '"+nome+"' è null, impossibile aggiungerlo");
        } else{
            this.strumento = strumento;
        }
    }

    public String getStrumento(){
        if(this.strumento == null){
            return ANSI_YELLOW+"Il Pokemon "+nome+" non ha uno strumento, per assegnarlo usare 'aggiungiStrumento()'"+ANSI_RESET;
        } else {
            return strumento.getNome();
        }
    }

    public void aggiungiNatura(Natura natura){
        if(natura == null){
            throw new IllegalArgumentException("ERRORE: La Natura assegnata al Pokemon '"+nome+"' è null, impossibile aggiungerla");
        } else{
            this.natura = natura;
        }
    }

    public String getNatura(){
        if(this.natura == null){
            return ANSI_YELLOW+"Il Pokemon "+nome+" non ha una natura, per assegnarla usare 'aggiungiNatura()'"+ANSI_RESET;
        } else {
            return natura.getNome();
        }
    }

    /**
     * Metodo che permette di aggiungere 4 mosse al Pokemon selezionato.
     * ==> L'Array 'Mosse' può contenere SOLO 4 elementi, ma un controllo ê eseguito lo stesso per ragioni di sicurezza. <br>
     *
     * @param mossa 'Mossa' da insegnare al Pokemon.
     */
    public void aggiungiMossa(Mossa mossa) {
        if (mossa == null) {
            throw new IllegalArgumentException("ERRORE: La Mossa assegnata al Pokemon '"+nome+"' è null, impossibile aggiungerla");
        } else {
            contaMosse();
            if (mossePresenti == 4) { // Controllo abbastanza inutile siccome l'array contiene solo 4 elementi, ma vabbè
                System.out.println(ANSI_YELLOW + "Il Pokemon "+nome+" ha già 4 mosse ("+getMosse()+",) impossibile aggiungerla'" + ANSI_RESET);
            }
            if(mossePresenti >= 1) { // Evito valori NULL (come la prima mossa aggiunta)
                if (controllaMosse(mossa)) {
                    System.out.println(ANSI_RED + "La mossa '"+mossa.getNome()+"' è già posseduta dal Pokemon '"+nome+"'"+ANSI_RESET);
                } else { // Se la seconda, terza o quarta mossa non è già stata inserita, la aggiungo
                    this.mosse[mossePresenti] = mossa;
                }
            } else {
                this.mosse[mossePresenti] = mossa;
            }
        }
    }

    public String getMosse(){
        if(contaMosse() == 0){    // Evita [null, null, null, null]
            return ANSI_YELLOW+"Il Pokemon "+nome+" non ha mosse assegnate, per assegnarle usare 'aggiungiMossa()'"+ANSI_RESET;
        } else {
            // Questa roba m'è stata suggerita da IntelliJ
            return Arrays.toString(mosse);
        }
    }

    private int contaMosse(){
        mossePresenti = 0;
        for (Mossa mossaInMosse : this.mosse) {
            if (!(mossaInMosse == null)) {
                mossePresenti++;
            }
        }
        return mossePresenti;
    }

    // [!!!] - Preso da ChatGpt, in parte
    private boolean controllaMosse(Mossa mossa){
        for(Mossa mossaInMosse : this.mosse){
            if(mossaInMosse != null && mossaInMosse.equals(mossa)){ // "mossaInMosse != null" evita che venga fatto il controllo sulle posizioni null dell'array
                return true;
            }
        }
        return false;
    }

    public void aggiungiBox(Box box){
        if(box == null){
            throw new IllegalArgumentException("ERRORE: ILl Box assegnato al Pokemon '"+nome+"' è null, impossibile aggiungerlo");
        } else {
            if(box.equals(this.box)){
                System.out.println(ANSI_YELLOW + "Il Pokemon "+nome+" si trova già in '"+getBox()+"'"+ANSI_RESET);
            } else {
                this.box = box;
            }
        }
    }

    public String getBox(){
        if(this.box == null){
            return ANSI_YELLOW+"Il Pokemon '"+nome+"' non è in un Box, per assegnarlo usare 'aggiungiBox()'"+ANSI_RESET;
        } else {
            return box.getNome();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(identificatore, pokemon.identificatore);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identificatore);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nome=" + nome + ", soprannome=" + soprannome + ", numeroPokedex=" + numeroPokedex +
                ", statistiche=" + Arrays.toString(statistiche) + ", livello=" + livello + '}';
    }
}