package model;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import model.database.SpelersDB;
import model.gokStrategy.GokStrategy;
import model.observer.SpelEvent;
import model.observer.SpelObserver;
import model.spelState.*;
import model.gokStrategy.GokEnum;

import java.util.*;

public class SpelModel {
    @Getter private SpelersDB spelersDB;
    private final Map<SpelEvent, List<SpelObserver>> observers;

    @Getter private Speler speler;
    @Getter private double inzet;
    @Getter private GokEnum gokEnum;
    @Getter private GokStrategy gokStrategy;
    @Getter boolean spelBezig, stratGekozen, spelGedaan;

    @Getter @Setter
    private SpelState spelState, nietIngelogdState, ingelogdState,
            spelGestartState, werpVast,
            werpVerander, eindeSpelState;

    @Getter private List<Integer> worpen;
    @Getter @Setter double origineelInzet;

    public SpelModel(SpelersDB spelersDB){
        this.spelersDB = spelersDB;
        this.worpen = new ArrayList<>();
        this.spelBezig = false;

        observers = new HashMap<>();
        for(SpelEvent event : SpelEvent.values()){
            observers.put(event, new ArrayList<>());
        }

        this.nietIngelogdState = new NietIngelogdeState(this);
        this.ingelogdState = new IngelogdeState(this);
        this.spelGestartState = new SpelGestartState(this);
        this.werpVast = new WerpStateVastSaldo(this);
        this.werpVerander = new WerpStateSaldoVeranderbaar(this);
        this.eindeSpelState = new EindeSpelState(this);

        this.spelState = this.nietIngelogdState;
    }

    /*
    --------------------------------------------------
    setters
    --------------------------------------------------
     */

    public void setSpeler(Speler speler) {
        this.speler = speler;
        notifyObservers(SpelEvent.LOGIN);
    }

    public void setInzet(double inzet) {
        this.inzet = inzet;
        notifyObservers(SpelEvent.INZET_CHANGE);
    }

    public void setSpelBezig(boolean spelBezig) {
        this.spelBezig = spelBezig;
        notifyObservers(SpelEvent.START);
    }

    public void setGokEnum(GokEnum gokEnum) {
        this.gokEnum = gokEnum;
    }

    public void setGokStrategy(GokStrategy gokStrategy) {
        this.gokStrategy = gokStrategy;
        notifyObservers(SpelEvent.SELECT_GOK);
    }

    public void setStratGekozen(boolean stratGekozen) {
        this.stratGekozen = stratGekozen;
        notifyObservers(SpelEvent.CONFIRM_GOK);
    }

    public void setSpelGedaan(boolean spelGedaan) {
        this.spelGedaan = spelGedaan;
        notifyObservers(SpelEvent.SPEL_GEDAAN);
    }

    /*
    --------------------------------------------------
    observable methods
    --------------------------------------------------
     */

    public void addObserver(SpelEvent event, SpelObserver obs){
        observers.get(event).add(obs);
    }

    public void removeObserver(SpelEvent event, SpelObserver obs){
        observers.get(event).remove(obs);
    }

    public void notifyObservers(SpelEvent event){
        for (SpelObserver obs: observers.get(event)) obs.update(this);
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

    /*
    --------------------------------------------------
    util methods
    --------------------------------------------------
     */

    public void gooiVolgendeDobbelsteen(){
        worpen.add(new Random().nextInt(6) + 1);
        notifyObservers(SpelEvent.WERP);
    }


}
