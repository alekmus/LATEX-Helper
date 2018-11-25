package parserTests;

import helper.parsers.LTXParser;
import helper.parsers.LineParser;
import helper.parsers.ParagraphParser;
import helper.parsers.ParserCollection;
import helper.parsers.QuoteParser;
import helper.parsers.SectionParser;
import helper.parsers.UmlautParser;
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
        pc = new ParserCollection(pp,sp,up,qp,lp);
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
    public void sectionParserCreatesSectionHeader(){
        ArrayList<String> list = sp.parse("hello");
        assertEquals("\\section*{hello}\n",list.get(0));
        
    }
    
    @Test
    public void lineParserChopsUpLinesWithNewline(){
        ArrayList<String> list = lp.parse("hello\nI am a great big\nhuge womble");
        assertEquals("hello\n",list.get(0));
        assertEquals("I am a great big\\\\\n",list.get(1));
        assertEquals("huge womble\n",list.get(2));
    }
    
    @Test
    public void umlautsWork(){
        assertEquals("\\¨{a}",up.parse("ä"));
        assertEquals("\\¨{o}",up.parse("ö"));
        assertEquals("\\r{a}",up.parse("å"));
        assertEquals("\\¨{A}",up.parse("Ä"));
        assertEquals("\\¨{O}",up.parse("Ö"));
        assertEquals("\\r{A}",up.parse("Å"));
    }
    
    @Test
    public void parserCollectionSeparatesParsers(){
        ArrayList<LTXParser> micros = new ArrayList();
        micros.add(up);
        micros.add(qp);
        micros.add(pp);
        ArrayList<LTXParser> macros = new ArrayList();
        macros.add(sp);
        macros.add(lp);
        assertTrue(pc.getmacros().containsAll(macros));
        assertTrue(pc.getmicros().containsAll(micros));
    }
    
    @Test
    public void parserCollectionParseDocWorks(){
        assertEquals("\\section*{Introduction}\nHow is everyone this should\n",
                pc.parseDoc("Introduction\n\nHow is everyone this should")); 
    }
}    
