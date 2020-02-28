package fr.may.gram;

import fr.may.processus.FileHub;
import fr.may.main.speedy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class determinant {
    private File origin = new FileHub().getDOriginFile();

    public determinant(){
        if(!speedy.testFileExist(origin)){
            List<String> d = Arrays.asList(
                    "m un\n","f une\n","p des\n","m le\n","f la\n","p les\n","m mon\n","m ton\n","f ta\n","m son\n","f sa\n","n nôtre\n","n notre\n",
                    "n votre\n", "n vôtre\n"," n leur\n","n leurs\n","m ce\n","m cet\n","f cette\n","p ces\n","p tes\n","p quelques\n"
            );
            for(String word : d){
                speedy.writeInFile(origin, word, false);
            }
        }
    }

    public boolean addDeterminant(String d, String type) {
        speedy.writeInFile(new FileHub().getWordsFile(), type + " " + d, false);
        return speedy.writeInFile(origin, type + " " + d, false);
    }

    public List<String> getDeGenre(String genre){
        List<String> d = new ArrayList<>();
        for (String str : speedy.getFileContent(origin)) {
            if (str.split(" ")[0].equalsIgnoreCase(genre)) {
                d.add(str.split(" ")[1]);
            }
        }
        return d;
    }
}
