import java.util.*;
import java.io.File;
import edu.duke.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fi = new FileResource(f);
        String fileName = f.getName();
        for (String w : fi.words()){
            if (map.keySet().contains(w)){
                ArrayList<String> arr = map.get(w);
                if (!arr.contains(fileName)){
                    arr.add(fileName);
                    map.put(w, arr);
                }
            }
            else {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(fileName);
                map.put(w, arr);
            }
        }
    }
    
    private void buildWordFileMap(){
        map = new HashMap<String,ArrayList<String>>();
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int best = 0;
        for (ArrayList<String> a : map.values()){
            int curr = a.size();
            if (curr > best){
                best = curr;
            }
        }
        return best;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for (String w : map.keySet()){
            int curr = map.get(w).size();
            if (curr == number){
                wordList.add(w);
            }
            
        }
        return wordList;
    }
    
    private void printFilesIn(String word){
        ArrayList<String> arr = map.get(word);
        for (String s : arr) {
            System.out.println(s + "\n"); 
        }
    }
    
    public void test(){
        buildWordFileMap();
        int n = maxNumber();
        System.out.println("Max: " + n);
        ArrayList<String> words = wordsInNumFiles(7);
        System.out.println(words.size());
        System.out.println(map.size());
        
        System.out.println(map.get("tree"));
        System.out.println(map.get("laid"));
        
        
        ArrayList<String> words2 = wordsInNumFiles(7);
        System.out.println(words2.size());
        for (String word : words){
            //printFilesIn(word);
            break;
        }
    }
}
