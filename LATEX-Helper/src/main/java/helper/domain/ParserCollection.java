package helper.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores parsers and regulates their roles.
 * @author Aleksi
 */
public class ParserCollection implements Serializable {
    private final List<MicroParser> microparsers;    
    private final List<MacroParser> macroparsers;
    
    /**
     * Accepts LTXParsers and separetes them into micro- and macroparsers based
     * on their classes so they can be used in the parseDoc method in correct
     * order.
     * @param args LTXParsers wished to be used.
     */
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
    /**
     * Takes a string and calls the parse method of the parser the class
     * was given.
     * @param docstring String of text that the parsers will be trying to mimic.
     * @return A string in LaTEX format given the rules of the parsers the class
     * has.
     */
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
