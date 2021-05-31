package view.panels;

import controller.adminPaneControllers.OverViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.SpelModel;
import model.database.SettingsDB;
import model.database.SpelersDB;

/**
 * @author Iedereen
 */
public class OverviewPane extends VBox {
    private OverViewController controller;

    private Text gameCounterTxt;
    private String log;
    private Text logTxt;
    private Button restartBtn;
    private Button sluitendBtn;

    public OverviewPane(OverViewController controller){
        this.controller = controller;
        controller.setView(this);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSpacing(10);

        //counter
        HBox counterBox = new HBox();
        gameCounterTxt = new Text("1");
        Text text1 = new Text("Dit is spel ");
        Text text2 = new Text(" van de huidige sessie");
        counterBox.getChildren().addAll(text1, gameCounterTxt, text2);

        //log
        this.log = "";
        this.logTxt = new Text(log);
        ScrollPane scrollPane = new ScrollPane(logTxt);
        scrollPane.setFitToHeight(true);
        scrollPane.setMinHeight(480);

        HBox h1 = new HBox(10);

        //reset
        restartBtn = new Button("Restart");
        restartBtn.setDisable(true);
        restartBtn.setOnAction(e -> controller.getModel().restart());

        //end
        sluitendBtn = new Button("Afsluiten");
        sluitendBtn.setDisable(true);
        sluitendBtn.setOnAction(e -> {
            SettingsDB.getInstance().save();
            SpelersDB.getInstance().save();
            System.exit(2);
        });

        h1.getChildren().setAll(restartBtn, sluitendBtn);
        this.getChildren().addAll(counterBox, scrollPane, h1);
    }

    public void refresh(){
        this.logTxt.setText(log);
        SpelModel model = controller.getModel();
        gameCounterTxt.setText(model.getGameCounter() + "");
        restartBtn.setDisable(!model.isSpelGedaan());
        sluitendBtn.setDisable(!model.isSpelGedaan());
    }

    public void log(String message){
        log = log + message + "\n";
        refresh();
    }

    public void clear(){
        log = "";
        refresh();
    }

}
