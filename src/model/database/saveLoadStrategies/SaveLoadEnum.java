package model.database.saveLoadStrategies;

import java.io.File;
import java.io.IOException;

/**
 * @author iedereen
 */
public enum SaveLoadEnum {
    SPELER_TEKST(
            SpelerTekstSaveLoad.class,
            new File("src/bestanden/tekst/speler.txt"),
            "Tekst (.txt"),
    SPELER_EXCEL(
            SpelerExcelSaveLoad.class,
            new File("src/bestanden/excel/speler.xls"),
            "excel (.xls)");

    private final File file;
    private final Class<? extends SaveLoadStrategy> clazz;
    private final String naam;

    SaveLoadEnum(Class<? extends SaveLoadStrategy> clazz, File file, String naam){
        checkFile(file);
        this.clazz = clazz;
        this.file = file;
        this.naam = naam;
    }

    public File getFile() {
        return file;
    }

    public Class<? extends SaveLoadStrategy> getClazz() {
        return clazz;
    }

    public String getNaam() {
        return naam;
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

    public String getClassName(){
        return clazz.getName();
    }

    public static SaveLoadEnum getEnumFromName(String clazzName){
        for(SaveLoadEnum saveLoad : values()){
            if(saveLoad.clazz.getName().equals(clazzName)) return saveLoad;
        }
        return null;
    }
}
