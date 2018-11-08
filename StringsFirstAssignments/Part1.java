
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        String result = "";
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1){
            return result;
        };
        int stopCodon = dna.indexOf("TAA",startCodon+3);
        if (stopCodon == -1){
            return result;
        };
        if ((stopCodon - startCodon)% 3 == 0){
            return dna.substring(startCodon, stopCodon);
        }
        else {
            return result;
        }
    };
    public void testSimpleGene(){
        String testA = "CAAGGCTAACG";
        System.out.println("Gene is " + testA);
        String resultA = findSimpleGene(testA);
        System.out.println("DNA strand is " + resultA);
        
        String testB = "AACGCGTATGCGAGGCTT";
        System.out.println("Gene is " + testB);
        String resultB = findSimpleGene(testB);
        System.out.println("DNA strand is " + resultB);
        
        String testC = "AGTATAGGACTGAACTTGTGGAT";
        System.out.println("Gene is " + testC);
        String resultC = findSimpleGene(testC);
        System.out.println("DNA strand is " + resultC);
        
        String testD = "CTATGTCGTTACGACCGTTCTAAGG";
        System.out.println("Gene is " + testD);
        String resultD = findSimpleGene(testD);
        System.out.println("DNA strand is " + resultD);
        
        String testE = "CAAATGCTCACCTTTAACTA";
        System.out.println("Gene is " + testE);
        String resultE = findSimpleGene(testE);
        System.out.println("DNA strand is " + resultE);
    }
}
