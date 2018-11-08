import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public static int[] countWordLengths (FileResource resource, int[] counts){
        for (String s : resource.words()){
            String newS = s;
            int len = newS.length();
            if ((Character.isLetter(s.charAt(0)) == false) || (Character.isLetter(s.charAt(len-1)) == false)){
                len--;
            }
            if (len > counts.length){
                len = counts.length;
            }
            counts[len] ++;
        }
        return counts;
    }
    
    public static void testCountWordLengths(){
        FileResource resource = new FileResource();
        int c[] = new int[31];
        int r[] = countWordLengths(resource, c);
        for (int i = 0; i<r.length; i++){
            System.out.println("Length"+ i+":" + r[i]);
        }
    }
}
