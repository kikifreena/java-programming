import edu.duke.*;
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
    
    public String findGene (String dna, int firstIndex) {
        int startIndex = dna.toUpperCase().indexOf("ATG", firstIndex);
        if (startIndex == -1) {return "";};
        int taaIndex = findStopCodon(dna.toUpperCase(), startIndex, "TAA");
        int tagIndex = findStopCodon(dna.toUpperCase(), startIndex, "TAG");
        int tgaIndex = findStopCodon(dna.toUpperCase(), startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if (minIndex == -1 || minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene (){
        String testA = "TAACGTAACGCG";
        System.out.println(findGene(testA, 0) + " should be empty");
        String testB = "CATCATGCGTTAATAGCG";
        System.out.println(findGene(testB, 0) + " == ATGCGTTAA");
        String testC = "ATGCGTCACGCG";
        System.out.println(findGene(testC, 0) + " should be empty");
    }
    
    public void printAllGenes (String dna) {
        while (true) {
            String tryGene = findGene(dna, 0);
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
    public void getAllGenesOld (String dna) {
        while (true) {
            String tryGene = findGene(dna, 0);
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            System.out.println("Current:"  + currentGene);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public void testOn(){
        System.out.println("Testing!");
        StorageResource genes = getAllGenes("ATGATCTAATTTTATGCTGCAACGGTGAAGA");
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public double cgRatio (String dna){
        int cString = 0;
        int total = 0;
        for(int i=0; i < dna.length(); i++){
            char ch = dna.toLowerCase().charAt(i);
            if ((ch == 'g') || (ch == 'c')){
                total++;
            }
        }
        return total * 1.0 / dna.length();
    }
    
    public int countCTG (String dna) {
        int count = 0;
        int startIndex = dna.indexOf ("CTG");
        while (startIndex != -1) {
            startIndex = dna.indexOf("CTG", startIndex + 3);
            count++;
        }
        return count;
    }
    
    public void processGenes (StorageResource sr) {
        int total = 0;
        int longestGeneLength = 0;
        int cgCount = 0;
        int dnaTotal = 0;
        for (String item : sr.data()) {
            dnaTotal ++;
            int dnaLength = item.length();
            if (dnaLength > 60) {
                System.out.println("\n> 60: " + item);
                total++;
            }
            if (dnaLength > longestGeneLength) {
                longestGeneLength = dnaLength;
            }
            if (cgRatio(item) > 0.35) {
                cgCount++;
                System.out.println("\n> 0.35 " + item);
            }
         
        }
        System.out.println("Number > 0.35" + cgCount);
        System.out.println("Number > 60: " + total);
        System.out.println("Overall: " + dnaTotal);
        System.out.println("Longest: " + longestGeneLength);
        
    }
    
    public void testProcessGenes (){
        //StorageResource srA = getAllGenes("ATGATCTAATTTTATGCTGCAACGGTGAAGA");
        //processGenes(srA);
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        int ctg = countCTG (dna);
        System.out.println("Length: " + dna.length());
        StorageResource dnaSR = getAllGenes(dna);
        processGenes(dnaSR);
        
        System.out.println(ctg + " ctg");
    }
}
