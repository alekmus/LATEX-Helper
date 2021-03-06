package helper.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Saves the header information for the LTXCodeDoc
 * @author Aleksi
 */
public class Header implements Serializable {
    private ArrayList<String> classdets;
    private String fontSize;
    private String classtype;
    private ArrayList<String> packages;
    private String doctype;
    private String title;
    
    public Header() {
        this.classdets = new ArrayList();
        this.classdets.add("a4paper");
        this.fontSize = "12pt";
        this.classtype = "article";
        this.packages = new ArrayList();
        this.doctype = "document";
        this.title = "";
    }    
    
    public void setTitle(String repl) {
        this.title = repl;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getFontSize() {
        return this.fontSize;
    }
    
    public String getClassType() {
        return this.classtype;
    }
    
    public void setPackages(ArrayList<String> repl) {
        this.packages = repl;
    }

    public void addToPackages(String pack) {
        this.packages.add(pack);
    }
    
    public void removePackage(String pack) {
        this.packages.remove(pack);
    }
    
    public ArrayList getPackages() {
        return this.packages;
    }    
    
    public void add(String s) {
        this.packages.add(s);
    }
    
    public void setFontSize(String str) {
        this.fontSize = str;
    }
    
    /**
     * Returns the header information in the wanted format and regulates what
     * information is shown based on user's reguirements.
     * @return String of the header information
     */    
    @Override
    public String toString() {
        String string = "\\documentclass[";
        for (String temp: this.classdets) {
            string += temp + ", ";
        }    
        string += this.fontSize + "]{" + this.classtype + "}\n";
        for (String temp : this.packages) {
            string += temp + "\n";
        }
        
        string += "\n\\title{" + this.title + "}\n\n";
        return string;
    }
    
    public String getDoctype() {
        return this.doctype;
    }
}
