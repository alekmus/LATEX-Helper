package helper.domain;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Stores the different components that make up the application's functionality.
 * and manages their relationships.
 * @author Aleksi
 */
public class LTXCodeDoc implements Serializable {
    private Header header;
    private String doc;
    private String text;
    private ParserCollection pc;
    private LTXTitlePage titlePage;
    private boolean showTitle;
    
    public LTXCodeDoc(Header header, ParserCollection pc, LTXTitlePage ltp) {
        this.pc = pc;
        this.header = header;
        this.doc = "";
        this.titlePage = ltp; 
        this.text = "";
        this.showTitle = true;
    }
    
    public void setShowTitle(boolean val) {
        this.showTitle = val;
    }
    
    public void useSectionNums(boolean val) {
        this.pc.replaceParserWith(new SectionParser(val));
    }
    
    public boolean getShowTitle() {
        return this.showTitle;
    }
    
    public void setDoc(String replacement) {
        this.doc = replacement;
    }
    
    public void setText(String replacement) {
        this.text = replacement;
    }
    
    public void parse() {
        this.doc = pc.parseDoc(doc);
    }
    
    public String getDoc() {
        return this.doc;
    }
    
    public String getTitle() {
        return this.header.getTitle();
    }
    
    public String getText() {
        return this.text;
    }
    
    public Header getHeader() {
        return this.header;
    }
    
    public LTXTitlePage getTitlePage() {
        return this.titlePage;
    }
    
    public void setTitle(String repl) {
        this.header.setTitle(repl);
        titlePage.setTitle(repl);
    }
    
    public void setAuthor(String repl) {
        titlePage.setAuthor(repl);
    }
    
    public void addPackage(String pack) {
        this.header.addToPackages(pack);
    }
    
    public void removePackage(String pack) {
        this.header.removePackage(pack);
    }
    
    public ArrayList getPackages() {
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
                + "}\n";
        if (this.showTitle) {
            str += this.titlePage;
        }
        
        str += doc
                + "\n\\end{"
                + this.header.getDoctype() + "}";
        return str;
    }
    
    
}
