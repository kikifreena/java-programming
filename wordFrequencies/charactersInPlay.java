import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * Write a description of characterNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class charactersInPlay {
    private ArrayList<String> myChars;
    private ArrayList<Integer> myFreqs;

    public charactersInPlay(){
        myChars = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findAllCharacters(){
        FileResource resource = new FileResource();
        for(String s : resource.lines()){
            int period = s.indexOf(".");
            if (period != -1){
                s = s.substring(0, period);
                update(s);
            }
        }
    }

    public void update(String person){
        int index = myChars.indexOf(person);
        if (index == -1){
            myChars.add(person);
            myFreqs.add(1);
        }
        else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value+1);
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int k=0; k < myChars.size(); k++){
            if ((myFreqs.get(k)  >= num1) && (myFreqs.get(k) <= num2)){
                System.out.println(myFreqs.get(k) + "\t" +myChars.get(k));
            }
        }
    } // 4932; the; 692; ; ; AUDREY

    public void test(){
        findAllCharacters();
        System.out.println("# unique lines: " + myChars.size());
        charactersWithNumParts(80, 1000000000);
        //System.out.println("Most common word's count: " + findIndexOfMax());
    }
}
