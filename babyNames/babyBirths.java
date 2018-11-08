import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of babyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class babyBirths {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalF = 0;
        int totalM = 0;
        int total = 0;
        int numF = 0;
        int numM = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths+= numBorn;
            total++;
            String sex = rec.get(1);
            if (sex.equals("F")){
                totalF += numBorn;
                numF ++;
            }
            if (sex.equals("M")){
                totalM += numBorn;
                numM++;
            }
        }
        System.out.println("total names = " + total);
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalM);
        System.out.println("# of boys names = " + numM);
        System.out.println("total girls = " + totalF);
        System.out.println("# of girls names = " + numF);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1900.csv");
        totalBirths(fr);
        FileResource fr2 = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr2);
    }
    
    public int getRank(int year, String name, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv"; // change later
        FileResource fr = new FileResource(filename);
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String s = rec.get(1);
            String n = rec.get(0);
            int numBorn = Integer.parseInt(rec.get(2));
            if (s.equals(gender)){
                currentRank ++;
                if (n.equals(name)){
                    return currentRank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank(){
        System.out.println(getRank(1961, "Mich", "M"));
        
        //System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv"; // change later
        FileResource fr = new FileResource(filename);
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String s = rec.get(1);
            String n = rec.get(0);
            int numBorn = Integer.parseInt(rec.get(2));
            if (s.equals(gender)){
                currentRank ++;
                if (currentRank == rank){
                    return n;
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        System.out.println(getName(1980, 350, "F"));
        
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        if (rank != -1){
            String newName = getName(newYear, rank, gender);
            System.out.println(name + " born in " + year + " would be " + newName + " if s/he was born in " + newYear + ".");
        }
        else {
            System.out.println("Name not found in " + year + "!");
        }
        
    }
    
    public void test(){
        //whatIsNameInYear("Susan", 1972, 2014, "F");
        
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        //System.out.println(yearOfHighestRank("Genevieve","F"));
        
        System.out.println(yearOfHighestRank("Mich","M"));
        //System.out.println(getRank(2012, "Mason", "M"));
        //System.out.println(getAverageRank("Susan", "F"));
        //System.out.println(getAverageRank("Robert", "M"));
        //System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
        
        //System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
    
    public int yearOfHighestRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 4000;
        int best = -1;
        for (File f : dr.selectedFiles()) {
            String filename = f.getName();
            int len = "yob".length(); // change later
            String x = filename.substring(len, len + 4);
            int year = Integer.parseInt(x);
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1){
                if (currentRank < 1000){
                    System.out.println("The year is " + year + " and the rank is " + currentRank);
                }
                if ((currentRank < rank) || rank == 4000){
                    rank = currentRank;
                    best = year;
                }
            }
        }
        return best;
    }
    
    public double getAverageRank ( String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double totalFiles = 0.0;
        double totalRank = 0.0;
        for (File f : dr.selectedFiles()) {
            String filename = f.getName();
            int len = "yob".length(); // change later
            String x = filename.substring(len, len + 4);
            int year = Integer.parseInt(x);
            int currentRank = getRank(year, name, gender);
            totalRank += currentRank;
            totalFiles++;
        }
        return totalRank / totalFiles;
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int rank = getRank(year, name, gender);
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv"; // change later
        FileResource fr = new FileResource(filename);
        int total = 0;
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String s = rec.get(1);
            String n = rec.get(0);
            int numBorn = Integer.parseInt(rec.get(2));
            if (s.equals(gender)){
                currentRank ++;
                if (currentRank == rank){
                    break;
                }
                total += numBorn;
            }
        }
        return total;
    }
}

// 2225
// 1421
// 251
// 54
// Mia
// Forrest
// Addison
// Leonel
// 1914
// 1960
// 173.51
//10.75
// 323200
// 1498074