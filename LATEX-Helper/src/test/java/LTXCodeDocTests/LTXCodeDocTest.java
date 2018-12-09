/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LTXCodeDocTests;

import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.LTXTitlePage;
import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Aleksi
 */
public class LTXCodeDocTest {
    private LTXCodeDoc lcd;
    
    @Before
    public void setUp() {
        Header head = new Header();
        head.addToPackages("\\usepackage[]{}");
        LTXTitlePage titlePage = new LTXTitlePage();
        QuoteParser qp = new QuoteParser();
        UmlautParser up = new UmlautParser();
        LineParser lp = new LineParser();
        ParagraphParser pp = new ParagraphParser();
        SectionParser sp = new SectionParser();
        ParserCollection pc = new ParserCollection(pp,sp,up,qp,lp);
        lcd = new LTXCodeDoc(head, pc, titlePage);
    }
    
    @Test
    public void codeSequenceIsCorrect(){
        assertEquals("\\documentclass[a4paper, 12pt]{article}\n" +
                        "\\usepackage[]{}\n" +
                        "\n" +
                        "\\title{}\n" +
                        "\n" +
                        "\\begin{document}\n" +
                        "{\\begingroup\n" +
                        "\\centering\n" +
                        "\\pagenumbering{gobble}\n" +
                        "\\vspace*{\\fill}\n" +
                        "\n" +
                        "\\\\ \\baselineskip=30pt\n" +
                        "\n" +
                        "\\date*{\\today}\n" +
                        "\n" +
                        "\\vspace*{\\fill}\n" +
                        "\\endgroup}\n" +
                        "\\pagebreak\n" +
                        "\\pagenumbering{arabic}\n" +
                        "\\tableofcontents\n" +
                        "\\pagebreak\n" +
                        "\n" +
                        "\\end{document}", lcd.toString());
    }
    
}
