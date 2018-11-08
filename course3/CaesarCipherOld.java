import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOld {
    public static String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String sAlphabetUC = alphabetUC.substring(key) + alphabetUC.substring(0, key);
        String alphabetLC = "abcdefghijklmnopqrstuvwxyz";
        String sAlphabetLC = alphabetLC.substring(key) + alphabetLC.substring(0, key);
        for (int i=0; i< encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idxUC = alphabetUC.indexOf(currChar);
            int idxLC = alphabetLC.indexOf(currChar);
            if (idxUC != -1){
                char newChar = sAlphabetUC.charAt(idxUC);
                encrypted.setCharAt(i, newChar);
            }
            else if (idxLC != -1){
                char newChar = sAlphabetLC.charAt(idxLC);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        String encrypted = "";
        for (int i = 0; i < input.length(); i++){
            String curr = input.substring(i, i+1);
            char currChar = input.charAt(i);
            if (i%2 == 0){
                String newChar = encrypt(curr, key1);
                encrypted += newChar;
            }
            else if (i%2 == 1){
                String newChar = encrypt(curr, key2);
                encrypted += newChar;
            }
        }
        return encrypted;
    }

    public static void testCaesar() {
        //int key = 17;
        //FileResource fr = new FileResource();
        String message = "Duke Computer Science Department Overview";
        //String encrypted = encrypt(message, 15);
        //System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(encryptTwoKeys(message, 17, 4));
    }
}
