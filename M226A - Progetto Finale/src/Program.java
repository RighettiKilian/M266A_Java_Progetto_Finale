import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;
import static metodi.generali.Colori.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("SpellCheckingInspection")
public class Program {
    public static void main(String[] args) {
    // PARTE R/W che non sono riuscito ad implementare.
    // Preso da StackOverflow
    // Path pathAssolutaCorrente = Paths.get("").toAbsolutePath();
    // Il "puntatore" si trova originariamente in "out", con il punto lo sposto all'indietro e poi lo reindirizzo
    // Path pathCorrente = Paths.get("./src/oggetti/tipi.txt");

    Tipo Normale = new Tipo("Normale");
    Tipo Fuoco = new Tipo("Fuoco");
    Tipo Acqua = new Tipo("Acqua");
    Tipo Erba = new Tipo("Erba");
    Tipo Elettro = new Tipo("Elettro");
    Tipo Ghiaccio = new Tipo("Ghiaccio");
    Tipo Lotta = new Tipo("Lotta");
    Tipo Veleno = new Tipo("Veleno");
    Tipo Terra = new Tipo("Terra");
    Tipo Volante = new Tipo("Volante");
    Tipo Psico = new Tipo("Psico");
    Tipo Coleottero = new Tipo("Coleottero");
    Tipo Roccia = new Tipo("Roccia");
    Tipo Spettro = new Tipo("Spettro");
    Tipo Drago = new Tipo("Drago");
    Tipo Buio = new Tipo("Buio");
    Tipo Acciaio = new Tipo("Acciaio");
    Tipo Folletto = new Tipo("Folletto");

     Mossa m1 = new Mossa("Graffio", 40, 35, 100, Normale);
     // Mossa m2 = new Mossa("TEST",-10,-1,-10, Fuoco);
     Mossa m3 = new Mossa("Ruggito", 0, 40, 100, Normale);
     Mossa m4 = new Mossa("Frustata",45,25,100, Erba);
     Mossa m5 = new Mossa("Crescita",0,20,100, Normale);

     Natura schiva = new Natura("Schiva", new int[]{0,+2,0,-2,0,0});

     Box box1 = new Box("Box 1");

     // Pokemon p6 = new Pokemon("Squirtle", 14, new int[]{10,48,65,50,64,43}, 1,"Squira"); // Numero di Pokedex duplicato
     Pokemon p1 = new Pokemon("Bulbasaur",10, new int[]{45,49,49,65,65,45}, 1, "Bulba");
     Pokemon p2 = new Pokemon("Ivysaur",16, new int[]{60,62,63,80,80,60}, 2,"");
     Pokemon p3 = new Pokemon("Venusaur",34, new int[]{80,82,83,100,100,80}, 3,"Saur");
     Pokemon p4 = new Pokemon("Squirtle",1, new int[]{44,48,65,50,64,43}, 7,"AHHHHH");
     Pokemon p5 = new Pokemon("Squirtle",2, new int[]{44,48,65,50,64,43}, 7,"Squir");

     boolean spegnimento = false;
     final Scanner myObj = new Scanner(System.in);

        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ [ SEZIONE   HOME ] ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
     System.out.println("Benvenuto nel Pokedex! Inserisci come input 'Guida colori' all'interno della HOME per stampare la legenda dei colori");
    while (!spegnimento) {
        System.out.println("Cosa vuoi fare? [ '1' Aggiungere dati // '2' Gestire o Vedere dati // '3' Guida // 'Esc' Uscire ]");
        String input_iniziale = myObj.nextLine().trim(); // [!!!] CHATGPT (TRIM)

        if (input_iniziale.equalsIgnoreCase("esc")) { // Suggerito da IntelliJ, io avrei usato "toLowerCase().equals"
            System.out.println("Uscita dal programma.");
            spegnimento = true;
        } else {
            try {
                // Prova a interpretare l'input come numero
                int input_iniziale_Integer = Integer.parseInt(input_iniziale);
                switch (input_iniziale.toLowerCase()) { // toLowerCase() elimina il Camel Case
                    case "1":
                        stampaDivisoreCambioSezione("AGGIUNTA  DATI");
                        sezioneCreazione();
                        break;
                    case "2":
                        stampaDivisoreCambioSezione("MODIFICA  DATI");
                        sezioneGestione();
                        break;
                    case "3":
                        stampaDivisoreCambioSezione("    GUIDA     ");
                        stampaGuidaColori();
                        break;
                }
            } catch (NumberFormatException e) { // CHATGPT [!!!]
                System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata NON ACCETTATO [!!!]"+ANSI_RESET);
            }
        }
    }
    }

