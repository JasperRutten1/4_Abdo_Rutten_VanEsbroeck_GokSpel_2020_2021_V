package model.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpelerTekstSaveLoad extends TeksSaveLoadTemplate<String,Speler> implements SaveLoadInterface{
    @Override
    protected Speler generateValue(String[] args) {
        return null;
    }

    @Override
    protected String generateKey(Speler value) {
        return null;
    }

    @Override
    protected String generateDataString(Speler value) {
        return null;
    }
}
