package model.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface SaveLoadInterface {
    void save(Map<String, Speler> data, File file);
    Map<String, Speler> load(File file);
}
