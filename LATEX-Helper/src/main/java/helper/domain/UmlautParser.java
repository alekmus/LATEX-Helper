/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Aleksi
 */
public class UmlautParser implements MicroParser {
    private HashMap<String, String> chars;
    
    public UmlautParser() {
        chars = new HashMap();
        chars.put("ä", "\\¨{a}");
        chars.put("ö", "\\¨{o}"); 
        chars.put("Ä", "\\¨{A}");
        chars.put("Ö", "\\¨{O}"); 
        chars.put("å", "\\r{a}");
        chars.put("Å", "\\r{A}"); 
    }
    
    @Override
    public String parse(String str) {
        for (String c: chars.keySet()) {
            str = str.replace(c, chars.get(c));
        }
        return str;
    }
}
