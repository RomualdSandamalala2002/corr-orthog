package mg.sanda.corrorthog.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Reponse du serveur lors de la requête
 */
public class Payload {
    @JsonProperty(value = "to-be-suggested")
    private ArrayList<String> toBeSuggested = new ArrayList<>();

    public ArrayList<String> getToBeSuggested() {
        return toBeSuggested;
    }


    public void setToBeSuggested(ArrayList<String> toBeSuggested) {
        this.toBeSuggested = toBeSuggested;
    }

    @JsonProperty(value = "suggested-words")
    private ArrayList
        <HashMap
            <String,ArrayList
                <String>
            >
        > suggestedWords = new ArrayList<>();
    

    public ArrayList<HashMap<String, ArrayList<String>>> getSuggestedWords() {
        return suggestedWords;
    }


    public void setSuggestedWords(ArrayList<HashMap<String, ArrayList<String>>> suggestedWords) {
        this.suggestedWords = suggestedWords;
    }

    public void addSuggestedWords(HashMap<String, ArrayList<String>> swHashMap){
        this.suggestedWords.add(swHashMap);
    }

    public Payload() {
    }
    
}
