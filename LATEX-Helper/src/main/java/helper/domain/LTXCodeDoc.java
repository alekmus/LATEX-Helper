package helper.domain;

import java.util.ArrayList;
import java.util.List;

public class LTXCodeDoc {
    private Header header;
    private Object[] doc;
    private ArrayList<LTXParser> parsers;
    
    
    public LTXCodeDoc(Header header, ArrayList<LTXParser> parsers){
        this.parsers = parsers;
        this.doc = new Object[4];
        this.doc[0] = header;
        this.doc[1] = "\\begin{document}\n";
        this.doc[2] = new String[0];
        this.doc[3] = "\n\\end{document}";
    }
    
    public void setText(String replacement){
        this.doc[2] = replacement.split("\n");
    }
    
    public void parse(){
        for (LTXParser parser : this.parsers){
            String[] lines = (String[])doc[2];
            for(int i = 0; i < lines.length; i++){
                lines[i] = parser.parse(lines[i]);
            }
            doc[2] = lines;
        }
    }
    
    public String getText(){
        return (String) this.doc[2];
    }
    
    @Override
    public String toString(){
        String string = "";
        string += doc[0].toString();
        string += doc[1].toString();
        
        String[] lines = (String[])doc[2];
        for(int i = 0; i < lines.length; i++){
            string += lines[i];
            if(i != lines.length-1){
                string += "\n";
            }
        }
        
        string += doc[3].toString();
        
        return string;
    }
    
    
}
