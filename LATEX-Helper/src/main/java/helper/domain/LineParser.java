package helper.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Separates a string into lines and creates the LaTEX "//" linebreak notations
 * when needed.
 * @author Aleksi
 */
public class LineParser implements MacroParser {
    /**
     * Separates a string into a list based on newline characters. Adds
     * LaTEX linebreak notations if the line isn't first or last and previous
     * or next line doesn't have a tab or newline in the beginning.
     * @param str String that will be split into lines
     * @return ArrayList of lines with desired breaklines.
     */
    @Override
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
