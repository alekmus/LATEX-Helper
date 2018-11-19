package helper.domain;
import java.util.ArrayList;
import java.util.Arrays;

public class SectionParser implements MacroParser{
    private final int N = 30;
    
    public ArrayList<String> parse(String string){
        ArrayList<String> parts = new ArrayList(
                Arrays.asList(string.split("(?<=\\n\\n)")));
        
        for(int i = 1; i<parts.size(); i++){
            if(parts.get(i).length()<N && !parts.get(i).isEmpty()){
                parts.set(i,"\\section{"+parts.get(i).trim()+"}\n");
                if(i+1<parts.size()){
                    parts.set(i, parts.get(i)+parts.get(i+1).trim());
                    parts.remove(i+1);
                }    
            }else if(!parts.get(i).equals("\\s")&& !parts.get(i).isEmpty()){
                parts.set(i,"\\section{}\n"+parts.get(i).trim());
            }
        }
        
        return parts;

    }
}


