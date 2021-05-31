package model.spelState;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.SpelModel;
import model.Speler;
import model.gokStrategy.GokEnum;

/**
 * @author Jasper
 */
public class NietIngelogdeState implements SpelState{
    private SpelModel model;

    public NietIngelogdeState(SpelModel model){
        this.model = model;
    }

    @Override
    public void onLogin(TextField naamFld) {
        String naam = naamFld.getText();
        if(naam == null || naam.isEmpty()) return;

        Speler speler = model.getSpelersDB().getSpelers().get(naam);
        System.out.println(speler);
        if(speler == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Speler Error");
            alert.setHeaderText("Spelernaam bestaat niet");
            alert.showAndWait();
        }
        else{
            model.setSpeler(speler);
            model.setSpelState(model.getIngelogdState());
        }
    }

    @Override
    public void onSaldoVerander(double inzet) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Speler Error");
        alert.setHeaderText("Je moet eerst inloggen voor je de inzet kan veranderen");
        alert.showAndWait();
    }

    @Override
    public void onStart() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Speler Error");
        alert.setHeaderText("Je moet eerst inloggen voor je kan beginnen");
        alert.showAndWait();
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
