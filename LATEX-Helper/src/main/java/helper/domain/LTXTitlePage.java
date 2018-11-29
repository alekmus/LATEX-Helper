/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;

/**
 *
 * @author Aleksi
 */
public class LTXTitlePage {
    private boolean pageNumsOn;
    private String author;
    private String opens;
    private String closes;
    private String title;
    private boolean tableOCOn;
    
    public LTXTitlePage() {
        this.pageNumsOn = true;
        this.author = "Author";
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
    
    @Override
    public String toString() {
        String str = "";
        
        str += this.opens;
        str += this.title;
        str += "\n [\\baselineskip]\n";
        str += this.author + "\n";
        str += this.closes;        
        str += "\\pagebreak\n";
        
        if (this.pageNumsOn) {
            str += "\\pagenumbering{arabic}\n";
        }
        
        if (this.tableOCOn) {
            str += "\\tableofcontents\n";
        }
        
        str += "\\pagebreak\n";
        
        return str;
    }
    
}
