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
    
    public boolean exportToTeX(String doc, String filename) {
        try (FileWriter fw = new FileWriter(new File(filename +".tex"))) {
            fw.write(doc);
        } catch (Exception e){
            System.out.println(e);
            return false;
        }  
        return true;
    }
    
    
    public boolean exportToPDF(String doc, String filename) {
        try (FileWriter fw = new FileWriter(
                new File("src/main/resources/saved/" + filename + ".tex"))) {
        fw.write(doc);
            
        
        Runtime run = Runtime.getRuntime();
        
        Process pro = new ProcessBuilder("pdflatex").start(); 
                //run.exec("pdflatex");
        
        BufferedReader br = new BufferedReader(
                new InputStreamReader(pro.getInputStream()));
        
        if (br.readLine() == null) {
            return false;
        }
        pro = new ProcessBuilder("pdflatex",
                filename,
                ".tex src/main/resources")
                .directory(new File("src/main/resources/saved"))
                .start();
 //       run.exec("pdflatex " + filename + ".tex src/main/resources");
        } catch (Exception e){
            return false;
        }      
        
        return true;
    }
}
