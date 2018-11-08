import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of exportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class exportData {
    public void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo (parser, "Nauru") );
        //parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters (parser, "cocoa") );
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        parser = fr.getCSVParser();
    }
    public String countryInfo (CSVParser parser, String country) {
        for (CSVRecord record : parser ){
            String c = record.get("Country");
            if (c.contains(country)) {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser ){
            String c = record.get("Country");
            String e = record.get("Exports");
            if (e.contains(exportItem1) && e.contains(exportItem2)){
                System.out.println(c);
            }
        }
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem){
        int n = 0;
        for (CSVRecord record : parser ){
            String c = record.get("Country");
            String e = record.get("Exports");
            if (e.contains(exportItem)){
                n++;
            }
        }
        return n;
    }
    
    public void bigExporters (CSVParser parser, String amount){
        for (CSVRecord record : parser ){
            String c = record.get("Country");
            String v = record.get("Value (dollars)");
            if (v.length() > amount.length()){
                System.out.println(c + " " + v);
            }
        }
    }
}
