package helper.ui;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import helper.domain.Header;
import helper.domain.LTXCodeDoc;
import helper.domain.LTXTitlePage;
import helper.domain.LineParser;
import helper.dao.MathDao;
import helper.domain.DocExporter;
import helper.domain.ParagraphParser;
import helper.domain.ParserCollection;
import helper.domain.QuoteParser;
import helper.domain.SectionParser;
import helper.domain.UmlautParser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
        
/**
 *
 * @author Aleksi
 */
public class HelperUI extends Application{
    private LTXCodeDoc lcd;
    private MathDao madao; 
    private DocExporter de;
    private ArrayList<String> nots;
    private ArrayList<String> rempacks;
    
    
    @Override
    public void init() throws Exception{
        //Initalize the header and titlepage
        this.rempacks = new ArrayList<>();
        this.de = new DocExporter();
        Header head = new Header();
        head.addToPackages("\\usepackage[utf8]{inputenc}");
        head.addToPackages("\\usepackage[Finnish]{babel}");
        
        LTXTitlePage titlePage = new LTXTitlePage();

        
        //Initialize parsers and their collection
        QuoteParser qp = new QuoteParser();
        UmlautParser up = new UmlautParser();
        LineParser lp = new LineParser();
        ParagraphParser pp = new ParagraphParser();
        SectionParser sp = new SectionParser();
        ParserCollection pc = new ParserCollection(pp,sp,up,qp,lp);
        
        //Initialize the database class and store saved LATEX-notations to nots
        this.madao = new MathDao("src/main/resources/helper.db");
        this.nots = madao.notations();
        
        //Initalize the target code document
        this.lcd = new LTXCodeDoc(head, pc, titlePage);
    }
    
    @Override
    public void start(Stage window){
       
        window.setTitle("LaTeX Helper");
        window.setHeight(500);
        window.setWidth(1000);
        
        BorderPane layout = new BorderPane();
        
        
        //Middle text area and formula helper
        TextArea txt = new TextArea();
        txt.setWrapText(true);
        txt.setPrefHeight(Integer.MAX_VALUE);
        
        HBox mathModeSearch = new HBox();
        mathModeSearch.setSpacing(5);
        ComboBox<String> searchbar = new ComboBox();
        searchbar.setPromptText("--Select Formula--");
        searchbar.getItems().addAll(nots);
        
        TextField searchresult = new TextField();
        
        Button render = new Button("Preview");
        
        ImageView formula = new ImageView();
        BorderPane imgPane = new BorderPane();
        imgPane.setMinHeight(50);
        imgPane.setCenter(formula);
        
        render.setOnAction((e)->{
            TeXFormula tex = new TeXFormula(searchresult.getText());
            java.awt.Image im = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 
                                           28,
                                           java.awt.Color.BLACK,
                                           null);
            WritableImage fxim = SwingFXUtils.toFXImage((BufferedImage) im,
                                                        null);
            formula.setImage(fxim);
            
        });
        
        searchbar.valueProperty().addListener((obs,o,n)->{
            try{
                searchresult.setText("$"+this.madao.find(n)+"$");
                formula.setImage(null);
            }catch(Exception e){
                searchresult.setText("Problem with database connection");
            }
        });
        
        mathModeSearch.getChildren().addAll(searchbar, searchresult, render);
        mathModeSearch.setPadding(new Insets(5,0,5,0));
        mathModeSearch.setAlignment(Pos.CENTER);
        
        VBox midBox = new VBox();
        midBox.setPadding(new Insets(0,5,10,5));
        midBox.getChildren().addAll(txt, mathModeSearch, imgPane);
        midBox.setPrefWidth(Integer.MAX_VALUE);
        
        
        //Text target on the right side
        TextArea txtTarget = new TextArea();
        txtTarget.setPrefHeight(Integer.MAX_VALUE);
        txtTarget.setWrapText(true);
        txtTarget.setEditable(false);
        
