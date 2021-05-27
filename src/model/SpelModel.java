package model;

import javafx.scene.control.TextField;
import model.database.SpelersDB;
import model.gokStrategy.GokStrategy;
import model.observer.SpelObserver;
import model.spelState.IngelogdeState;
import model.spelState.NietIngelogdeState;
import model.spelState.SpelState;
import model.gokStrategy.GokEnum;

import java.util.ArrayList;
import java.util.List;

public class SpelModel {
    private SpelersDB spelersDB;
    private final List<SpelObserver> observers = new ArrayList<>();

    private Speler speler;
    private double inzet;
    private GokStrategy gokStrategy;
    private boolean isBezig;

    private SpelState spelState, nietIngelogdState, ingelogdState,
            spelGestartState, gokGekozenState, worp1State,
            worp2State, worp3State, worp4State;

    private List<Integer> worpen;

    public SpelModel(SpelersDB spelersDB){
        this.spelersDB = spelersDB;
        this.worpen = new ArrayList<>();
        this.isBezig = false;

        this.nietIngelogdState = new NietIngelogdeState(this);
        this.ingelogdState = new IngelogdeState(this);


        this.spelState = this.nietIngelogdState;
    }


    /*
    --------------------------------------------------
    getters
    --------------------------------------------------
     */

    public SpelState getGokGekozenState() {
        return gokGekozenState;
    }

    public SpelState getIngelogdState() {
        return ingelogdState;
    }

    public SpelState getNietIngelogdState() {
        return nietIngelogdState;
    }

    public SpelState getSpelGestartState() {
        return spelGestartState;
    }

    public SpelState getWorp1State() {
        return worp1State;
    }

    public SpelState getWorp2State() {
        return worp2State;
    }

    public SpelState getWorp3State() {
        return worp3State;
    }

    public SpelState getWorp4State() {
        return worp4State;
    }

    public SpelState getSpelState() {
        return spelState;
    }

    public SpelersDB getSpelersDB() {
        return spelersDB;
    }

    public List<Integer> getWorpen() {
        return worpen;
    }

    public Speler getSpeler() {
        return speler;
    }

    public double getInzet() {
        return inzet;
    }

    public boolean isBezig() {
        return isBezig;
    }

    /*
    --------------------------------------------------
    setters
    --------------------------------------------------
     */

    public void setSpelState(SpelState spelState) {
        this.spelState = spelState;
    }

    public void setNietIngelogdState(SpelState nietIngelogdState) {
        this.nietIngelogdState = nietIngelogdState;
    }

    public void setIngelogdState(SpelState ingelogdState) {
        this.ingelogdState = ingelogdState;
    }

    public void setGokGekozenState(SpelState gokGekozenState) {
        this.gokGekozenState = gokGekozenState;
    }

    public void setSpelGestartState(SpelState spelGestartState) {
        this.spelGestartState = spelGestartState;
    }

    public void setWorp1State(SpelState worp1State) {
        this.worp1State = worp1State;
    }

    public void setWorp2State(SpelState worp2State) {
        this.worp2State = worp2State;
    }

    public void setWorp3State(SpelState worp3State) {
        this.worp3State = worp3State;
    }

    public void setWorp4State(SpelState worp4State) {
        this.worp4State = worp4State;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
        notifyObservers();
    }

    public void setInzet(double inzet) {
        this.inzet = inzet;
        notifyObservers();
    }

    public void setBezig(boolean bezig) {
        isBezig = bezig;
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
        getSpelState().onWerp(worpen);
    }
}
