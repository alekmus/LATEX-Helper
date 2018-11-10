package latex.helper;
import java.util.ArrayList;
import java.util.Arrays;
import latex.helper.domain.Header;
import latex.helper.domain.LTXCodeDoc;
import latex.helper.domain.ParagraphParser;

public class TestUI {
    
    public static void main(String[] args){      
        String txt = "It was the best of times, it was the worst of times, \n"
                + " it was the age of wisdom, it was the age of foolishness";
        
        ArrayList<String> lista = new ArrayList();
        lista.add("\\documentclass[a4paper,12pt]{article}");
        
        Header head = new Header(lista);
        ParagraphParser pp = new ParagraphParser();
        LTXCodeDoc parser = new LTXCodeDoc(head,new ArrayList(Arrays.asList(pp)));
        parser.setText(txt);
        System.out.println(parser);
    }
}
