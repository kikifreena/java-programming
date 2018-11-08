import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void linkReader (){
    URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.words()) {
            int youTubeCheck = s.toLowerCase().indexOf("youtube.com");
            if (youTubeCheck > -1){
                // find first quote - int
                int first = s.lastIndexOf("\"", youTubeCheck);
                // find second quote - int
                int second = s.indexOf("\"", youTubeCheck);
                // substring
                System.out.println( s.substring(first, second) );
            }
            
        };
    }
}
