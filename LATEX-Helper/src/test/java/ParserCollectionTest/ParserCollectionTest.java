/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserCollectionTest;

import helper.domain.LTXParser;
import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aleksi
 */
public class ParserCollectionTest {
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
