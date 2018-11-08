package latex.helper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
        
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
        HBox ala = new HBox();
        TextField cs = new TextField();
        TextField cs_target = new TextField();
       
        ala.getChildren().add(cs);
        ala.getChildren().add(cs_target);
        VBox vasen = new VBox();
        vasen.getChildren().add(txt);
        vasen.getChildren().add(ala);
        
        TextArea lTxt = new TextArea();
        
        txt.textProperty().addListener((obs,o,n)->{lTxt.setText(n);});
        cs.textProperty().addListener((obs,o,n)->{cs_target.setText(n);});
     
        layout.setRight(lTxt);
        layout.setLeft(vasen);
        
        Scene scene = new Scene(layout);
        txt.requestFocus();
        window.setScene(scene);
        window.show();
    }
    
//    public static void main(String[] args){
//        launch(HelperUI.class);   
//    }
    
}
