
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    public int strcmp (char a[], char b[]){
        int i=0;
        boolean state = true;
        while (true){
            if (a[i] == b[i]){
                i++;
                continue;
            }
            else {
                state = false;
                break;
            }
        }
        if (state == false){
            return 0;
        }
        else {
            return 1;
        }
    }
    
    public void test(){
        System.out.println(strcmp(arr["a", "b", "C"]), arr(["a", "b", "C"])));
    }
}

