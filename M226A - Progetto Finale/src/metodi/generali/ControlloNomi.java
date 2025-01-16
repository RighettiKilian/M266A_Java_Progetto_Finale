package metodi.generali;

import java.util.List;
import java.util.Set;
/*
 * Questa classe viene utilizzata da molte classi per effettuare il controllo di univocità dei nomi. <br>
 * <br>
 * [!!!] Si consiglia di non alterare questa classe [!!!]
 *
 * @author Kilian Righetti
 * @version 16.01.2025
 */
public class ControlloNomi {
    public static boolean controlloGenerale(Set<String> set, String nome){ // Set<String> INCLUDE anche gli Hashset
        // Suggerimento di CHATGPT [^]
        if(set.contains(nome)){
            return false;
        } else {
            return true;
        }
    }
}


