package model.database.saveLoadStrategies;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Speler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iedereen
 */
public class SpelerExcelSaveLoad implements SaveLoadStrategy {

    @Override
    public void save(Map<String, Speler> spelers, File file) {
        ArrayList<ArrayList<String>> args = new ArrayList<>();
        for(Speler speler : spelers.values()){
            ArrayList<String> row = new ArrayList<>();
            row.add(speler.getNaam());
            row.add(speler.getVoornaam());
            row.add(speler.getGebruiker());
            row.add(speler.getSaldo() + "");
            args.add(row);
        }
        ExcelPlugin plugin = new ExcelPlugin();
        try{
            plugin.write(file, args);
        }
        catch (IOException | BiffException | WriteException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Speler> load(File file) {
        HashMap<String, Speler> spelers = new HashMap<>();
        ExcelPlugin plugin = new ExcelPlugin();
        try{
            ArrayList<ArrayList<String>> args = plugin.read(file);
            for(ArrayList<String> row : args){
                for(int i = 0 ; i < row.size() ; i++){
                    Speler speler = new Speler(row.get(0), row.get(1), row.get(2), Double.parseDouble(row.get(3)));
                    spelers.put(speler.getGebruiker(), speler);
                }
            }
        }
        catch (IOException | BiffException | NumberFormatException exception){
            exception.printStackTrace();
        }
        return spelers;
    }
}
