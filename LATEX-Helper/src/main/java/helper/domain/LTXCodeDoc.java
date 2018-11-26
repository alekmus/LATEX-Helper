package helper.domain;

import helper.domain.ParserCollection;

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
    
    public void setTitle(String repl) {
        this.header.setTitle(repl);
        titlePage.setTitle(repl);
    }
    
    public void setAuthor(String repl) {
        titlePage.setAuthor(repl);
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
