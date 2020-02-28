package fr.may.gram;

import fr.may.processus.FileHub;
import fr.may.main.speedy;

import java.util.List;

public class invariables {

    public List<String> getInvariables() {
        return speedy.getFileContent(new FileHub().getiFile());
    }

    public boolean addInvariable(String inv){
        speedy.writeInFile(new FileHub().getWordsFile(), inv, false);
        return speedy.writeInFile(new FileHub().getiFile(), inv, false);
    }


}
