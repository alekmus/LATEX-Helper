package helper.domain;
import java.io.*;

/**
 *Provides methods for saving the state of LTXCodeDoc objects and
 * exporting strings to .tex and .pdf files.
 * @author Aleksi
 */
public class DocExporter {
    /**
     * Saves the LTXCodeDoc object to file.
     * @param lcd LTXCodeDoc object to be saved.
     * @param file Location where the object will be saved.
     */
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
    /**
     * Creates a new LTXCodeDoc object from file.
     * @param file Location of saved LTXCodeDoc object.
     * @return a new LTXCodeDoc that has the same values as the saved one.
     */
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
    /**
     * Exports a string to a .tex file.
     * @param doc String that is being exported.
     * @param file Location where the .tex file will be saved.
     * @return Returns true if the export has been successfull and false if 
     * an exception is thrown while making the new file.
     */
    public boolean exportToTeX(String doc, File file) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(doc);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }  
        return true;
    }
    
    /**
     * Exports a string to pdf format. Uses a runtime executable from the LaTEX
     * distribution to resolve the LaTEX coding.
     * @param doc String that is being exported.
     * @param filename name that will be used to save the files.
     * @param directory Location where the pdf and accompanying files will 
     * be saved.
     * @return Returns true if the export has been successful and false if there
     * has been a problem with either the FileWriter or if the required LaTEX 
     * executable is not found in path.
     */
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
