package model.database;

import model.database.saveLoadStrategies.SaveLoadEnum;
import model.gokStrategy.GokEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SettingsDB extends Properties {
    private SaveLoadEnum saveLoad;
    private Map<GokEnum, Double> winstFactors;
    private List<GokEnum> beschikbareGokken;

    public SettingsDB(){
        try{
            File file = new File("src/bestanden/instellingen/settings.properties");
            if(!file.exists()){
                file.createNewFile();
            }

            FileInputStream fis = new FileInputStream(file);
            load(fis);
            fis.close();

            loadSaveLoad();
            loadWinstFactors();
            loadBeschikbareGokken();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /*

     */

    public Map<GokEnum, Double> getWinstFactors() {
        return winstFactors;
    }

    public void save(){
        writeSaveLoad();
        writeWinstFactors();
        writeBeschikbareGokken();
        try{
            File file = new File("src/bestanden/instellingen/settings.properties");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            store(fos, "gok propperties");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void loadSaveLoad(){
        saveLoad = SaveLoadEnum.getEnumFromName(getProperty("saveLoad"));
        if(saveLoad == null) saveLoad = SaveLoadEnum.SPELER_TEKST;
    }

    private void loadWinstFactors(){
        /*
        property key => gokEnum naam + "_winst"
         */
        winstFactors = new HashMap<>();
        for(GokEnum gok : GokEnum.values()){
            String value_string = getProperty(gok.getNaam()  + "_winst");
            if(value_string == null){
                winstFactors.put(gok, 2.0);
            }
            else{
                double value;
                    try{
                        value = Double.parseDouble(value_string);
                    }
                    catch(NumberFormatException ex){
                        value = 2.0;
                    }
                    winstFactors.put(gok, value);
            }

        }
    }

    private void loadBeschikbareGokken(){
        /*
        property key => "beschikbareGokken"
        property value => naam,naam (, als separator)
         */
        beschikbareGokken = new ArrayList<>();
        String values = getProperty("beschikbareGokken");
        if(values == null){
            beschikbareGokken.addAll(Arrays.asList(GokEnum.values()));
        }

    }

    private void writeSaveLoad(){
        setProperty("saveLoad", saveLoad.getClassName());
    }

    private void writeWinstFactors(){
        for(Map.Entry<GokEnum, Double> entry : winstFactors.entrySet()){
            setProperty(entry.getKey().getNaam() + "_winst", entry.getValue() + "");
        }
    }


    private void writeBeschikbareGokken(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < beschikbareGokken.size() ; i++){
            GokEnum gok = beschikbareGokken.get(i);
            sb.append(gok.getNaam());
            if(i < beschikbareGokken.size() - 1){
                sb.append(",");
            }
        }
        setProperty("beschikbareGokken", sb.toString());
    }
}
