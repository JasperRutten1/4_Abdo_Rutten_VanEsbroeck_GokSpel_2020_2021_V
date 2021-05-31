package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.gokStrategy.GokEnum;

/**
 * @author Jasper
 */
public class EindeSpelState implements SpelState{
    private SpelModel model;

    public EindeSpelState(SpelModel model){
        this.model = model;
    }

    @Override
    public void onLogin(TextField naamFld) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is gedaan, wacht tot het herstart");
        alert.showAndWait();
    }

    @Override
    public void onSaldoVerander(double inzet) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Je spel is gedaan, wacht tot het herstart.");
        alert.showAndWait();
    }

    @Override
    public void onStart() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is gedaan, wacht tot het herstart");
        alert.showAndWait();
    }

    @Override
    public void onKiesGok(GokEnum gok) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is gedaan, wacht tot het herstart");
        alert.showAndWait();
    }

    @Override
    public void onBevestigGok() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is gedaan, wacht tot het herstart");
        alert.showAndWait();
    }

    @Override
    public void onWerp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is gedaan, wacht tot het herstart");
        alert.showAndWait();
    }
}
