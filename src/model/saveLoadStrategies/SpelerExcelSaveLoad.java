package model.saveLoadStrategies;

import model.Speler;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SpelerExcelSaveLoad implements SaveLoadInterface{

    @Override
    public void save(Map<String, Speler> spelers, File file) {

    }

    @Override
    public HashMap<String, Speler> load(File file) {
        return null;
    }
}
