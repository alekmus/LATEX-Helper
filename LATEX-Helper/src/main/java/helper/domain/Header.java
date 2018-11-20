package helper.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Header {
    private List header;
    private String doctype;
    private String title;
    
    public Header(List<String> packages){
        this.header = packages.stream().collect(Collectors.toList());
        this.doctype = "document";
        this.title = "";
    }    
    
    public void setTitle(String repl){
        this.title = repl;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setHeader(List repl){
        this.header = repl;
    }    
    
    public List getHeader(){
        return this.header;
    }    
    
    public void add(String s){
        this.header.add(s);
    }
    
    @Override
    public String toString(){
        String string = "";
        
        for (Object temp : header){
            string += (String) temp + "\n";
        }
        string += "\n\\title{"+this.title+"}\n\n";
        return string;
    }
    
    public String getDoctype(){
        return this.doctype;
    }
}
