package latex.helper.domain;

import java.util.List;

public class LTXCodeDoc {
    private Header header;
    private Object[] doc;
    
    
    public LTXCodeDoc(Header header){
        this.doc = new Object[4];
        this.doc[0] = header;
        this.doc[1] = "\\begin{document}\n";
        this.doc[2] = "";
        this.doc[3] = "\n\\end{document}";
    }
    
    public void setText(String replacement){
        this.doc[2] = replacement;
    }
    
    public void parse(List<LTXParser> parsers){
        for (LTXParser parser : parsers){
            doc[2] = parser.parse((String)doc[2]);
        }
    }
    
    public String getText(){
        return (String) this.doc[2];
    }
    
    @Override
    public String toString(){
        String string = "";
        for (Object temp : this.doc){
            string += temp.toString();
        }
        return string;
    }
    
    
}
