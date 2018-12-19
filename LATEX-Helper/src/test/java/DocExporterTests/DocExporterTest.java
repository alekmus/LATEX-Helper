/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocExporterTests;

import helper.domain.DocExporter;
import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.LTXTitlePage;
import helper.domain.ParserCollection;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Aleksi
 */
public class DocExporterTest {
    @Rule
    public TemporaryFolder test= new TemporaryFolder(); 
    
    private DocExporter exp;
    private LTXCodeDoc lcd;
   
    @Before
    public void setUp() {
        exp = new DocExporter();
        lcd = new LTXCodeDoc(
                new Header(), 
                new ParserCollection(),
                new LTXTitlePage());
                
    }
    
    @Test
    public void saveCreatesAFileCorrectly() {
        lcd.setText("Hello\n\nmy name is womble");
        lcd.parse();
        LTXCodeDoc opened = null;
        try {
            File f = test.newFile();
            exp.save(lcd, f);
            opened = exp.open(f);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        assertEquals(lcd.toString(),opened.toString());
    }
    
    @Test
    public void createsATexFile() {
        String fpath = "";
        try {
            File f = test.newFile();
            fpath = f.getAbsolutePath();
            exp.exportToTeX(lcd.getDoc(), f);
        }        
        catch (Exception e) {
                System.out.println(e);
        }        
        assertTrue(new File(fpath + ".tex").exists());
    }
    
    @Test
    public void createsAPDFFile() {
        boolean result = false;
        try {
            result = exp.exportToPDF(lcd.getDoc(),
                    "testpdf",
                    test.getRoot().getAbsolutePath());
        }        
        catch (Exception e) {
                System.out.println(e);
        }        
        assertTrue(result);
        
    }
    
}
