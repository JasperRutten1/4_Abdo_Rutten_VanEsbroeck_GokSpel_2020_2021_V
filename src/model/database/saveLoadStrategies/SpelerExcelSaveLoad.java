package model.database.saveLoadStrategies;

import model.Speler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SpelerExcelSaveLoad implements SaveLoadStrategy {

    @Override
    public void save(Map<String, Speler> spelers, File file) {

    }

    @Override
    public HashMap<String, Speler> load(File file) {
        return null;
    }
}
