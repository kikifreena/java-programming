import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+= totalSlices){
            char c = message.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++){
            String currslice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker();
            int dkey = cc.getKey(currslice);
            key[i] = dkey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String word : fr.lines()){
            word = word.toLowerCase();
            hs.add(word);
        }
        return hs;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] splitMessage = message.split("\\W+");
        int count = 0;
        for (int i = 0; i < splitMessage.length; i++){
            String curr = splitMessage[i];
            if (dictionary.contains(curr)){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String bestString = "";
        char common = mostCommonCharIn(dictionary);
        for (int i = 1; i < 100; i++){
            int[] arr = tryKeyLength(encrypted, i, common);
            VigenereCipher vc = new VigenereCipher(arr);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (max < count){
                max = count;
                bestString = i + ": " + decrypted;
            }
        }
        //System.out.println("Count: " + max);
        return bestString;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
        for (String word : dictionary){
            for (int i = 0; i < word.length(); i++){
                char currentChar = word.charAt(i);
                if (charCounts.containsKey(currentChar)){
                    charCounts.put(currentChar, charCounts.get(currentChar) + 1);
                }
                else {
                    charCounts.put(currentChar, 1);
                }
            }
        }
        char best = ' ';
        int max = 0;
        for (char c : charCounts.keySet()){
            int count = charCounts.get(c);
            if (count > max){
                best = c;
                max = count;
            }
        }
        return best;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String best = "";
        String bestLang = "";
        int max = 0;
        for (String languageName : languages.keySet()){
            HashSet<String> dictionary = languages.get(languageName);
            String currDecrypt = breakForLanguage(encrypted, dictionary);
            int total = countWords(currDecrypt, dictionary);
            if (max < total){
                max = total;
                best = currDecrypt;
                bestLang = languageName;
            }
        }
        System.out.println(best+ " Language used: "+ bestLang);
        return best;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encryptedText = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        DirectoryResource dictFiles = new DirectoryResource();
        for (File f : dictFiles.selectedFiles()){
            FileResource dictFile = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dictFile);
            dictionaries.put(f.getName(), dictionary);
        }

        String vcd = breakForAllLangs(encryptedText, dictionaries);
        System.out.println(vcd.substring(0, 200));
    }

}
