package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.gokStrategy.GokEnum;
import model.gokStrategy.GokFactory;

public class SpelGestartState implements SpelState{
    private SpelModel model;

    public SpelGestartState(SpelModel model){
        this.model = model;
    }

    @Override
    public void onLogin(TextField naamFld) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Speler Error");
        alert.setHeaderText("Je bent al ingelogd");
        alert.showAndWait();
    }

    @Override
    public void onSaldoVerander(double inzet) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Saldo Error");
        alert.setHeaderText("Je kan je saldo niet meer veranderen");
        alert.showAndWait();
    }

    @Override
    public void onStart() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Het spel is al gestart");
        alert.showAndWait();
    }

    @Override
    public void onKiesGok(GokEnum gok) {
        System.out.println(gok);
        model.setGokStrategy(GokFactory.getInstance(gok));
    }

    @Override
    public void onBevestigGok() {
        if(model.getGokStrategy() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Spel Error");
            alert.setHeaderText("Je moet een Gok strategie kiezen om te bevestigen");
            alert.showAndWait();
        }
        else{
            model.setSpelState(model.getGokGekozenState());
            model.setStratGekozen(true);
        }
    }

    @Override
    public void onWerp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Je moet een Gok strategie bevestigen voor je kan werpen.");
        alert.showAndWait();
    }
}
