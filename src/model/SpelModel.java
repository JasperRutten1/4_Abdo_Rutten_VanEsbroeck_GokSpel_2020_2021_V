package model;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import model.database.SettingsDB;
import model.database.SpelersDB;
import model.gokStrategy.GokStrategy;
import model.observer.SpelEvent;
import model.observer.SpelObserver;
import model.spelState.*;
import model.gokStrategy.GokEnum;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * @author iedereen
 */
public class SpelModel {
    private static SpelModel uniqueInstancce;

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
    @Getter @Setter private double origineelInzet;
    @Getter private int gameCounter;

    public SpelModel(SpelersDB spelersDB){
        this.spelersDB = spelersDB;
        this.worpen = new ArrayList<>();
        this.gameCounter = 1;

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

    public void eindSpel(){
        this.spelGedaan = true;

        //om afrondigsfouten te vermijden
        BigDecimal bdInzet = new BigDecimal(inzet, MathContext.DECIMAL64);
        BigDecimal bdSpelerSaldo = new BigDecimal(speler.getSaldo(), MathContext.DECIMAL64);
        BigDecimal bdGokEnumFactor = new BigDecimal(SettingsDB.getInstance().getWinstFactors().get(gokEnum), MathContext.DECIMAL64);
        BigDecimal total;
        double doubleTotal;


        if(gokStrategy.kanWinnen(this.worpen)){
            total = bdSpelerSaldo.add(bdInzet.multiply(bdGokEnumFactor));
            doubleTotal = total.doubleValue();
            speler.setSaldo(doubleTotal);
        }
        else{
            total = bdSpelerSaldo.subtract(bdInzet);
            doubleTotal = total.doubleValue();
            speler.setSaldo(doubleTotal);
        }
        notifyObservers(SpelEvent.SPEL_GEDAAN);
    }

    public void restart(){
        this.gokEnum = null;
        this.speler = null;
        this.inzet = 0;
        this.origineelInzet = 0;
        this.gokStrategy = null;
        this.spelBezig = false;
        this.spelGedaan = false;
        this.stratGekozen = false;
        this.worpen = new ArrayList<>();
        
        this.gameCounter++;

        this.spelState = this.nietIngelogdState;
        notifyObservers(SpelEvent.RESTART);
    }

    public static SpelModel getInstance() {
        if(uniqueInstancce == null) uniqueInstancce = new SpelModel(SpelersDB.getInstance());
        return uniqueInstancce;
    }
}
