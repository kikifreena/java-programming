
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch){
        String vowels = "aeiou";
        for (int i = 0; i< vowels.length(); i++){
            if (vowels.charAt(i) == Character.toLowerCase(ch)){
                return true;
            }
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch){
        String replaced = "";
        for (int i = 0; i < phrase.length(); i++){
            char current = phrase.charAt(i);
            if (isVowel(current)){
                replaced += ch;
            }
            else {
                replaced += current;
            }
        }
        return replaced;
    }
    
    public String emphasize (String phrase, char ch){
        ch = Character.toLowerCase(ch);
        String replaced = "";
        for (int i = 0; i < phrase.length(); i++){
            char current = phrase.charAt(i);
            char lcurrent = Character.toLowerCase(current);
            if ((i%2 == 0) && (lcurrent == ch) ){
                replaced += '*';
            }
            else if ((i%2 == 1) && (lcurrent == ch) ){
                replaced += '+';
            }
            else {
                replaced += current;
            }
        }
        return replaced;
    }
    
    public void test(){
        //System.out.println(isVowel('A'));
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
