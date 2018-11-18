package latex.helper.domain;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class SectionParser implements LTXParser{
    
    @Override
    public String parse(String string){
        String pattern = "\n\n";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        if (m.find()){
            string = "\\section{"+string+"}";
        }
        return string;
    }
}
