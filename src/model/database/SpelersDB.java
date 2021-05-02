package model.database;

import model.Speler;
import model.saveLoadStrategies.SaveLoadInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iedereen
 */
public class SpelersDB {
    private Map<String, Speler> spelers;
    private SaveLoadInterface saveLoad;

    public SpelersDB(SaveLoadInterface saveLoad){
        this.spelers = new HashMap<>();
        this.saveLoad = saveLoad;
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

    public void save(){
        saveLoad.save(this.spelers);
    }

    public void load(){
        this.spelers = saveLoad.load();
    }
}
