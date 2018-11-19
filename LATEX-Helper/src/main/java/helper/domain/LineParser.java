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
public class LineParser implements MacroParser{
    
    
    public ArrayList<String> parse(String string){
        ArrayList<String> parts = new ArrayList(Arrays.asList(string.replace("\n", "\\\\\n").split("(?<=\\n)")));
        return parts;

    }
}
