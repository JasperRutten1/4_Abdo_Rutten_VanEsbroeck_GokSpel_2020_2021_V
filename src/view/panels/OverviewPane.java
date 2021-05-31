package view.panels;

import controller.adminPaneControllers.OverViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.SpelModel;

public class OverviewPane extends VBox {
    private OverViewController controller;

    private Text gameCounterTxt;
    private String log;
    private Text logTxt;
    private Button resertBtn;

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

        //reset
        this.resertBtn = new Button("Restart");
        this.resertBtn.setDisable(true);
        this.resertBtn.setOnAction(e -> controller.getModel().restart());

        this.getChildren().addAll(counterBox, scrollPane, resertBtn);
    }

    public void refresh(){
        this.logTxt.setText(log);
        SpelModel model = controller.getModel();
        gameCounterTxt.setText(model.getGameCounter() + "");
        resertBtn.setDisable(!model.isSpelGedaan());
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
