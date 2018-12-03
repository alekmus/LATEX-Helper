/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aleksi
 */
public class LineParser implements MacroParser {
    
    public ArrayList<String> parse(String str) {
        ArrayList<String> parts = new ArrayList(
                                        Arrays.asList(str.split("\\n")));
        
        for (int i = 0; i < parts.size(); i++) {
            if (i != 0 && i != parts.size() - 1
                    && (parts.get(i - 1).matches("^[^\t\n]+") 
                    || parts.get(i + 1).matches("^[^\t\n]+"))) {
                parts.set(i, parts.get(i) + "\\\\\n");
                
                
            } else {
                parts.set(i, parts.get(i) + "\n");    
            }
        }
        
        return parts;
    }
}
