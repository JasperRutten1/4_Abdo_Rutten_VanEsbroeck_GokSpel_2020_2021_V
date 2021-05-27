package model;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import model.database.SpelersDB;
import model.gokStrategy.GokStrategy;
import model.observer.SpelObserver;
import model.spelState.IngelogdeState;
import model.spelState.NietIngelogdeState;
import model.spelState.SpelGestartState;
import model.spelState.SpelState;
import model.gokStrategy.GokEnum;

import java.util.ArrayList;
import java.util.List;

public class SpelModel {
    @Getter private SpelersDB spelersDB;
    private final List<SpelObserver> observers = new ArrayList<>();

    @Getter private Speler speler;
    @Getter private double inzet;
    @Getter private GokEnum gokEnum;
    @Getter private GokStrategy gokStrategy;
    @Getter boolean spelBezig;
    @Getter boolean stratGekozen;

    @Getter @Setter
    private SpelState spelState, nietIngelogdState, ingelogdState,
            spelGestartState, gokGekozenState, worp1State,
            worp2State, worp3State, worp4State;

    @Getter private List<Integer> worpen;

    public SpelModel(SpelersDB spelersDB){
        this.spelersDB = spelersDB;
        this.worpen = new ArrayList<>();
        this.spelBezig = false;

        this.nietIngelogdState = new NietIngelogdeState(this);
        this.ingelogdState = new IngelogdeState(this);
        this.spelGestartState = new SpelGestartState(this);


        this.spelState = this.nietIngelogdState;
    }

    /*
    --------------------------------------------------
    setters
    --------------------------------------------------
     */

    public void setSpeler(Speler speler) {
        this.speler = speler;
        notifyObservers();
    }

    public void setInzet(double inzet) {
        this.inzet = inzet;
        notifyObservers();
    }

    public void setSpelBezig(boolean spelBezig) {
        this.spelBezig = spelBezig;
        notifyObservers();
    }

    public void setGokEnum(GokEnum gokEnum) {
        this.gokEnum = gokEnum;
    }

    public void setGokStrategy(GokStrategy gokStrategy) {
        this.gokStrategy = gokStrategy;
        notifyObservers();
    }

    public void setStratGekozen(boolean stratGekozen) {
        this.stratGekozen = stratGekozen;
        notifyObservers();
    }

    /*
    --------------------------------------------------
    observable methods
    --------------------------------------------------
     */

    public void addObserver(SpelObserver obs){
        observers.add(obs);
    }

    public void removeObserver(SpelObserver obs){
        observers.remove(obs);
    }

    public void notifyObservers(){
        for (SpelObserver obs: observers)
            obs.update(this);
    }

    /*
    --------------------------------------------------
    state methods
    --------------------------------------------------
     */

    public void login(TextField naamFld){
        getSpelState().onLogin(naamFld);
    }

    public void veranderInzet(double inzet){
        getSpelState().onStart();
    }

    public void kiesGokStrategie(GokEnum gok){
        getSpelState().onKiesGok(gok);
    }

    public void werp(List<Integer> worpen){
        getSpelState().onWerp();
    }
}
