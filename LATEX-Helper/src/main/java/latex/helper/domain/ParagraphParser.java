package latex.helper.domain;

public class ParagraphParser implements LTXParser{
    
    @Override
    public String parse(String string){
        return string.replace("\n\t", "\n\n");
    }
}
