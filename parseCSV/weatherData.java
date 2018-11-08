


import java.io.File;

import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of weatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class weatherData {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallest = null;
        for (CSVRecord r : parser){
            if (smallest == null){
                smallest = r;
            }
            else {
                double currentTemp = Double.parseDouble(r.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallest.get("TemperatureF"));
                if ((currentTemp < smallestTemp ) && currentTemp != -9999){
                    smallest = r;
                }
            }
        }
        return smallest;
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String smallestName = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord r = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null){
                smallestSoFar = r;
                smallestName = f.getName();
            }
            else {
                double currentTemp = Double.parseDouble(r.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if ((currentTemp < smallestTemp ) && currentTemp != -9999){
                    smallestSoFar = r;
                    smallestName = f.getName();
                    smallestTemp = currentTemp;
                }
            }
        }
        System.out.println("Coldest temperature on that day was " + smallestSoFar.get("TemperatureF"));
        System.out.println("Coldest day was in file " + smallestName);
        return smallestName;
    }
    
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord smallest = null;
        for (CSVRecord r : parser){
            if (smallest == null){
                smallest = r;
            }
            else {
                String current = r.get("Humidity");
                if (current != "N/A"){
                    double currentH = Double.parseDouble(current);
                    double smallestH = Double.parseDouble(smallest.get("Humidity"));
                    if ((currentH < smallestH )){
                        smallest = r;
                    }
                }
            }
        }
        return smallest;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(csv);
        String h = csv.get("Humidity");
        String date = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " + h + " at " + date);
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String smallestName = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord r = lowestHumidityInFile(fr.getCSVParser());
            if (smallestSoFar == null){
                smallestSoFar = r;
                smallestName = f.getName();
            }
            else {
                double currentH = Double.parseDouble(r.get("Humidity"));
                double smallestH = Double.parseDouble(smallestSoFar.get("Humidity"));
                if ((currentH < smallestH ) && currentH != -9999){
                    smallestSoFar = r;
                }
            }
        }
        System.out.println("Lowest humidity was " + smallestSoFar.get("Humidity") + " at " + smallestSoFar.get("DateUTC"));
        return smallestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        lowestHumidityInManyFiles();
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double total = 0.0;
        double count = 0.0;
        for (CSVRecord rec : parser){
             total = total + Double.parseDouble(rec.get("TemperatureF"));
             count ++;
        }
        return total / count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double t = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + t);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double total = 0.0;
        double count = 0.0;
        for (CSVRecord rec : parser){
             String current = rec.get("Humidity");
             if (current != "N/A"){
                 int currentH = Integer.parseInt(current);
                 if (currentH >= value) {
                     total = total + Double.parseDouble(rec.get("TemperatureF"));
                     count ++;
                 }
             }
        }
        if (count == 0.0){
            return 0;
        }
        else{
            return total / count;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double t = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (t == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature when high humidity is " + t);
        }
    }
}
