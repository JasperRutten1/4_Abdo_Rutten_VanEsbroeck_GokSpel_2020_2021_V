package model.database.saveLoadStrategies;

import java.io.File;
import java.io.IOException;

public enum SaveLoadEnum {
    SPELER_TEKST(SpelerTekstSaveLoad.class, new File("src/bestanden/tekst/speler.txt")),
    SPELER_EXCEL(SpelerExcelSaveLoad.class, new File("src/bestanden/excel/speler.xls"));

    private final File file;
    private final Class<? extends SaveLoadStrategy> clazz;

    SaveLoadEnum(Class<? extends SaveLoadStrategy> clazz, File file){
        checkFile(file);
        this.clazz = clazz;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Class<? extends SaveLoadStrategy> getClazz() {
        return clazz;
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