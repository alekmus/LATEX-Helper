/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Aleksi
 */
public class ParserCollection {
    private final List<MicroParser> microparsers;    
    private final List<MacroParser> macroparsers;
    
    public ParserCollection(LTXParser... args) {
        this.microparsers = new ArrayList();
        this.macroparsers = new ArrayList();
        
        for (LTXParser p: args) {
            if (p.getClass()
                    .getInterfaces()[0]
                    .getSimpleName()
                    .equals("MicroParser")) {
                this.microparsers.add((MicroParser) p);
            } else if (p.getClass()
                    .getInterfaces()[0]
                    .getSimpleName()
                    .equals("MacroParser")) {
                this.macroparsers.add((MacroParser) p);
            }
        }
    }
    
    public List<MicroParser> getmicros() {
        return this.microparsers;
    }
    
    public List<MacroParser> getmacros() {
        return this.macroparsers;
    }
    
    public String parseDoc(String docstring) {
        ArrayList<String> parts = new ArrayList();
        ArrayList<String> temp = new ArrayList();
        parts.add(docstring);
        
        for (MacroParser a: this.macroparsers) {
            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);
                temp.addAll(a.parse(part));
            }
            parts = (ArrayList<String>) temp.clone();
            temp.clear();
        }
        
        for (MicroParser b: this.microparsers) {
            for (int i = 0; i < parts.size(); i++) {
                parts.set(i, b.parse(parts.get(i)));
            }
        }
        
        String output = "";    
        for (String part: parts) {
            output += part;
        }
        
        return output;
    }
}
