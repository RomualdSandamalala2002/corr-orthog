package mg.sanda.corrorthog.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileHandlerService {
    
    private Resource rsFR = new ClassPathResource("gutenberg.txt"); /// Ressource à main/resources
    private Resource rsEN = new ClassPathResource("able.txt"); /// Ressource à main/resources
    public ArrayList<String> listWordsFR = new ArrayList<>();; /// Listes des mot dans le fichier gutemberg.txt
    public ArrayList<String> listWordsEN = new ArrayList<>();; /// Listes des mots dans le fichier able.txt

    public FileHandlerService(){
        try {
            File fileFR = rsFR.getFile();
            File fileEN = rsEN.getFile();
            Scanner readerFileFR = new Scanner(fileFR);
            Scanner readerFileEN = new Scanner(fileEN);
            while (readerFileFR.hasNextLine()) {
                String data = readerFileFR.nextLine();
                listWordsFR.add(data);
            }
            while (readerFileEN.hasNextLine()) {
                String data = readerFileEN.nextLine();
                listWordsEN.add(data);
            }
            readerFileFR.close();
            readerFileEN.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
