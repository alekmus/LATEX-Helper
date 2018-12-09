package helper.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Splits a string into parts based on interpreted section changes. Tries to
 * find headline for each section based on a give length seen reasonable for
 * section headlines.
 * @author Aleksi
 */
public class SectionParser implements MacroParser {
    private int titleLength = 30;
    private String sectionStyle;
    
    public SectionParser() {
        this.sectionStyle = "\\section{";
    }
    
    public SectionParser(boolean usenums) {
        if (usenums) {
            this.sectionStyle = "\\section{";
        } else {
            this.sectionStyle = "\\section*{";
        }
        
    }
    
    
    public void setTitleLength(int len) {
        this.titleLength = len;
    }
    
    /**
     * Defines if the LaTeX document should use section numbering or not
     * @param usenums Boolean value representing if numbering is wanted
     */
    
    public void useNumStyle(boolean usenums) {
        if (usenums) {
            this.sectionStyle = "\\section{"; 
        } else {
            this.sectionStyle = "\\section*{";
        }
        
    }
    
    
    /**
     * Takes a string and splits it based on newline characters. If short enough
     * uses the first line after a split as a section headline. Otherwise just 
     * uses the section number.
     * @param str A String that is being split
     * @return An ArrayList with perceived sections of the string.
     */
    @Override
    public ArrayList<String> parse(String str) {
        ArrayList<String> parts = new ArrayList(
                Arrays.asList(str.split("\\n\\n")));
        
        if (parts.get(0).length() < titleLength && !parts.get(0).contains("\n") 
                && !parts.get(0).isEmpty()) {
            parts.set(0, this.sectionStyle + parts.get(0).trim() + "}\n");
            
            if (parts.size() > 1) {
                parts.set(0, parts.get(0) + parts.get(1).trim());
                parts.remove(1);
            }    
                
        } else if (!parts.get(0).equals("\\s") && !parts.get(0).isEmpty()) {
            parts.set(0, this.sectionStyle + "}\n" + parts.get(0).trim() + "\n");
        }
        
        for (int i = 1; i < parts.size(); i++) {
            if (parts.get(i).length() < titleLength && !parts.get(i).isEmpty() 
                    && !parts.get(i).contains("\n")) {
                parts.set(i, "\n\n" + this.sectionStyle + parts.get(i).trim() 
                        + "}\n");
                if (i + 1 < parts.size()) {
                    parts.set(i, parts.get(i) + parts.get(i + 1).trim());
                    parts.remove(i + 1);
                }    
            } else if (!parts.get(i).equals("\\s") && !parts.get(i).isEmpty()) {
                parts.set(i, "\n\n" + this.sectionStyle + "}\n"
                        + parts.get(i).trim() + "\n");
            }
        }
        return parts;

    }
}


