package model.database;

import model.Speler;
import model.database.saveLoadStrategies.SaveLoadEnum;
import model.database.saveLoadStrategies.SaveLoadFactory;
import model.database.saveLoadStrategies.SaveLoadInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iedereen
 */
public class SpelersDB {
    private Map<String, Speler> spelers;
    private SaveLoadInterface saveLoad;
    private File file;

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
}
