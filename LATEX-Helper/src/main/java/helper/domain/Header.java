package helper.domain;

import java.util.ArrayList;
import java.util.List;

public class Header {
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
    
    public void setPackages(ArrayList<String> repl) {
        this.packages = repl;
    }

    public void addToPackages(String pack){
        this.packages.add(pack);
    }
    
    public void removePackage(String pack){
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
