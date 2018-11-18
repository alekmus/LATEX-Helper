package latex.helper;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import latex.helper.domain.Header;
import latex.helper.domain.LTXCodeDoc;
import latex.helper.domain.ParagraphParser;
import latex.helper.domain.SectionParser;
        
/**
 *
 * @author Aleksi
 */
public class HelperUI extends Application{
    
    @Override
    public void start(Stage window){
       
        window.setTitle("LaTeX Helper");
        BorderPane layout = new BorderPane();       
        
        TextArea txt = new TextArea();
        VBox midbox = new VBox();
        midbox.getChildren().add(txt);
        
        TextArea txt_target = new TextArea();
        txt_target.setEditable(false);
        
        HBox toptions = new HBox();
        Button file = new Button("Tiedosto");
        toptions.getChildren().add(file);
        toptions.setMinHeight(20.0);
        layout.setTop(toptions);
        
        VBox sideOptions = new VBox();
        sideOptions.setMinWidth(100.0);
        HBox fields = new HBox();
        fields.getChildren().addAll(sideOptions, midbox, txt_target);
        
        layout.setCenter(fields);
        
        Header head = new Header(
                Arrays.asList("\\documentclass[a4paper,12pt]{article}"));
        
        ParagraphParser pp = new ParagraphParser();
        SectionParser sp = new SectionParser();
        LTXCodeDoc lcd = new LTXCodeDoc(head,new ArrayList(Arrays.asList(pp,sp)));
        txt_target.setText(lcd.toString());
                
        txt.textProperty().addListener((obs,o,n)-> {
            lcd.setText(n);
            lcd.parse();
            txt_target.setText(lcd.toString());
        });
                
        System.out.println("It was the best of times, it was the worst of times, \n"
                + "it was the age of wisdom, it was the age of foolishness, \n"
                + "it was the epoch of belief, it was the epoch of incredulity, \n"
                + "it was the season of Light, it was the season of Darkness, \n"
                + "it was the spring of hope, it was the winter of despair, \n\t"
                + "we had everything before us, we had nothing before us, \n"
                + "we were all going direct to Heaven, we were all going direct \n"
                + "the other way—in short, the period was so far like \n"
                + "the present period, that some of its noisiest authorities \n"
                + "insisted on its being received, for good or for evil, \n"
                + "in the superlative degree of comparison only.");
        
        Scene scene = new Scene(layout);
        txt.requestFocus();
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(HelperUI.class);   
    }
    
}
