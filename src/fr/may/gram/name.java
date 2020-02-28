package fr.may.gram;

import fr.may.processus.FileHub;
import fr.may.main.speedy;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class name {

    private File origin = new FileHub().getnFile();

    public boolean addName(String name, String type) {
        speedy.writeInFile(new FileHub().getWordsFile(), type + " " + name, false);
        return speedy.writeInFile(origin, type + " " + name, false);
    }

    public String getCleanPlurial(String word){
        if(word.substring(word.length() - 2).equalsIgnoreCase("al")){
            word = word.substring(0,word.length() - 2) + "aux";
        } else {
            word = word + "s";
        }
        return word;
    }

    public List<String> getVoyelles() {
        return Arrays.asList("a", "e", "i", "o", "u", "y");
    }


}
