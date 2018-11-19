package helper.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Header {
    private List header;
    private String doctype;
    
    public Header(List<String> packages){
        this.header = packages.stream().collect(Collectors.toList());
        this.doctype = "document";
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
        
        return string;
    }
    
    public String getDoctype(){
        return this.doctype;
    }
}
