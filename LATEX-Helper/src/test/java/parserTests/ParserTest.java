package parserTests;

import helper.domain.LTXParser;
import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
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
    QuoteParser qp;
    UmlautParser up;
    ParserCollection pc;
    
    
    @Before
    public void setUp() {
        pp = new ParagraphParser();
        sp = new SectionParser();
        lp = new LineParser();
        qp = new QuoteParser();
        up = new UmlautParser();
    }
     
    @Test
    public void paragraphParserCreatesNewlinesFromForwardTabs() {
        assertEquals("\nhello",pp.parse("   hello"));
    }
    
    @Test
    public void paragraphParserDoesntCreateNewlinesFromBackTabs() {
        assertEquals("hello   ",pp.parse("hello   "));
    }
   
    @Test
    public void paragraphParserDoesntCreateNewlinesFromMidTabs() {
        assertEquals("hel   lo",pp.parse("hel   lo"));
    }
    
    @Test
    public void sectionParserCreatesSectionHeader() {
        ArrayList<String> list = sp.parse("hello");
        assertEquals("\\section*{hello}\n",list.get(0));
        
    }
    
    @Test
    public void lineParserChopsUpLinesWithNewline() {
        ArrayList<String> list = lp.parse("hello\nI am a great big\nhuge womble");
        assertEquals("hello\n",list.get(0));
        assertEquals("I am a great big\\\\\n",list.get(1));
        assertEquals("huge womble\n",list.get(2));
    }
    
    @Test
    public void umlautsWork() {
        assertEquals("\\¨{a}",up.parse("ä"));
        assertEquals("\\¨{o}",up.parse("ö"));
        assertEquals("\\r{a}",up.parse("å"));
        assertEquals("\\¨{A}",up.parse("Ä"));
        assertEquals("\\¨{O}",up.parse("Ö"));
        assertEquals("\\r{A}",up.parse("Å"));
    }
    
    @Test
    public void sectionparserhandlesnonwhitespacefirstline() {
        sp.setTitleLength(1);
        assertEquals("\\section*{}\na\n",sp.parse("a").get(0));
    }
    
    @Test
    public void secttionparsercreatesheaderandtext() {
        assertEquals("\n\n\\section*{a}\nab",sp.parse("a\n\nab\n\na\n\nab").get(1));
    }
    
    @Test
    public void quotesclosecorrectly() {
        assertEquals("``''",qp.parse("\"\""));
    }
}    
