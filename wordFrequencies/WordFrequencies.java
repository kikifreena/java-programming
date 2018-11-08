import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int  ind = 0;
        for (int k=0; k < myWords.size(); k++){
            int size = myFreqs.get(k);
            if (size > ind){
                ind = size;
            }
        }
        return ind;
    }
    
    public void test(){
        findUnique();
        for (int k=0; k < myWords.size(); k++){
            if (myFreqs.get(k) > 60){
            System.out.println(myFreqs.get(k) + "\t" +myWords.get(k));
        }}
        System.out.println("# unique words: " + myWords.size());
        System.out.println("Most common word's count: " + findIndexOfMax());
    }
}
