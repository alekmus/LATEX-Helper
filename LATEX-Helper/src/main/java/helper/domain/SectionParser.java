package helper.domain;
import java.util.ArrayList;
import java.util.Arrays;

public class SectionParser implements MacroParser{
    private final int N = 30;
    private String sectionStyle;
    
    public SectionParser(){
        this.sectionStyle = "\\section*{";
    }
    
    public void setNumStyle(){
        this.sectionStyle = "\\section{";
    }
    
    public void setNoNumStyle(){
        this.sectionStyle = "\\section*{";
    }
    
    @Override
    public ArrayList<String> parse(String str){
        ArrayList<String> parts = new ArrayList(
                Arrays.asList(str.split("\\n\\n")));
        
        if(parts.get(0).length()<N && !parts.get(0).contains("\n") 
                && !parts.get(0).isEmpty()){
                parts.set(0,this.sectionStyle+parts.get(0).trim()+"}\n");
                if(parts.size()>1){
                    parts.set(0, parts.get(0)+parts.get(1).trim());
                    parts.remove(1);
                }    
                
        }else if(!parts.get(0).equals("\\s")&& !parts.get(0).isEmpty()){
            parts.set(0,this.sectionStyle+"}\n"+parts.get(0).trim()+"\n");
        }
        
        
        
        for(int i = 1; i<parts.size(); i++){
            if(parts.get(i).length()<N && !parts.get(i).isEmpty() 
                    && !parts.get(i).contains("\n")){
                parts.set(i,"\n\n"+this.sectionStyle+parts.get(i).trim()+"}\n");
                if(i+1<parts.size()){
                    parts.set(i, parts.get(i)+parts.get(i+1).trim());
                    parts.remove(i+1);
                }    
            }else if(!parts.get(i).equals("\\s")&& !parts.get(i).isEmpty()){
                parts.set(i,"\n\n"+this.sectionStyle+"}\n"
                        +parts.get(i).trim()+"\n");
            }
        }
        
        return parts;

    }
}


