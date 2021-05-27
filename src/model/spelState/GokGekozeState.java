package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.gokStrategy.GokEnum;

public class GokGekozeState implements SpelState{
    private SpelModel model;
    public GokGekozeState(SpelModel model){this.model = model;}

    @Override
    public void onLogin(TextField naamFld) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Speler Error");
        alert.setHeaderText("Je bent al ingelogd.");
        alert.showAndWait();
    }

    @Override
    public void onSaldoVerander(double inzet) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Saldo Error");
        alert.setHeaderText("Saldo kan op dit moment niet gewijzigd worden");
        alert.showAndWait();
    }

    @Override
    public void onStart() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Start Error");
        alert.setHeaderText("Je bent al gestart.");
        alert.showAndWait();
    }

    @Override
    public void onKiesGok(GokEnum gok) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gok Error");
        alert.setHeaderText("Strategie kan niet meer worden geselecteerd");
        alert.showAndWait();
    }

    @Override
    public void onBevestigGok() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gok Error");
        alert.setHeaderText("Strategie kan niet meer worden vastgelegd");
        alert.showAndWait();
    }

    @Override
    public void onWerp() {

    }
}
