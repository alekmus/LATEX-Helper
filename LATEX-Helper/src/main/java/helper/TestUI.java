package helper;
import java.util.ArrayList;
import java.util.Arrays;
import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.MathFormulaAccessor;
import helper.domain.ParagraphParser;
import helper.domain.QuoteParser;

public class TestUI {
    
    public static void main(String[] args){  
       try{ 
       MathFormulaAccessor mfa = new MathFormulaAccessor("src/main/resources/helper.db");
           System.out.println(mfa.notations());
       }
       catch(Exception e){
           System.out.println(e);
       }
    }
}
