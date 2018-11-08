
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }
    
    public void testHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> s = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(s.size());
    }
    
    public void testUniqueIPRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int i = la.countUniqueIPsInRange(200,299);
        System.out.println(i);
    }
    
    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int i = la.mostNumberVisitsByIP(counts);
        System.out.println(i);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> a= la.iPsMostVisits(counts);
        System.out.println(a);
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println(ipDays);
    }
    
    public void testdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        String day = la.dayWithMostIPVisits(ipDays);
        System.out.println(day);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        ArrayList<String> addrs = la.iPsWithMostVisitsOnDay(ipDays, "Sep 29");
        System.out.println(addrs);
    }
}

