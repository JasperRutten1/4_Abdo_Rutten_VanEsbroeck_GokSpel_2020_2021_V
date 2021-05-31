package model.database;

import model.Speler;
import model.database.saveLoadStrategies.SaveLoadEnum;
import model.database.saveLoadStrategies.SaveLoadFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * @author iedereen
 */
public class SpelersDB {
    private static SpelersDB uniqueInstance;

    private Map<String, Speler> spelers;

    public SpelersDB() {
        this.spelers = new HashMap<>();
    }

    public Map<String, Speler> getSpelers() {
        return spelers;
    }

    public void addSpeler(Speler speler) {
        if (spelers.containsKey(speler.getGebruiker()))
            throw new IllegalArgumentException("gebruikers naam moet uniek zijn");
        spelers.put(speler.getGebruiker(), speler);
    }

    public void removeSpeler(Speler speler) {
        spelers.remove(speler.getGebruiker());
    }

    public void save() {
        SaveLoadEnum saveLoadEnum = SettingsDB.getInstance().getSaveLoad();
        SaveLoadFactory.getInstance(saveLoadEnum).save(this.spelers, saveLoadEnum.getFile());
    }

    public void load() {
        SaveLoadEnum saveLoadEnum = SettingsDB.getInstance().getSaveLoad();
        this.spelers = SaveLoadFactory.getInstance(saveLoadEnum).load(saveLoadEnum.getFile());
    }

    public static SpelersDB getInstance() {
        if(uniqueInstance == null) uniqueInstance = new SpelersDB();
        return uniqueInstance;
    }
}