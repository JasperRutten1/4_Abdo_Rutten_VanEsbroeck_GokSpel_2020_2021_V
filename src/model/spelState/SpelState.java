package model.spelState;

import javafx.scene.control.TextField;
import model.gokStrategy.GokEnum;

/**
 * @author Jasper
 */
public interface SpelState {
    void onLogin(TextField naamFld);
    void onSaldoVerander(double inzet);
    void onStart();
    void onKiesGok(GokEnum gok);
    void onBevestigGok();
    void onWerp();
}
