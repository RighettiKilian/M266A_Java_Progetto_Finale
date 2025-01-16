package metodi.generali;

import java.util.Random;

@SuppressWarnings("SpellCheckingInspection")
/*
 * Questa classe viene utilizzata dalle classi 'Pokemon' e 'Box' per dare un attributo UNIVOCO ad ogni Pokemon. <br>
 * ==> L'attributo univoco (chiamato Identificatore) permette l'eliminazione di un Pokemon da un Box, ad esempio. <br>
 * <br>
 * [!!!] Si consiglia di non alterare questa classe [!!!]
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class GeneratoreID {
    Random rand = new Random();
    int rand_upper_lower;
    int rand_numeri ;

    // COSTANTI: https://stackoverflow.com/questions/66066/what-is-the-best-way-to-implement-constants-in-java
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERI = "0123456789";
    private String identificatore = "";

    public String creazioneIdentificatore(){
        for(int i = 0; i < 30; i++){ // Il '30' è il numero di caratteri della Password
            switch(rand.nextInt(3)) {
                case 0:
                    rand_upper_lower = rand.nextInt(26);
                    identificatore += UPPER.charAt(rand_upper_lower);
                    break;
                case 1:
                    rand_upper_lower = rand.nextInt(26);
                    identificatore += LOWER.charAt(rand_upper_lower);
                    break;
                case 2:
                    rand_numeri = rand.nextInt(10);
                    identificatore += NUMERI.charAt(rand_numeri);
                    break;
            }
        }
        return identificatore;
        // La probabilità che vengno creati due iD identici è pari a 62^30, quindi non implemento un controllo
    }
}