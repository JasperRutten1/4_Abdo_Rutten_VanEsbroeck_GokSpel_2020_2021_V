package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.gokStrategy.GokEnum;

public class IngelogdeState implements SpelState{
    private SpelModel model;

    public IngelogdeState(SpelModel model){
        this.model = model;
    }

    @Override
    public void onLogin(TextField naamFld) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Speler Error");
        alert.setHeaderText("Je bent al ingelogd.");
        alert.showAndWait();
    }

    @Override
    public void onSaldoVerander(double inzet) {
        String inzetTxt = Double.toString(Math.abs(inzet));
        int integerPlaces = inzetTxt.indexOf('.');
        int decimalPlaces = inzetTxt.length() - integerPlaces - 1;
        if(inzet <= 0.0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je moet een strikt positief getal geven.");
            alert.showAndWait();
        }
        else if(decimalPlaces>2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je moet een eurowaarde ingeven met MAX 2 na de komma");
            alert.showAndWait();
        }
        else if(inzet > model.getSpeler().getSaldo()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je kan niet meer inzetten dan je hebt.");
            alert.showAndWait();
        }
        else{
            model.setInzet(inzet);
        }
    }

    @Override
    public void onStart() {
        if(model.getInzet() > 0){
            model.setSpelState(model.getSpelGestartState());
            model.setSpelBezig(true);
            model.setOrigineelInzet(model.getInzet());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je moet een saldo inzetten voor je kan beginnen");
            alert.showAndWait();
        }
    }

    @Override
    public void onKiesGok(GokEnum gok) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Je moet eerst een spel beginnen voor je een gok kan kiezen");
        alert.showAndWait();
    }

    @Override
    public void onBevestigGok() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Je moet eerst een spel beginnen voor je een gok kan bevestigen");
        alert.showAndWait();
    }

    @Override
    public void onWerp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Spel Error");
        alert.setHeaderText("Je moet eerst een gok strategie kiezen voor je kan beginnen met werpen");
        alert.showAndWait();
    }
}