    private static void stampaGuidaColori() {
        System.out.println("══════════════════ [ GUIDA COLORI ] ══════════════════");
        System.out.println(" >> BIANCO: Richiesta di Input, ovvero i miei messaggi :]");
        System.out.println(ANSI_CYAN+" >> AZZURRO: Output"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+" >> GIALLO: Input non validi"+ANSI_RESET);
        System.out.println("══════════════════════════════════════════════════════");
        stampaDivisoreCambioSezione("SEZIONE   HOME"); // Torno alla sezione Home
    }

    private static void stampaDivisoreCambioSezione(String nome){
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ [ "+nome+" ] ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
    }

    boolean esciGestione = false;
    boolean esciGuida = false;

    final Scanner scannerGestione = new Scanner(System.in);

    public static void sezioneCreazione(){
        boolean esciCreazione = false;
        final Scanner scannerCreazione = new Scanner(System.in);
            System.out.println("Cosa vuoi creare? Queste sono le opzioni: [ 'Pokemon' // 'Abilita' // 'Mossa' // 'Natura' // 'Strumento' // 'Ind' Indietro ]");
            String input_iniziale = scannerCreazione.nextLine().trim(); // [!!!] CHATGPT (TRIM)

            if (input_iniziale.equalsIgnoreCase("Ind")) {
                System.out.println("Indietro.");
                stampaDivisoreCambioSezione("SEZIONE   HOME");
                return;
            } else {
                switch (input_iniziale.toLowerCase()) { // toLowerCase() elimina il Camel Case
                    case "pokemon":
                        int[] statistichePokemon = {0,0,0,0,0,0};
                        try {
                            System.out.println("Inserisci il nome del Pokemon:");
                            String nomePokemon = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci il soprannome del Pokemon: (Se non vuoi inserirlo scrivi 'null')");
                            String soprannomePokemon = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci il numero di Pokedex del Pokemon: (Dev'essere UNIVOCO per ogni specie)");
                            int numeroPokedex = scannerCreazione.nextInt();
                            scannerCreazione.nextLine();

                            for (int i = 0; i != statistichePokemon.length; i++){
                                if(i == 0){
                                    System.out.println("Inserisci l'attacco del Pokemon: (Valore accetato da 0 a 200)");
                                } else if(i == 1){
                                    System.out.println("Inserisci l'attacco speciale del Pokemon: (Valore accetato da 0 a 200)");
                                }  else if(i == 2){
                                    System.out.println("Inserisci la Difesa speciale del Pokemon: (Valore accetato da 0 a 200)");
                                }  else if(i == 3){
                                    System.out.println("Inserisci la Difesa speciale del Pokemon: (Valore accetato da 0 a 200)");
                                }  else if(i == 4){
                                    System.out.println("Inserisci i PS del Pokemon: (Valore accetato da 0 a 200)");
                                } else {
                                    System.out.println("Inserisci la Velocità del Pokemon: (Valore accetato da 0 a 200)");
                                }
                                statistichePokemon[i] += scannerCreazione.nextInt();
                                scannerCreazione.nextLine();
                            }

                            System.out.println("Inserisci il livello del Pokemon: (Valore accettato da 1 a 100)");
                            int livelloPokemon = scannerCreazione.nextInt();
                            scannerCreazione.nextLine();

                            Pokemon nuovoPokemon = new Pokemon(nomePokemon, livelloPokemon, statistichePokemon, numeroPokedex, soprannomePokemon);
                            System.out.println("Mossa creata: " + nuovoPokemon); // Non effettuo controlli per via dell'ID
                        } catch (InputMismatchException ie){
                            System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata del Pokemon NON ACCETTATO [!!!]"+ANSI_RESET);
                            scannerCreazione.nextLine(); // Evita input residui
                        }
                        break;
                    case "abilita":
                        try {
                            System.out.println("Inserisci il nome dell'abilità:");
                            String nomeAbilita = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci l'effetto dell'abilità:");
                            String effettoAbilita = scannerCreazione.nextLine().trim();

                            Abilita nuovaAbilita = new Abilita(nomeAbilita, effettoAbilita);
                            if(!nuovaAbilita.controllaNome()){ // Se l'abilità NON ha un nome già presente...
                                System.out.println("Abilità creata: " + nuovaAbilita);
                            }
                        } catch (InputMismatchException ie){
                            System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata dell'Abilità NON ACCETTATO [!!!]"+ANSI_RESET);
                            scannerCreazione.nextLine(); // Evita input residui
                        }
                        break;
                    case "mossa":
                        try {
                            System.out.println("Inserisci il nome della Mossa:");
                            String nomeMossa = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci la potenza della Mossa: (Valore accettato da 0 a 250)");
                            int potenzaMossa = scannerCreazione.nextInt();
                            scannerCreazione.nextLine();

                            System.out.println("Inserisci i PP (punti potenza) della Mossa: (Valore accettato da 5 a 40 non compressi)");
                            int PPMossa = scannerCreazione.nextInt();
                            scannerCreazione.nextLine();

                            System.out.println("Inserisci la precisione della Mossa: (Valore accettato da 0 a 100)");
                            int precisioneMossa = scannerCreazione.nextInt();
                            scannerCreazione.nextLine();

                            System.out.println("Inserisci il Tipo della Mossa:");
                            String tipoMossaString = scannerCreazione.nextLine().trim();
                            Tipo tipoMossa = new Tipo(tipoMossaString);


                            Mossa nuovaMossa = new Mossa(nomeMossa, potenzaMossa, PPMossa, precisioneMossa, tipoMossa);
                            if(!nuovaMossa.controllaNome()){ // Se la Mossa NON ha un nome già presente...
                                System.out.println("Mossa creata: " + nuovaMossa);
                            }
                        } catch (InputMismatchException ie) {
                            System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata della Mossa NON ACCETTATO [!!!]"+ANSI_RESET);
                            scannerCreazione.nextLine(); // Evita input residui
                        }
                        break;
                    case "natura":
                        try {
                            System.out.println("Inserisci il nome della Natura:");
                            String nomeNatura = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci la statistica da incrementare: (Attacco, Attacco speciale, Difesa, Difesa Speciale, PS, Velocita)");
                            int[] statisticheNatura = {0,0,0,0,0,0};
                            String statisticaDaAumentare = scannerCreazione.nextLine().trim();
                            switch (statisticaDaAumentare.toLowerCase()){
                                case "attacco":
                                    statisticheNatura[0] += 2;
                                    break;
                                case "attacco speciale":
                                    statisticheNatura[1] += 2;
                                    break;
                                case "difesa":
                                    statisticheNatura[2] += 2;
                                    break;
                                case "difesa speciale":
                                    statisticheNatura[3] += 2;
                                    break;
                                case "ps":
                                    statisticheNatura[4] += 2;
                                    break;
                                case "velocita":
                                    statisticheNatura[5] += 2;
                                    break;
                            }

                            System.out.println("Inserisci la statistica da DEcrementare: (Attacco, Attacco speciale, Difesa, Difesa Speciale, PS, Velocita)");
                            String statisticaDaDiminuire = scannerCreazione.nextLine().trim();
                            switch (statisticaDaDiminuire.toLowerCase()){
                                case "attacco":
                                    statisticheNatura[0] -= 2;
                                    break;
                                case "attacco speciale":
                                    statisticheNatura[1] -= 2;
                                    break;
                                case "difesa":
                                    statisticheNatura[2] -= 2;
                                    break;
                                case "difesa speciale":
                                    statisticheNatura[3] -= 2;
                                    break;
                                case "ps":
                                    statisticheNatura[4] -= 2;
                                    break;
                                case "velocita":
                                    statisticheNatura[5] -= 2;
                                    break;
                            }

                            Natura nuovaNatura = new Natura(nomeNatura, statisticheNatura);
                            if(!nuovaNatura.controllaNome()){ // Se la Mossa NON ha un nome già presente...
                                System.out.println("Natura creata: " + nuovaNatura);
                            }
                        } catch (InputMismatchException ie ){
                            System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata della Natura NON ACCETTATO [!!!]"+ANSI_RESET);
                            scannerCreazione.nextLine(); // Evita input residui
                        }
                        break;
                    case "strumento":
                        try {
                            System.out.println("Inserisci il nome dello Strumento:");
                            String nomeStrumento = scannerCreazione.nextLine().trim();

                            System.out.println("Inserisci la descrizione dello Strumento:");
                            String descrizioneStrumento = scannerCreazione.nextLine().trim();

                            Strumento nuovoStrumento = new Strumento(nomeStrumento, descrizioneStrumento);
                            if(!nuovoStrumento.controllaNome()){ // Se lo Strumento ha un nome già presente...
                                System.out.println("Strumento creato: " + nuovoStrumento);
                            }
                        } catch (InputMismatchException ie){
                            System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata dello Strumento NON ACCETTATO [!!!]"+ANSI_RESET);
                            scannerCreazione.nextLine(); // Evita input residui
                        }
                        break;
                }
            }
    }

    private static void sezioneGestione(){
        boolean esciGestione = false;
        final Scanner scannerGestione = new Scanner(System.in);
        while (!esciGestione) {
            System.out.println("Cosa vuoi fare? [ 'Modificare' // 'Mostrare a schermo' // 'Ind' Indietro ]");
            String input_iniziale = scannerGestione.nextLine().trim();

            if (input_iniziale.equalsIgnoreCase("Ind")) {
                System.out.println("Indietro.");
                stampaDivisoreCambioSezione("SEZIONE   HOME");
                return;
            } else{
                try {
                    switch(input_iniziale.toLowerCase()){
                        case "modificare":
                            // La mia idea iniziale era quella di usare i metodi .aggiungi per affiungere cose ai pokemon,
                            // singolarmente funzionano tutti ma non trovo il modo di implemntarli QUI, considerando che ogni
                            // singolo metodo necessita di un parametro che corrisponda al proprio TIPO(Nome della classe)
                            System.out.println("SEZIONE NON IMPLEMENTATA");
                            break;
                        case "mostrare a schermo":
                            System.out.println("Cosa vuoi mostrare? [ 'Pokemon' // 'Abilita' // 'Box' (Stampare tutti i Pokemon) // 'Mossa' // 'Natura' // 'Strumento' // 'Tipo' // 'Ind' Indietro ]");
                            String inputModifica = scannerGestione.nextLine().trim();

                            if (input_iniziale.equalsIgnoreCase("Ind")) {
                                System.out.println("Indietro.");
                                sezioneGestione();
                                return;
                            }
                            System.out.println("Inserisci il nome del/della '"+inputModifica+"' desiderato/a");
                            String nomeInputModifica2 = scannerGestione.nextLine();

                            switch (inputModifica.toLowerCase()){
                                case "pokemon":
                                    break;
                                case "abilita":
                                    Abilita abilitaTrovata = new Abilita();
                                    abilitaTrovata = abilitaTrovata.cercaAbilita(nomeInputModifica2);
                                    if (abilitaTrovata != null) {
                                        System.out.println("Abilità trovata: " + abilitaTrovata);
                                    } else {
                                        System.out.println(ANSI_YELLOW+"[!!!] Abilità NON esistente [!!!]"+ANSI_RESET);
                                    }
                                    break;
                                case "box":
                                    // NON FUNZIONANTE, getPokemons() funziona solo con il nome preciso del box e non ho idea di come sistemare
//                                    Box boxTrovato = new Box();
//                                    System.out.println("Pokemon contenuti nel Box '"+boxTrovato+"'");
//                                    System.out.println(boxTrovato.getPokemons());
                                    break;
                                case "mossa":
                                    // Non implementata
                                    break;
                                case "natura":
                                    System.out.println("SEZIONE NON IMPLEMNTATA");
                                    break;
                                case "strumento":
                                    Strumento strumentoTrovato = new Strumento();
                                    strumentoTrovato = strumentoTrovato.cercaStrumento(nomeInputModifica2);
                                    if (strumentoTrovato != null) {
                                        System.out.println("Strumento trovato: " + strumentoTrovato);
                                    } else {
                                        System.out.println(ANSI_YELLOW+"[!!!] Strumento NON esistente [!!!]"+ANSI_RESET);
                                    }
                                    break;
                                case "tipo":
                                    // Non implementabile a causa del fatto che altrimenti la classe Mossa andrebbe rifatta da 0
                                    // nella classe Pogram funziona ma non ho alternative
                                    System.out.println("SEZIONE NON IMPLEMNTATA");
                                    break;
                            }
                            break;
                    }
                } catch (NumberFormatException e) { // CHATGPT [!!!]
                    System.out.println(ANSI_YELLOW+"[!!!] Valore d'entrata NON ACCETTATO [!!!]"+ANSI_RESET);
                    scannerGestione.nextLine(); // Evita input residui
                }
            }
        }
    }
    // DOCUMENTAZIONI:
    // LEFT TO RIGHT NEL PLANT UML: https://plantuml.com/use-case-diagram#:~:text=actor2%3A%20%2D%2D%3E%20(Usecase2)%0A%40enduml-,Left%20to%20right%20direction,-The%20general%20default
    // new int[] come parametro: https://sentry.io/answers/java-initialize-array/?utm_source=chatgpt.com#initialize-an-array-using-known-element-values:~:text=Initialize%20an%20array%20using%20known%20element%20values
    // TESTI COLORATI: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    // JAVA.NIO directory corrente: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
    // HASHMAP: https://stackoverflow.com/questions/16819368/hashmap-in-java
}