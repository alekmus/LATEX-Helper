/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;
import java.io.*;

/**
 *
 * @author Aleksi
 */
public class DocExporter {
    
    public void save(String doc, String filename) {
        try (FileWriter fw = new FileWriter(new File(filename +".txt"))) {
            fw.write(doc);
            
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
    public void exportToTeX(String doc, String filename) {
        try (FileWriter fw = new FileWriter(new File(filename +".tex"))) {
            fw.write(doc);
        } catch (Exception e){
            System.out.println(e);
        }  
    }
    
    
    public void exportToPDF(String doc, String filename) {
        try (FileWriter fw = new FileWriter(new File(filename + ".tex"))) {
        fw.write(doc);
        
        Runtime run = Runtime.getRuntime();
        Process pro = run.exec("pdflatex" + filename + ".tex");
        } catch (Exception e){
            System.out.println(e);
        }      
    }
}
