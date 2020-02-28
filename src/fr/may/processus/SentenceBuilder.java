package fr.may.processus;

import fr.may.gram.determinant;
import fr.may.gram.name;
import fr.may.main.speedy;

import java.io.File;
import java.util.List;
import java.util.Random;

public class SentenceBuilder {
    private File wordsFile = new FileHub().getWordsFile();
    private File aFile = new FileHub().getaFile();
    private File dFile = new FileHub().getDOriginFile();
    private File nFile = new FileHub().getnFile();
    private File pFile = new FileHub().getPOriginFile();
    private File vFile = new FileHub().getvFile();
    private determinant deter = new determinant();

    public String build(){
        if(wordsFile.exists() && dFile.exists() && aFile.exists() && nFile.exists() && pFile.exists() && vFile.exists()){
            return getVerbe() + " " + getGroupeNominal();
        } else {
            return "Il y a un problème dans les fichiers, vérifiez que tous les fichiers sont bien présents !";
        }
    }

    public String getVerbe(){
        Random r = new Random();
        List<String> verbs = speedy.getFileContent(vFile);
        String line = verbs.get(r.nextInt(verbs.size() -1));
        String verb = line.split(" ")[1];

        String test1 = verb.substring(0, verb.length() -3);
        String test2 = verb.substring(0, verb.length() -1);
        String test3 = verb.substring(0, verb.length() -2);
        if(isOir(verb)){
            verb = test1;
            switch (r.nextInt(6)){
                case 0:
                    verb = "je " + verb + "ois";
                    break;
                case 1:
                    verb = "tu " + verb + "ois";
                    break;
                case 2:
                    verb = "il " + verb + "oit";
                    break;
                case 3:
                    verb = "nous " + verb + "oyons";
                    break;
                case 4:
                    verb = "vous " + verb + "oyez";
                    break;
                case 5:
                    verb = "ils " + verb  + "oient";
                    break;
            }
        } else if(isAis(verb)){
            verb = test1;
            switch (r.nextInt(6)){
                case 0:
                    verb = "je " + verb + "ais";
                    break;
                case 1:
                    verb = "tu " + verb + "ais";
                    break;
                case 2:
                    verb = "il " + verb + "ait";
                    break;
                case 3:
                    verb = "nous " + verb + "ions";
                    break;
                case 4:
                    verb = "vous " + verb + "iez";
                    break;
                case 5:
                    verb = "ils " + verb  + "aient";
                    break;
            }
        } else if(is1stGr(verb)){
            verb = test2;
            switch (r.nextInt(6)){
                case 0:
                    verb = "je " + verb + "e";
                    break;
                case 1:
                    verb = "tu " + verb + "es";
                    break;
                case 2:
                    verb = "il " + verb + "e";
                    break;
                case 3:
                    verb = "nous " + verb + "ons";
                    break;
                case 4:
                    verb = "vous " + verb + "ez";
                    break;
                case 5:
                    verb = "ils " + verb  + "ent";
                    break;
            }
        } else if(is1stGr2(verb)){
            verb = test3;
            switch (r.nextInt(6)){
                case 0:
                    verb = "je " + verb + "e";
                    break;
                case 1:
                    verb = "tu " + verb + "es";
                    break;
                case 2:
                    verb = "il " + verb + "e";
                    break;
                case 3:
                    verb = "nous " + verb + "ons";
                    break;
                case 4:
                    verb = "vous " + verb + "ez";
                    break;
                case 5:
                    verb = "ils " + verb  + "ent";
                    break;
            }
        } else {
            verb = line;
        }
        return verb;
    }
    private boolean isOir(String verb){
        String term = speedy.getTerminaison(verb, 3);
        return term.equals("ois") || term.equals("oir") || term.equals("oit");
    }
    private boolean isAis(String verb){
        String term = speedy.getTerminaison(verb, 3);
        return term.equals("ais") || term.equals("ait");
    }
    private boolean is1stGr(String verb){
        String term = speedy.getTerminaison(verb, 1);
        return term.equals("e");
    }
    private boolean is1stGr2(String verb){
        String term = speedy.getTerminaison(verb, 2);
        return term.equals("es") || term.equals("ez");
    }

    public String getGroupeNominal(){
        String message = "Ah, oof.";
        List<String> noms = speedy.getFileContent(nFile);
        boolean voyelle = false;
        Random r = new Random();
        String nLine = noms.get(r.nextInt(noms.size() -1 ));
        String nom = nLine.split(" ")[1];
        String genre = nLine.split(" ")[0];

        for(char v : new name().getVoyelles()){
            if(nom.charAt(0) == v){
                voyelle = true;
            }
        }
        if(voyelle){
            switch(r.nextInt(2)){
                case 0:
                    message = "cet" + " " + nom;
                    break;
                case 1:
                    message = "l'" + nom;
                    break;
            }
        } else {
            List<String> ds;
            if(genre.equals("m")){
                ds = this.deter.getDeGenre("m");
                message = ds.get(r.nextInt(ds.size() - 1)) + " " + nom;
            } else if(genre.equals("f")){
                ds = this.deter.getDeGenre("f");
                message = ds.get(r.nextInt(ds.size() - 1)) + " " + nom;
            } else {
                ds = this.deter.getDeGenre("p");
                message = ds.get(r.nextInt(ds.size() - 1)) + " " + new name().getCleanPlurial(nom);
            }
        }
        return message;
    }

}
