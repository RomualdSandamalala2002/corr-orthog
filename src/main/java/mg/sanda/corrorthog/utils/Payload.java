package mg.sanda.corrorthog.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Reponse du serveur lors de la requête
 */
public class Payload {
    private String response;
    
    @JsonProperty(value = "suggested-words")
    private HashMap<String,ArrayList<String>> suggestedWords;
    
    @JsonIgnore
    private ArrayList<String> listSuggestedWords;

    public Payload() {
    }

    public Payload(ArrayList<String> lsw){
        if(lsw.isEmpty()) {
            this.response = "none";
        }
        else {
            this.response = "ok";
        }
        this.listSuggestedWords = lsw;
    }

    public HashMap<String, ArrayList<String>> getSuggestedWords() {
        return suggestedWords;
    }

    public void setSuggestedWords(HashMap<String, ArrayList<String>> suggestedWords) {
        this.suggestedWords = suggestedWords;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<String> getListSuggestedWords() {
        return listSuggestedWords;
    }

    public void setListSuggestedWords(ArrayList<String> listSuggestedWords) {
        this.listSuggestedWords = listSuggestedWords;
    }
}
