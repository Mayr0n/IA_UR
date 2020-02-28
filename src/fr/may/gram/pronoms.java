package fr.may.gram;

import fr.may.processus.FileHub;
import fr.may.main.speedy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pronoms {

    private File origin = new FileHub().getPOriginFile();

    public pronoms(){
        if(!speedy.testFileExist(origin)){
            List<String> pr = Arrays.asList(
                    "p je\n", "p tu\n", "p il\n", "p elle\n", "p nous\n", "p vous\n", "p ils\n", "p elles\n",
                    "r que\n","r qui\n","r dont\n"
            );
            for(String word : pr){
                speedy.writeInFile(origin, word, false);
            }
        }
    }

    public List<String> getPersonnel() {
        List<String> pers = new ArrayList<>();
        for (String str : speedy.getFileContent(origin)) {
            if (str.split(" ")[0].equalsIgnoreCase("p")) {
                pers.add(str.split(" ")[1]);
            }
        }
        return pers;
    }

    public List<String> getRelatif() {
        List<String> rela = new ArrayList<>();
        for (String str : speedy.getFileContent(origin)) {
            if (str.split(" ")[0].equalsIgnoreCase("r")) {
                rela.add(str.split(" ")[1]);
            }
        }
        return rela;
    }

    public boolean addPronom(String pronom, String type) {
        speedy.writeInFile(new FileHub().getWordsFile(), type + " " + pronom, false);
        return speedy.writeInFile(origin, type + " " + pronom, false);
    }
}
