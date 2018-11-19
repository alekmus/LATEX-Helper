package helper.domain;

import java.util.ArrayList;
import java.util.List;

public class LTXCodeDoc {
    private Header header;
    private String doc;
    private ParserCollection pc;
    
    
    public LTXCodeDoc(Header header, ParserCollection pc){
        this.pc = pc;
        this.header = header;
        this.doc = "";
    }
    
    public void setText(String replacement){
        this.doc = replacement;
    }
    
    public void parse(){
        this.doc = pc.parseDoc(doc);
    }
    
    public String getText(){
        return this.doc;
    }
    
    @Override
    public String toString(){
        String str = this.header.toString();
        str +="\\begin{"+this.header.getDoctype()
                +"}\n"
                +doc
                +"\n\\end{"
                +this.header.getDoctype()+"}";
        return str;
    }
    
    
}
