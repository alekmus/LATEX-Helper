package helper;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.LTXTitlePage;
import helper.domain.LineParser;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
        
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
        
        TextField title = new TextField();
        title.setPromptText("Title of your document");
        
        TextArea txt = new TextArea();
        txt.setWrapText(true);       
        
        HBox mathModeSearch = new HBox();
        
        TextField searchbar = new TextField();
        TextField searchresult = new TextField();
        
        mathModeSearch.getChildren().addAll(searchbar,searchresult);
        mathModeSearch.setPadding(new Insets(5,0,5,0));
        mathModeSearch.setAlignment(Pos.CENTER);
        ImageView preview = new ImageView();
        
        VBox midBox = new VBox();
        midBox.setPadding(new Insets(0,5,5,5));
        midBox.getChildren().addAll(title,txt,mathModeSearch,preview);
                
        TextArea txtTarget = new TextArea();
        txtTarget.setPrefHeight(Integer.MAX_VALUE);
        txtTarget.setWrapText(true);
        txtTarget.setEditable(false);
        
        
        VBox rightBox = new VBox();
        rightBox.getChildren().add(txtTarget);
        rightBox.setPadding(new Insets(0,5,5,0));
        VBox.setVgrow(rightBox, Priority.ALWAYS);
                
        HBox toptions = new HBox();
        Button filebutton = new Button();
        toptions.getChildren().add(filebutton);
        toptions.setMinHeight(20.0);
        layout.setTop(toptions);
        
        VBox sideOptions = new VBox();
        sideOptions.setMinWidth(100.0);
        HBox fields = new HBox();
        fields.getChildren().addAll(sideOptions, midBox, rightBox);
        
        layout.setCenter(fields);
        
        Header head = new Header(
                Arrays.asList("\\documentclass[a4paper,12pt]{article}",
                        "\\usepackage[utf8]{inputenc}",
                        "\\usepackage[Finnish]{babel}" ));
        
        QuoteParser qp = new QuoteParser();
        UmlautParser up = new UmlautParser();
        LineParser lp = new LineParser();
        ParagraphParser pp = new ParagraphParser();
        SectionParser sp = new SectionParser();
        
        
        ParserCollection pc = new ParserCollection(pp,sp,up,qp,lp);
        LTXTitlePage titlePage = new LTXTitlePage();
        
        LTXCodeDoc lcd = new LTXCodeDoc(head, pc, titlePage);
        
        txtTarget.setText(lcd.toString());
        
        title.textProperty().addListener((obs,o,n)->{
            lcd.setTitle(n);
            txtTarget.setText(lcd.toString());
        });
                
        txt.textProperty().addListener((obs,o,n)-> {
            lcd.setText(n);
            lcd.parse();
            double pos = txtTarget.getScrollTop();
            txtTarget.setText(lcd.toString());
            txtTarget.setScrollTop(pos);
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
