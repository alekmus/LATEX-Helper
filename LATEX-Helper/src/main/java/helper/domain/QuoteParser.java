/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aleksi
 */
public class QuoteParser implements MicroParser {
   
    @Override
    public String parse(String str) {
        String out = "";
        ArrayList<String> sents = new ArrayList(Arrays.asList(str.split("(?<=\\.)")));
        
        for (String sent:sents) {
            boolean prevWasOpen = false;
            for (int i = 0; i < sent.length(); i++) {
                if (sent.charAt(i) == '"') {
                    if (!prevWasOpen) {
                        out += "``";
                        prevWasOpen = true;
                    } else {
                        out += "''";
                        prevWasOpen = false;
                    }
                } else {
                    out += sent.charAt(i);
                }
            }
        }
        return out;
    }
}
