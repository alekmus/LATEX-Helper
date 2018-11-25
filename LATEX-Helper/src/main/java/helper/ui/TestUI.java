package helper.ui;
import helper.dao.MathDAO;

public class TestUI {
    
    public static void main(String[] args){  
       try{ 
       MathDAO mfa = new MathDAO("src/main/resources/helper.db");
           System.out.println(mfa.notations());
       }
       catch(Exception e){
           System.out.println(e);
       }
    }
}
