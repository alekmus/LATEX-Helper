package helper.ui;
import helper.dao.MathDao;

public class TestUI {
    
    public static void main(String[] args){  
       try{ 
       MathDao mfa = new MathDao("src/main/resources/helper.db");
           System.out.println(mfa.notations());
       }
       catch(Exception e){
           System.out.println(e);
       }
    }
}
