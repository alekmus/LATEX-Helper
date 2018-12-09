/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LTXTitlePageTests;

import helper.domain.LTXTitlePage;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Aleksi
 */
public class LTXTitlePageTest {
    LTXTitlePage ltx;
    
   
    @Before
    public void setUp() {
        ltx = new LTXTitlePage();
    }
    
    @Test
    public void titleSequenceIsCorrect(){
        assertEquals("{\\begingroup\n" +
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
                        "", ltx.toString());
    }
}
