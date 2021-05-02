package model.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextSaveLoad implements SaveLoadInterface{
    private static File spelersFile = new File("src/bestanden/tekst/speler.txt");

    @Override
    public void save(Map<String, Speler> spelers) {
        checkFile(spelersFile);
        StringBuilder sb = new StringBuilder();
        for(Speler speler : spelers.values()){
            sb.append(speler.getNaam()).append(",").append(speler.getVoornaam()).append(",")
                    .append(speler.getGebruiker()).append(",").append(speler.getSaldo()).append("\n");
        }
        try {
            FileWriter writer = new FileWriter(spelersFile);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Speler> load() {
        checkFile(spelersFile);
        HashMap<String, Speler> spelers = new HashMap<>();
        try {
            Scanner reader = new Scanner(spelersFile);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] data = line.split(",");
                Speler speler = new Speler(data[0], data[1], data[2], Double.parseDouble(data[3]));
                spelers.put(speler.getGebruiker(), speler);
            }
            reader.close();
            return spelers;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return spelers;
        }
    }

    private static void checkFile(File file){
        System.out.println(file.getAbsolutePath());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
