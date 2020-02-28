package fr.may.main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class speedy {
    public static boolean testFileExist(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            return true;
        }
    }
    public static void testFolderExist(File file){
        if(!file.exists()){
            file.mkdir();
        }
    }
    public static boolean fileHas(File file, String txt, boolean precise){
        boolean found = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = reader.lines().collect(Collectors.toList());
            reader.close();
            if(precise){
                for(String word : lines){
                    if(word.equalsIgnoreCase(txt)){
                        found = true;
                    }
                }
            } else {
                if(lines.contains(txt)){
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ET. MERDE.");
        }
        return found;
    }
    public static List<String> getFileContent(File file){
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while(line != null){
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException | NullPointerException e){
            e.printStackTrace();
        }
        return lines;
    }
    public static boolean writeInFile(File file, String txt, boolean erase){
        testFileExist(file);
        try {
            OutputStreamWriter osw;
            if (erase) {
                osw = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
            } else {
                osw = new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8);
            }
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(txt);
            bw.close();
            osw.close();
            return true;
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("ET. MERDE.");
            return false;
        }
    }
    public static String getTerminaison(String str, int i) {
        String t = "123456789";
        if (str.length() > i) {
            t = str.substring(str.length() - i);
        }
        return t;
    }

}
