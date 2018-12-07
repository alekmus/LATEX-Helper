package helper.domain;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Processes a set of characters that have umlauts into more compatible form.
 * Most notably from a Finnish standpoint affects ä, ö and å.
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
    /**
     * Class utilises its HasMap of different values for each letter to replace
     * them with LaTEX notations .
     * @param str A String where characters will be replaced with corresponding
     * sequences.
     * @return The input string with replaced values.
     */
    @Override
    public String parse(String str) {
        for (String c: chars.keySet()) {
            str = str.replace(c, chars.get(c));
        }
        return str;
    }
}
