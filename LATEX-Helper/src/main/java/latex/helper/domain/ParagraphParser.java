package latex.helper.domain;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class ParagraphParser implements LTXParser{
    
    @Override
    public String parse(String string){
        String pattern = "^\\s+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        string = m.replaceAll("\n");
        return string;
    }
}
