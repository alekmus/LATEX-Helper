package helper.parsers;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class ParagraphParser implements MicroParser {
    
    @Override
    public String parse(String str) {
        String pattern = "^\\s+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        str = m.replaceAll("\n");
        return str;
    }
}
