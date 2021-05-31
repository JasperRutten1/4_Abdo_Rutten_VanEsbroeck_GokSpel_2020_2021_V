package view.panels;


import controller.adminPaneControllers.InstellingController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.database.SettingsDB;
import model.database.saveLoadStrategies.SaveLoadEnum;
import model.gokStrategy.GokEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iedereen
 */
public class InstellingPane extends VBox {
    private InstellingController instellingController;
    private SettingsDB settingsDB = SettingsDB.getInstance();

    private VBox v2;
    private VBox v3;

    private Button saveBtn;
    private List<RadioButton> saveOptions;
    private List<CheckBox> gokOptions;

    public InstellingPane(InstellingController instellingController){
        this.instellingController = instellingController;
        instellingController.setView(this);

        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(10);

        VBox v = new VBox(10);

        Label l = new Label("Formaat van het bestand: ");

        v.getChildren().addAll(l);

        generateSaveLoadOptions();
        generateGokOptions();

        //save button
        this.saveBtn = new Button("Save");
        this.saveBtn.setOnAction(e -> {
            settingsDB.save();
            System.out.println(settingsDB.getBeschikbareGokken());
        });
        this.getChildren().addAll(v,l,v2,v3,saveBtn);

    }

    private void generateSaveLoadOptions(){
        v2 = new VBox(10);
        v2.setPadding(new Insets(10, 10, 10, 10));
        v2.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
                )
        );
        ToggleGroup tg = new ToggleGroup();
        saveOptions = new ArrayList<>();
        for(SaveLoadEnum saveLoad : SaveLoadEnum.values()){
            RadioButton rdBtn = new RadioButton(saveLoad.getNaam());
            rdBtn.setToggleGroup(tg);
            rdBtn.setSelected(settingsDB.getSaveLoad() == saveLoad);
            rdBtn.setOnAction(e -> {
                settingsDB.setSaveLoad(saveLoad);
            });
            v2.getChildren().add(rdBtn);
        }
    }

    private void generateGokOptions(){
        v3 = new VBox(10);
        v3.setPadding(new Insets(10, 10, 10, 10));
        v3.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
                )
        );
        gokOptions = new ArrayList<>();
        for(GokEnum gok : GokEnum.values()){
            HBox gokBox = new HBox(10);

            //checkbox
            CheckBox checkBox = new CheckBox(gok.getNaam());
            checkBox.setSelected(settingsDB.getBeschikbareGokken().contains(gok));
            checkBox.setOnAction(e -> {
                if(settingsDB.getBeschikbareGokken().contains(gok)){
                    settingsDB.getBeschikbareGokken().remove(gok);
                }
                else{
                    settingsDB.getBeschikbareGokken().add(gok);
                }
            });
            checkBox.setMinWidth(200);

            //lable
            Label factorLbl = new Label("winstfactor: ");

            //field
            TextField factorFld = new TextField(SettingsDB.getInstance().getWinstFactors().get(gok) + "");
            factorFld.setMinWidth(100);
            factorFld.setOnAction(e -> {
                try{
                    SettingsDB.getInstance().getWinstFactors().put(gok, Double.parseDouble(factorFld.getText()));
                }
                catch (NumberFormatException ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Nummer Error");
                    alert.setHeaderText("ongeldig nummer");
                    alert.showAndWait();
                }
            });

            gokBox.getChildren().addAll(checkBox, factorLbl, factorFld);
            v3.getChildren().add(gokBox);
        }
    }
}