        VBox rightBox = new VBox();
        rightBox.getChildren().add(txtTarget);
        rightBox.setPadding(new Insets(0,10,10,0));
        rightBox.setPrefWidth(Integer.MAX_VALUE);   
        
        txtTarget.setText(lcd.toString());         
        txt.textProperty().addListener((obs,o,n)-> {
            lcd.setDoc(n);
            lcd.setText(n);
            lcd.parse();
            double pos = txtTarget.getScrollTop();
            txtTarget.setText(lcd.toString());
            txtTarget.setScrollTop(pos);
        });
        
        
        // Options on the left
        VBox leftBox = new VBox();
        
        leftBox.setPadding(new Insets(0,0,10,0));
        leftBox.setMinWidth(200);
        
        TitledPane setupoptions = new TitledPane();
        setupoptions.setText("Set up");
        setupoptions.setExpanded(false);
        
        VBox setupsetup = new VBox();
        
        ListView packs = new ListView();
        packs.setCellFactory(CheckBoxListCell.forListView((String item) -> {
            BooleanProperty obsvable = new SimpleBooleanProperty();
            obsvable.addListener((obs, o, n) -> {
                if (n) {
                    rempacks.add(item);
                } else {
                    rempacks.remove(item);
                }
            });
            return obsvable ;
        }));        
        packs.getItems().setAll(lcd.getPackages());
        
        Label packlabel = new Label("Add package:");
        HBox packoptions = new HBox();
        TextField pack = new TextField();
        pack.setPromptText("Package name");
        TextField packdetails = new TextField();
        packdetails.setPromptText("Options");
        
        HBox packbuttons = new HBox();
        
        Button addpack = new Button("Add");
        addpack.setOnAction((e)->{
            if (!pack.getText().isEmpty()) {
                String packStr = pack.getText();
                if (!packdetails.getText().isEmpty()) {
                    String detStr = packdetails.getText();
                    lcd.addPackage(("\\usepackage["
                            + detStr
                            + "]{"
                            + packStr
                            + "}"));
                } else {
                    lcd.addPackage("\\usepackage{"+ packStr + "}");
                }
                packs.getItems().setAll(lcd.getPackages());
                txtTarget.setText(lcd.toString());
            }
        });
        
        Button removepack = new Button("Remove selected");
        removepack.setOnAction((e)->{
            for (String rem : rempacks){
                lcd.removePackage(rem);
                packs.getItems().setAll(lcd.getPackages());
            }
            txtTarget.setText(lcd.toString());
            
        });
        
        packbuttons.getChildren().addAll(addpack,removepack);
                
        packoptions.getChildren().addAll(pack,packdetails);        
        setupsetup.getChildren().addAll(new Label("Packages:"),
                packs,
                packlabel,
                packoptions,
                packbuttons);
        
        setupoptions.setContent(setupsetup);
        
        TitledPane titleoptions = new TitledPane();
        titleoptions.setText("Title");
        titleoptions.setExpanded(false);
        
        VBox titlesetup = new VBox();
        
        TextField title = new TextField();
        Label titlelabel = new Label("Title");
        titlelabel.setPrefWidth(100);
        HBox titlebox = new HBox(titlelabel,title);
        titlebox.setSpacing(5);
        
        title.textProperty().addListener((obs,o,n)->{
            lcd.setTitle(n);
            txtTarget.setText(lcd.toString());
        });
                
        TextField author = new TextField();
        Label authorlabel = new Label("Author");
        authorlabel.setPrefWidth(100);
        HBox authorbox = new HBox(authorlabel,author);   
        authorbox.setSpacing(5);
        
        author.textProperty().addListener((obs,o,n)->{
            lcd.setAuthor(n);
            txtTarget.setText(lcd.toString());
        });
        
        titlesetup.getChildren().addAll(titlebox, authorbox);     
        titleoptions.setContent(titlesetup);
        
        TitledPane fontoptions = new TitledPane();
        fontoptions.setText("Font");
        fontoptions.setExpanded(false);
        
        VBox fontsetup = new VBox();
        
        ComboBox<String> font = new ComboBox();        
        
