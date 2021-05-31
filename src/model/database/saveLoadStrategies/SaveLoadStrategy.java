package model.database.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.util.Map;

/**
 * @author iedereen
 */
public interface SaveLoadStrategy {
    void save(Map<String, Speler> data, File file);
    Map<String, Speler> load(File file);
}
