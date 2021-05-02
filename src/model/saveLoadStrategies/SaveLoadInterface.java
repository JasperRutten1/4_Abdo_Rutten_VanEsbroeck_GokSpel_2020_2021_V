package model.saveLoadStrategies;

import model.Speler;

import java.util.HashMap;
import java.util.Map;

public interface SaveLoadInterface {
    void save(Map<String, Speler> spelers);
    HashMap<String, Speler> load();
}