        Label fontlabel = new Label("Font");
        fontlabel.setPrefWidth(100);
        HBox fontbox = new HBox(fontlabel,font);
        titlebox.setSpacing(5);
        
        ComboBox<String> fontsize = new ComboBox();
        
        for (int i = 1; i < 81; i++){
            fontsize.getItems().add(String.valueOf(i)+"pt");
        }
        fontsize.getSelectionModel().select("12pt");
        fontsize.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> {
            lcd.setFontSize(n);
            txtTarget.setText(lcd.toString());
        });
        
        
        Label fontsizelabel = new Label("Font size");
        fontsizelabel.setPrefWidth(100);
        HBox fontsizebox = new HBox(fontsizelabel, fontsize);
        titlebox.setSpacing(5);
        
        fontsetup.getChildren().addAll(fontbox,fontsizebox);
        
        fontoptions.setContent(fontsetup);
        
        leftBox.getChildren().addAll(setupoptions,titleoptions,fontoptions);
        
        
        // General layout
        HBox fields = new HBox();
        fields.getChildren().addAll(leftBox, midBox, rightBox);
        layout.setCenter(fields);        
        
        
        // menubar
        Menu file = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction((e) -> {
            FileChooser openChooser = new FileChooser();
            File openFile = openChooser.showOpenDialog(window);
            LTXCodeDoc openedDoc = de.open(openFile);
            if (openedDoc != null) {
                lcd = openedDoc;
                fontsize.getSelectionModel()
                        .select(lcd.getHeader().getFontSize());
                author.setText(lcd.getTitlePage().getAuthor());
                title.setText(lcd.getTitlePage().getTitle());
                txt.setText(lcd.getText());
                packs.getItems().setAll(lcd.getPackages());
            }
        });
        
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction((e) -> {
            FileChooser saveChooser = new FileChooser();
            File saveFile = saveChooser.showSaveDialog(window);
            de.save(lcd, saveFile);
        });
        
        Menu export = new Menu("Export");
        MenuItem toPDF = new MenuItem("to PDF");
        MenuItem toTex = new MenuItem("to Tex");
        export.getItems().addAll(toPDF, toTex);
        
        toPDF.setOnAction((e) -> {
            DirectoryChooser pdfChooser = new DirectoryChooser();
            pdfChooser.setTitle("Export to");
            File dir = pdfChooser.showDialog(window);
            if (dir != null) {
                String dirpath = dir.getAbsolutePath() + "\\";
                if (!de.exportToPDF(txtTarget.getText(), lcd.getTitle(),dirpath)){
                    Stage errorstage = new Stage();
                    errorstage.setTitle("Export error");
                    TextArea errorArea = new TextArea();
                    errorArea.setText("This program uses the LaTEX distribution "
                            + "to convert tex-files into pdf-files.\n"
                            + "Please make sure you have LaTEX installed before "
                            + "trying again to export the file.");
                    errorArea.setWrapText(true);
                    errorArea.setPrefSize(400, 100);
                    errorArea.setEditable(false);
                    Scene errorScene = new Scene(errorArea);
                    errorstage.setScene(errorScene);
                    errorstage.show();
                }
            }    
        });
        
        toTex.setOnAction((e) -> {
            FileChooser texChooser = new FileChooser();
            texChooser.getExtensionFilters().add(
                    new ExtensionFilter(".tex","*.tex"));
            File texfile = texChooser.showSaveDialog(window);
            if (texfile != null) {
                de.exportToTeX(txtTarget.getText(), texfile);
            }
        });
        
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction((e)->{
            System.exit(0);
        });
        
        file.getItems().addAll(openItem, saveItem, export, exitItem);
        
        
        MenuBar menubar = new MenuBar(file);
        layout.setTop(menubar);
        
        
        Scene scene = new Scene(layout);
        txt.requestFocus();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void stop(){
        System.out.println("Sovellus suljettu");
       
    }
    
    public static void main(String[] args){
        launch(HelperUI.class);   
    }
    
}
