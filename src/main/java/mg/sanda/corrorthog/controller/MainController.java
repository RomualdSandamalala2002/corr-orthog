package mg.sanda.corrorthog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import mg.sanda.corrorthog.services.FileHandlerService;
import mg.sanda.corrorthog.services.WordHandlerService;
import mg.sanda.corrorthog.utils.Payload;
import mg.sanda.corrorthog.utils.PayloadRequestBody;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class MainController {

    @Autowired
    FileHandlerService fHService = new FileHandlerService();

    @PostMapping(value = "/")
    public ResponseEntity<Payload> postMethodName(@RequestBody PayloadRequestBody req) {
        ArrayList<String> listAllWords = new ArrayList<>();
        ArrayList<String> listToSuggestedWords = new ArrayList<>();
        Payload payload = new Payload();

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
                payload.setToBeSuggested(listToSuggestedWords);
                break;
            case "en":
                for (String word : listAllWords) {
                    if (!WordHandlerService.findWord(fHService.listWordsEN, word)) {
                        listToSuggestedWords.add(word);
                    }
                }
                payload.setToBeSuggested(listToSuggestedWords);
                break;
            default:
                break;
        }
        for (String word : listToSuggestedWords) {

            HashMap<String, ArrayList<String>> wordSug = new HashMap<>();
            ArrayList<String> suggestedWords = WordHandlerService.suggestionWord(fHService.listWordsFR, word);
            wordSug.put(word, suggestedWords);
            payload.addSuggestedWords(wordSug);
        }
        return ResponseEntity.ok(payload);
    }

}
