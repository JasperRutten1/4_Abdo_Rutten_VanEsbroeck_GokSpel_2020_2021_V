package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.gokStrategy.GokEnum;

public class WerpStateSaldoVeranderbaar implements SpelState {
    private SpelModel model;
    public WerpStateSaldoVeranderbaar(SpelModel model){this.model = model;}

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
        else if(inzet < model.getOrigineelInzet()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je kan je inzet niet verlagen.");
            alert.showAndWait();
        }
        else if(inzet > model.getOrigineelInzet() + 10){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inzet Error");
            alert.setHeaderText("Je kan je inzet enkel verhogen met 10");
            alert.showAndWait();
        }
        else{
            model.setInzet(inzet);
            model.setSpelState(model.getWerpVast());
        }
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
        model.gooiVolgendeDobbelsteen();
        if(model.getGokStrategy().kanWinnen(model.getWorpen())){
            model.setSpelState(model.getWerpVast());
        }
        else{
            model.eindSpel();
            model.setSpelState(model.getEindeSpelState());
        }
    }
}
