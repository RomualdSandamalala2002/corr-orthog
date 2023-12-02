package mg.sanda.corrorthog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import mg.sanda.corrorthog.services.FileHandlerService;
import mg.sanda.corrorthog.services.LDService;
import mg.sanda.corrorthog.services.WordHandlerService;
import mg.sanda.corrorthog.utils.Payload;
import mg.sanda.corrorthog.utils.PayloadRequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MainController {

    @Autowired
    FileHandlerService fHService = new FileHandlerService();

    @GetMapping(value = "/")
    public ResponseEntity<Payload> getSuggestedWords() {
        Payload pl = new Payload(WordHandlerService.suggestionWord(fHService.listWordsFR, "jks"));
        return ResponseEntity.ok(pl);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ArrayList<Payload>> postMethodName(@RequestBody PayloadRequestBody req) {
        ArrayList<String> listAllWords = new ArrayList<>();
        ArrayList<String> listToSuggestedWords = new ArrayList<>();
        ArrayList<Payload> listSuggestion = new ArrayList<>();
        listAllWords.addAll(
                Arrays.asList(
                        req.getSentence().split("\s", 0)));
        switch (req.getLang()) {
            case "fr":
                for (String word : listAllWords) {
                    if (!WordHandlerService.findWord(fHService.listWordsFR, word)) {
                        listToSuggestedWords.add(word);
                    }
                }
                break;
            case "en":
                for (String word : listAllWords) {
                    if (!WordHandlerService.findWord(fHService.listWordsEN, word)) {
                        listToSuggestedWords.add(word);
                    }
                }
                break;
            default:
                break;
        }
        for (String word : listToSuggestedWords) {
            Payload payload = new Payload();
            HashMap<String, ArrayList<String>> wordSug = new HashMap<>();
            ArrayList<String> suggestedWords = WordHandlerService.suggestionWord(fHService.listWordsFR, word);
            payload.setListSuggestedWords(suggestedWords);
            wordSug.put(word, suggestedWords);
            payload.setSuggestedWords(wordSug);
            listSuggestion.add(payload);
        }
        return ResponseEntity.ok(listSuggestion);
    }

}
