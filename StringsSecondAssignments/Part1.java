
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startindex, String stopCodon) {
        int stopindex = dna.indexOf(stopCodon, startindex);
        while (stopindex != -1) {
            if( (stopindex - startindex) %3 == 0){
                return stopindex;
            }
            else {
                stopindex = dna.indexOf(stopCodon, stopindex + 1);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon () {
        String stringA = "AAGCTATGCTATAA";
        int testA = findStopCodon(stringA, 5, "TAA");
        System.out.println(testA + " == 11");
        String stringB = "CCGTATGATAGCGTAGCGT";
        int testB = findStopCodon(stringB, 4, "TAG");
        System.out.println(testB + " == 13");
        String stringC = "CAGTTCATAA";
        int testC = findStopCodon(stringC, 1, "TAT");
        System.out.println(testC + " == 10");
    }
    
    public String findGene (String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {return "";};
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int smallestIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (smallestIndex == dna.length()){
            return "";
        }
        else {
            return dna.substring(startIndex, smallestIndex+3);
        }
    }
    
    public void testFindGene (){
        String testA = "TAACGTAACGCG";
        System.out.println(findGene(testA) + " should be empty");
        String testB = "CATCATGCGTTAATAGCG";
        System.out.println(findGene(testB) + " == ATGCGTTAA");
        String testC = "ATGCGTCACGCG";
        System.out.println(findGene(testC) + " should be empty");
    }
    
    public void printAllGenes (String dna) {
        while (true) {
            String tryGene = findGene(dna);
            int firstATG = dna.indexOf("ATG");
            if (tryGene != "") {
                System.out.println(tryGene);
                dna = dna.substring(firstATG + tryGene.length() + 1, dna.length());
            }
            else if (firstATG != -1) { // still more ATGs
                dna = dna.substring(firstATG + 1, dna.length());
            }
            else {
                break;
            }
        }
    }
}
