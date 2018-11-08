import java.util.HashMap;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount() {
        map = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        map = new HashMap<String,Integer>();
        for (int i = start; i < (dna.length() - 3); i+=3){
            String current = dna.substring(i, i+3);
            if (map.keySet().contains(current)){
                map.put(current, map.get(current) + 1);
            }
            else {
                map.put(current, 1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        int max = -1;
        String codon = "Not found";
        for (String k : map.keySet()){
            if (map.get(k) > max){
                max = map.get(k);
                codon = k;
            }
        }
        return codon;
    }
    
    private void printCodonCounts(int start, int end){
        for (String k : map.keySet()){
            int count = map.get(k);
            if (count > start && count < end){
                System.out.println(k + " " + count);
            }
        }
    }
    
    private int printMapSize(){
        return map.size();
    }
    
    public void test(){
        HashMap<String, Integer> map = new HashMap<String,Integer>();
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        buildCodonMap(0, dna);
        
        System.out.println("most common codon: " + getMostCommonCodon());
        System.out.println("Size:"+ printMapSize() + "\n");
        printCodonCounts(1, 500);
        
        buildCodonMap(1, dna);
        System.out.println("most common codon: " + getMostCommonCodon());
        printCodonCounts(1, 500);
        System.out.println("Size:"+ printMapSize() + "\n");
        
        buildCodonMap(2, dna);
        System.out.println("most common codon: " + getMostCommonCodon());
        printCodonCounts(1, 500);
        System.out.println("Size:"+ printMapSize() + "\n");
        
    }

}
