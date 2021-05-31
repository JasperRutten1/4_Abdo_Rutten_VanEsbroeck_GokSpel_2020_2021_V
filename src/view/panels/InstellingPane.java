package view.panels;

import controller.InstellingController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class InstellingPane extends VBox {
    private InstellingController controller;

    public InstellingPane(InstellingController controller){
        this.controller = controller;
        controller.setView(this);

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
    }
}
