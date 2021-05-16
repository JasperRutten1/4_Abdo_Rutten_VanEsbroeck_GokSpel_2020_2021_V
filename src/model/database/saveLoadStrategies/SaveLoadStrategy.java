package model.database.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.util.Map;

public interface SaveLoadStrategy {
    void save(Map<String, Speler> data, File file);
    Map<String, Speler> load(File file);
}
