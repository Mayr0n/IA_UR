package fr.may.processus;

import fr.may.main.speedy;

import java.io.File;

public class FileHub {
    public FileHub(){
        speedy.testFolderExist(new File("data/sentenceBuilder/"));
        speedy.testFolderExist(new File("data/sentenceBuilder/originaux/"));
    }

    public File getWordsFile(){
        File file = new File("data/sentenceBuilder/words.txt");
        speedy.testFileExist(file);
        return file;
    }
    public File getaFile(){
        File file = new File("data/sentenceBuilder/adv.txt");
        speedy.testFileExist(file);
        return file;
    }
    public File getiFile(){
        File file = new File("data/sentenceBuilder/invariables.txt");
        speedy.testFileExist(file);
        return file;
    }
    public File getnFile(){
        File file = new File("data/sentenceBuilder/noms.txt");
        speedy.testFileExist(file);
        return file;
    }
    public File getvFile(){
        File file = new File("data/sentenceBuilder/verbes.txt");
        speedy.testFileExist(file);
        return file;
    }

    public File getDOriginFile(){
        File file = new File("data/sentenceBuilder/originaux/determinants.txt");
        return file;
    }
    public File getPOriginFile(){
        File file = new File("data/sentenceBuilder/originaux/pronoms.txt");
        return file;
    }
}
