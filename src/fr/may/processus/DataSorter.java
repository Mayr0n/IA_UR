package fr.may.processus;

import fr.may.gram.determinant;
import fr.may.gram.name;
import fr.may.gram.pronoms;
import fr.may.main.speedy;

import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class DataSorter {
    private boolean log = true;
    private File wordsFile = new FileHub().getWordsFile();
    private File advFile = new FileHub().getaFile();
    private File nameFile = new FileHub().getnFile();
    private File verbFile = new FileHub().getvFile();

    public DataSorter(boolean log){
        this.log = log;
    }

    public Hashtable<String, Boolean> addWords(String txt) {
        List<String> words = Arrays.asList(txt.split(" "));
        log(words.toString());
        Hashtable<String, Boolean> mots = new Hashtable<>();

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).toLowerCase();
            String wordBefore = "123456789";
            if (i > 0) {
                wordBefore = words.get(i - 1);
            }
            mots.put(word, test(word, wordBefore));
        }
        return mots;
    }

    private boolean test(String word, String wordBefore){
        if (isAlphanumeric(word) && hasVoyelle(word)) { //teste si constitué uniquement de lettres et possède au moins une voyelle
            log(word);
            if (!speedy.fileHas(wordsFile, word, true)) { //vérifie que le mot n'a pas déjà été analysé
                if (isAdverb(word) && !speedy.fileHas(advFile, word, false)) { //vérifie terminaison -ment et s'il n'est pas déjà enregistré
                    return speedy.writeInFile(advFile, word + "\n", false);
                } else if (isVerb(word, wordBefore) && !speedy.fileHas(verbFile, word, false)) { //vérifie multiples terminaisons et si déjà enregistré
                    return speedy.writeInFile(verbFile, wordBefore + " " + word + "\n", false);
                } else if (canChange(word) && !speedy.fileHas(nameFile, word, false)) { //vérifie si mot invariable via les mots pré-enregistrés
                    if(getGenre(wordBefore).equalsIgnoreCase("p")) { //si au pluriel, retire le s, s'il y en a un
                        word = withoutPlurial(word);
                    }
                    String register = getGenre(wordBefore) + " " + word + "\n";
                    if (!getGenre(wordBefore).equalsIgnoreCase("n")) {
                        return registerInFile(nameFile, register, false);
                    }
                    return registerInFile(wordsFile, register, false);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean registerInFile(File file, String contenu, boolean erase){
        if(!speedy.fileHas(file, contenu, false)){
            return speedy.writeInFile(file, contenu, erase);
        } else {
            return false;
        }
    }
    private boolean isAdverb(String word) {
        String t = speedy.getTerminaison(word, 4);
        log("-" + t);
        return t.equalsIgnoreCase("ment");
    }
    private boolean isVerb(String str, String mot) {
        boolean isVerb = false;
        String t2 = speedy.getTerminaison(str, 2);
        String t3 = speedy.getTerminaison(str, 3);
        log("-" + t2);
        log("-" + t3);
        List<String> canBe2 = Arrays.asList(
                "is", "ds", "as", "ez", "ai"
        );
        List<String> canBe3 = Arrays.asList(
                "ais", "ait", "end", "ent", "ons", "iez", "ions"
        );
        for (String w : canBe2) {
            if (t2.equalsIgnoreCase(w) && new pronoms().getPersonnel().contains(mot)) {
                isVerb = true;
            }
        }
        for (String w : canBe3) {
            if (t3.equalsIgnoreCase(w) && new pronoms().getPersonnel().contains(mot)) {
                isVerb = true;
            }
        }
        String r = "false";
        if(isVerb){
            r = "true";
        }
        log(r);
        return isVerb;
    }
    private boolean canChange(String word){
        List<Boolean> b = Arrays.asList(
            new pronoms().getPersonnel().contains(word), //vérifie si c'est un pronom personnel
            new pronoms().getRelatif().contains(word), //vérifie si c'est un pronom relatif
            new determinant().getDeGenre("f").contains(word), //vérifie si c'est un déterminant féminin
            new determinant().getDeGenre("m").contains(word), //vérifie si c'est un déterminant masculin
            new determinant().getDeGenre("p").contains(word) //vérifie si c'est un déterminant pluriel
        );
        log(b.toString()); //log
        return !b.contains(true); //si remplit une des conditions, alors invariable => retourne false
    }
    private String withoutPlurial(String word) {
        String t = speedy.getTerminaison(word, 1); //retourne la dernière lettre
        log("-" + t); //log
        if (t.equalsIgnoreCase("s")) {
            word = word.substring(0, word.length() - 1);
        }
        log(word); //log
        return word;
    }
    private String getGenre(String deter) {
        String genre;
        if (new determinant().getDeGenre("m").contains(deter)) { //teste si le déterminant devant appartient aux déterminants masculins pré-enregistrés
            genre = "m";
        } else if (new determinant().getDeGenre("f").contains(deter)) { //teste si le déterminant devant appartient aux déterminants féminins pré-enregistrés
            genre = "f";
        } else if (new determinant().getDeGenre("p").contains(deter)) { //teste si le déterminant devant appartient aux déterminants pluriels pré-enregistrés
            genre = "p";
        } else {
            genre = "n";
        }
        return genre;
    }
    private boolean hasVoyelle(String word) {
        boolean hasVoyelle = false;
        for(String s : new name().getVoyelles()){
            if(word.contains(s)){
                hasVoyelle = true;
            }
        }
        return hasVoyelle;
    }
    private boolean isAlphanumeric(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }
    private void log(String str){
        if(this.log) {
            System.out.println(str);
        }
    }
}
