package helper.domain;

import java.util.ArrayList;

public class LTXCodeDoc {
    private Header header;
    private String doc;
    private ParserCollection pc;
    private LTXTitlePage titlePage;
    
    public LTXCodeDoc(Header header, ParserCollection pc, LTXTitlePage ltp) {
        this.pc = pc;
        this.header = header;
        this.doc = "";
        this.titlePage = ltp; 
    }
    
    public LTXCodeDoc(Header header, ParserCollection pc) {
        this.pc = pc;
        this.header = header;
        this.doc = "";
    }
    
    public void setText(String replacement) {
        this.doc = replacement;
    }
    
    public void parse() {
        this.doc = pc.parseDoc(doc);
    }
    
    public String getText() {
        return this.doc;
    }
    
    public String getTitle() {
        return this.header.getTitle();
    }
    
    public void setTitle(String repl) {
        this.header.setTitle(repl);
        titlePage.setTitle(repl);
    }
    
    public void setAuthor(String repl) {
        titlePage.setAuthor(repl);
    }
    
    public void addPackage(String pack){
        this.header.addToPackages(pack);
    }
    
    public void removePackage(String pack){
        this.header.removePackage(pack);
    }
    
    public ArrayList getPackages(){
        return this.header.getPackages();
    }
    
    public void setFontSize(String str) {
        this.header.setFontSize(str);
    }
    
    @Override
    public String toString() {
        String str = this.header.toString();
        
        str +=  "\\begin{"
                + this.header.getDoctype()
                + "}\n"
                + titlePage
                + doc
                + "\n\\end{"
                + this.header.getDoctype() + "}";
        return str;
    }
    
    
}
