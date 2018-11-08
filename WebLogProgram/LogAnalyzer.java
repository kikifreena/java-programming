
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
        // complete constructor
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String log : fr.lines()){
            records.add(WebLogParser.parseEntry(log));
        }
        // complete method
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP (HashMap<String, Integer> ips){
        int most = 0;
        for (int count : ips.values()){
            if (count > most){
                most = count;
            }
        }
        return most;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips){
        ArrayList<String> addresses = new ArrayList<String>();
        int most = mostNumberVisitsByIP(ips);
        for (String ip : ips.keySet()){
            if (ips.get(ip) == most && !(addresses.contains(ip))){
                addresses.add(ip);
            }
        }
        return addresses;
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records) {
            int code = le.getStatusCode();
            if (code > num){
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay (String someday){
        ArrayList<String> visits = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            int loc = date.indexOf(someday);
            String ip = le.getIpAddress();
            if ((loc!=-1) && !(visits.contains(ip))){
                visits.add(ip);
            }
        }
        return visits;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String dateMD = date.substring(4, 10);
            String ip = le.getIpAddress();
            if (map.containsKey(dateMD)){
                ArrayList<String> arr = map.get(dateMD);
                arr.add(ip);
                map.put(dateMD, arr);
            }
            else {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(ip);
                map.put(dateMD, arr);
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        int most = 0;
        String best = "";
        for (String ip: map.keySet()){
            int count = map.get(ip).size();
            if (count > most){
                most = count;
                best = ip;
            }
        }
        return best;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date){
        ArrayList<String> dateList = map.get(date);
        HashMap<String, Integer> ips = new HashMap<String, Integer>();
        for (String ip : dateList){
            if(ips.containsKey(ip)){
                ips.put(ip, ips.get(ip) + 1);
            }
            else {
                ips.put(ip, 1);
            }
        }
        return iPsMostVisits(ips);
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> count = new ArrayList<String>();
        for (LogEntry le : records) {
            int code = le.getStatusCode();
            String ip = le.getIpAddress();
            if ((code >= low) && (code <= high) && !(count.contains(ip))){
                count.add(ip);
            }
            
        }
        return count.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
