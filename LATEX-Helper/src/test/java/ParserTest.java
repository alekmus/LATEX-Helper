import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.SectionParser;
import java.util.ArrayList;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Aleksi
 */
public class ParserTest {
    
    ParagraphParser pp;
    SectionParser sp;
    LineParser lp;
            
    @Before
    public void setUp() {
        pp = new ParagraphParser();
        sp = new SectionParser();
        lp = new LineParser();
    }
    
    @Test
    public void paragraphParserCreatesNewlinesFromForwardTabs(){
        assertEquals("\nhello",pp.parse("   hello"));
    }
    
    @Test
    public void paragraphParserDoesntCreateNewlinesFromBackTabs(){
        assertEquals("hello   ",pp.parse("hello   "));
    }
    
    @Test
    public void paragraphParserDoesntCreateNewlinesFromMidTabs(){
        assertEquals("hel   lo",pp.parse("hel   lo"));
    }
    
    @Test
    public void sectionParserCreatesSectionHeaderAfterTwoNewlines(){
        ArrayList<String> list = sp.parse("hello\n\nhello");
        assertEquals("hello\n\n",list.get(0));
        assertEquals("\\section{hello}\n",list.get(1));
    }
    
    @Test
    public void lineParserChopsUpLinesWithNewline(){
        ArrayList<String> list = lp.parse("hello\nI am a great big\nhuge womble");
        assertEquals("hello\n",list.get(0));
        assertEquals("I am a great big\n",list.get(1));
        assertEquals("huge womble",list.get(2));
    }
}    
