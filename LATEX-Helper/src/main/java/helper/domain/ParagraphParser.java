package helper.domain;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;
/**
 * Creates desired indentations in a LaTEX document
 * @author Aleksi
 */
public class ParagraphParser implements MicroParser {
    /**
     * Replaces all tabs with newlines as LaTEX interpretes them
     * similarily
     * @param str String that is being processed.
     * @return A string with newlines instead of tabs
     */
    @Override
    public String parse(String str) {
        String pattern = "^\\s+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        str = m.replaceAll("\n");
        return str;
    }
}
