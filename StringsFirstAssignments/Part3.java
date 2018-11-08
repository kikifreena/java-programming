
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences (String stringa, String stringb){
        int first = stringb.indexOf(stringa);
        if (first == -1) {return false;}
        int second = stringb.indexOf(stringa, first + stringa.length());
        if (second == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public String lastPart (String stringa, String stringb){
        int first = stringb.indexOf(stringa);
        if (first == -1){ return stringb;}
        else {
            return stringb.substring(first + stringa.length());
        }
    }
    
    public void testing (){
        String firstA = "by";
        String firstB = "A story by Abby Long";
        System.out.println("true == " + twoOccurences(firstA, firstB));
        String secondA = "a";
        String secondB = "banana";
        System.out.println("true == " + twoOccurences(secondA, secondB));
        String thirdA = "atg";
        String thirdB = "ctgtatgta";
        System.out.println("false == " + twoOccurences(thirdA, thirdB));
        
        String testA = lastPart("an", "banana");
        String testB = lastPart("zoo", "forest");
        System.out.println(testA + " " + testB);
    }
}
