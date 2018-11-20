package helper;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
        
/**
 *
 * @author Aleksi
 */
public class HelperUI extends Application{
    
    @Override
    public void start(Stage window){
       
        window.setTitle("LaTeX Helper");
        window.setHeight(500);
        window.setWidth(1000);
        BorderPane layout = new BorderPane();       
        
        TextArea txt = new TextArea();
        txt.setWrapText(true);
        VBox midbox = new VBox();
        midbox.getChildren().add(txt);
        
        
        TextArea txt_target = new TextArea();
        txt_target.setWrapText(true);
        txt_target.setEditable(false);
        
        HBox toptions = new HBox();
        Button file = new Button("---");
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
        
        QuoteParser qp = new QuoteParser();
        UmlautParser up = new UmlautParser();
        LineParser lp = new LineParser();
        ParagraphParser pp = new ParagraphParser();
        SectionParser sp = new SectionParser();
        
        
        ParserCollection pc = new ParserCollection(pp,sp,up,qp,lp);
        
        
        LTXCodeDoc lcd = new LTXCodeDoc(head,pc);
        
        txt_target.setText(lcd.toString());
                
        txt.textProperty().addListener((obs,o,n)-> {
            lcd.setText(n);
            lcd.parse();
            txt_target.setText(lcd.toString());
        });
        
        Scene scene = new Scene(layout);
        txt.requestFocus();
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(HelperUI.class);   
    }
    
}
