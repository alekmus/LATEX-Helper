package helper.domain;

import java.io.Serializable;

/**
 * Stores the information and functionality that is related to the title page of
 * the LaTEX document
 * @author Aleksi
 */
public class LTXTitlePage implements Serializable {
    private boolean pageNumsOn;
    private String author;
    private String opens;
    private String closes;
    private String title;
    private boolean tableOCOn;
    
    public LTXTitlePage() {
        this.pageNumsOn = true;
        this.author = "";
        this.opens = "{\\begingroup\n"
                + "\\centering\n"
                + "\\pagenumbering{gobble}\n"
                + "\\vspace*{\\fill}\n\n";
        this.closes = "\\date*{\\today}\n\n"
                + "\\vspace*{\\fill}\n"
                + "\\endgroup}\n";
        this.title = "";
        this.tableOCOn = true;
    }
    
    public void setpageNumsOn(boolean value) {
        this.pageNumsOn = value;
    }
    
    public void setAuthor(String repl) {
        this.author = repl;
    }
    
    public boolean getpageNumsOn() {
        return this.pageNumsOn;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setOpens(String repl) {
        this.opens = repl;
    }
    
    public String getOpens() {
        return this.opens;
    }
    
    public void settableOCOn(boolean var) {
        this.tableOCOn = var;
    }
    
    public boolean getTableOCOn() {
        return this.tableOCOn;
    }
    
    public void setCloses(String repl) {
        this.closes = repl;
    }
    
    public String getCloses() {
        return this.closes;
    }
    
    public void setTitle(String repl) {
        this.title = repl;
    }
    
    public String getTitle() {
        return this.title;
    }
    /**
     * Return the title page information and regulates what information is shown
     * based on user's reguirements.
     * @return String of titlePage information
     */
    @Override
    public String toString() {
        String str = "";
        
        str += this.opens;
        str += this.title;
        str += "\\\\ \\baselineskip=30pt\n";
        str += this.author + "\n";
        str += this.closes;        
        str += "\\pagebreak\n";
        
        if (this.pageNumsOn) {
            str += "\\pagenumbering{arabic}\n";
        } else {
            str += "\\pagenumbering{gobble}\n";
        }
        
        if (this.tableOCOn) {
            str += "\\tableofcontents\n";
        }
        
        str += "\\pagebreak\n";
        
        return str;
    }
    
}
