package helper.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Takes quotation marks and processes them into the preferred LaTEX format.
 * @author Aleksi
 */
public class QuoteParser implements MicroParser {
    
    /**
     * Takes string with non-directional quotation marks and changes the into 
     * directional quotation marks. Does not recognise nested quotes.
     * @param str A String with non-directional quotes.
     * @return input string but with directional quotes.
     */
    @Override
    public String parse(String str) {
        String out = "";
        ArrayList<String> sents = new ArrayList(
                Arrays.asList(str.split("(?<=\\.)")));
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
