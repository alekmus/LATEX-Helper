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
    
    public void save(LTXCodeDoc lcd, File file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(lcd);
            objOut.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }  
    }
    
    public LTXCodeDoc open(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Object ob = objIn.readObject();
            if (ob.getClass().getSimpleName().equals("LTXCodeDoc")) {
                return (LTXCodeDoc) ob;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public boolean exportToTeX(String doc, File file) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(doc);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }  
        return true;
    }
    
    
    public boolean exportToPDF(String doc, String filename, String directory) {
        try (FileWriter fw = new FileWriter(
                new File(directory + filename + ".tex"))) {
            fw.write(doc);
            
            Runtime run = Runtime.getRuntime();        
            Process pro = new ProcessBuilder("pdflatex").start(); 
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));
        
            if (br.readLine() == null) {
                return false;
            }
            pro = new ProcessBuilder("pdflatex", filename, ".tex")
                    .directory(new File(directory))
                    .start();
        } catch (Exception e) {
            return false;
        }      
        return true;
    }
}
