package view.panels;


import controller.adminPaneControllers.InstellingController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.database.SettingsDB;

public class InstellingPane extends VBox {
    private InstellingController instellingController;
    private SettingsDB settingsDB;


    private Button saveBtn;

    public InstellingPane(InstellingController instellingController){
        this.instellingController = instellingController;
        instellingController.setView(this);

        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(10);

        VBox v = new VBox(10);

        Label l = new Label("Formaat van het bestand: ");

        v.getChildren().addAll(l);

        VBox v2 = new VBox(10);

        ToggleGroup tg = new ToggleGroup();
        RadioButton rdTekst = new RadioButton("Tekst");
        rdTekst.setToggleGroup(tg);
        rdTekst.setSelected(true);
        RadioButton rdExcel = new RadioButton("Excel");
        rdExcel.setToggleGroup(tg);
        v2.getChildren().addAll(rdTekst,rdExcel);

        //save
        this.saveBtn = new Button("Save");
        this.saveBtn.setOnAction(e -> {

           settingsDB.save();

        }
        );
        this.getChildren().addAll(v,l,v2,saveBtn);

    }
}
