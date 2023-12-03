package mg.sanda.corrorthog.services;

import java.util.ArrayList;

/**
 * Service pour gérer les mots dans le dictionnaire
 */
public class WordHandlerService {
    /**
     * Renvoi un vrai si le mot est trouvé
     * @param listWords
     * @param wordToFind
     * @return
     */
    public static boolean findWord(ArrayList<String> listWords, String wordToFind) {
        return listWords.indexOf(wordToFind) != -1;
    }

    /**
     * Donne les mots à suggérer
     * 
     * @param listWords Listes des mots à traiter
     * @param word      Mot traitement
     * @return list 
     */
    public static ArrayList<String> suggestionWord(ArrayList<String> listWords, String word) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0; /// compteur de phrase
        for (String wordToCompare : listWords) {
            int cost = LDService.calculateDistance(wordToCompare, word);
            if (cost <= (int) word.length() / 2 && cost <= 2) {
                list.add(wordToCompare);
                i++;
            }
            if(i>3) break;
        }
        return list;
    }
}
