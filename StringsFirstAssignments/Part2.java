
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        String result = "";
        int start = dna.toLowerCase().indexOf(startCodon.toLowerCase());
        if (start == -1){
            return result;
        };
        int stop = dna.toLowerCase().indexOf(stopCodon.toLowerCase(),start+3);
        if (stop == -1){
            return result;
        };
        if ((stop - start)% 3 == 0){
            return dna.substring(start, stop);
        }
        else {
            return result;
        }
    };
    public void testSimpleGene(){
        String testA = "CAAGGCTAACG";
        System.out.println("Gene is " + testA);
        String resultA = findSimpleGene(testA, "ATG", "TAA");
        System.out.println("DNA strand is " + resultA);
        
        String testB = "AACGCGTATGCGAGGCTT";
        System.out.println("Gene is " + testB);
        String resultB = findSimpleGene(testB, "ATG", "TAA");
        System.out.println("DNA strand is " + resultB);
        
        String testC = "AGTATAGGACTGAACTTGTGGAT";
        System.out.println("Gene is " + testC);
        String resultC = findSimpleGene(testC, "ATG", "TAA");
        System.out.println("DNA strand is " + resultC);
        
        String testD = "CTATGTCGTTACGACCGTTCTAAGG";
        System.out.println("Gene is " + testD);
        String resultD = findSimpleGene(testD, "ATG", "TAA");
        System.out.println("DNA strand is " + resultD);
        
        String testE = "CAAATGCTCACCTTTAACTA";
        System.out.println("Gene is " + testE);
        String resultE = findSimpleGene(testE, "ATG", "TAA");
        System.out.println("DNA strand is " + resultE);
    }
}
