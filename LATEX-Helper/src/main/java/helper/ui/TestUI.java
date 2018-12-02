package helper.ui;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestUI {
    
    public static void main(String[] args){  
        try {
        Runtime run = Runtime.getRuntime();
        Process pro = run.exec("late");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String line;
        while((line = br.readLine())!=null){
            System.out.println(line);
            System.out.println("1");
        }
        } catch (Exception e) {
            
        }
    }
}
