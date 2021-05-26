package model.database;

import model.*;
import model.database.saveLoadStrategies.SaveLoadEnum;
import model.database.saveLoadStrategies.SaveLoadFactory;
import model.database.saveLoadStrategies.SaveLoadStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.observer.*;
/**
 * @author iedereen
 */
public class SpelersDB {
    private Map<String, Speler> spelers;
    private SaveLoadStrategy saveLoad;
    private File file;

    private Collection<spelObserver> observers = new ArrayList<>();

    public SpelersDB(SaveLoadEnum saveLoadEnum){
        this.spelers = new HashMap<>();
        this.saveLoad = SaveLoadFactory.getInstance(saveLoadEnum);
        this.file = saveLoadEnum.getFile();
    }

    public Map<String, Speler> getSpelers() {
        return spelers;
    }

    public void addSpeler(Speler speler){
        if(spelers.containsKey(speler.getGebruiker())) throw new IllegalArgumentException("gebruikers naam moet uniek zijn");
        spelers.put(speler.getGebruiker(), speler);
    }

    public void removeSpeler(Speler speler){
        spelers.remove(speler.getGebruiker());
    }

    public void save(File file){
        saveLoad.save(this.spelers, file);
    }

    public void load(){
        this.spelers = saveLoad.load(file);
    }

    public void addObserver(spelObserver obs){
        observers.add(obs);
    }

    public void notifyObservers(){
        for (spelObserver obs: observers)
             obs.update();
        }
    }
