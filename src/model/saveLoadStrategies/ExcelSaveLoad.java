package model.saveLoadStrategies;

import model.Speler;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelSaveLoad implements SaveLoadInterface{
    private static File spelersFile = new File("src/bestanden/excel/speler.xls");

    @Override
    public void save(Map<String, Speler> spelers) {
        checkFile(spelersFile);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Blad1");
        int rowCount = 0;
        for(Speler speler : spelers.values()){
            Row row = sheet.createRow(rowCount);
            rowCount++;
            row.createCell(0).setCellValue(speler.getNaam());
            row.createCell(1).setCellValue(speler.getVoornaam());
            row.createCell(2).setCellValue(speler.getGebruiker());
            row.createCell(3).setCellValue(speler.getSaldo());
        }
        try {
            FileOutputStream out = new FileOutputStream(spelersFile);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Speler> load() {
        checkFile(spelersFile);
        HashMap<String, Speler> spelers = new HashMap<>();
        try {
            FileInputStream input = new FileInputStream(spelersFile);
            HSSFWorkbook workbook = new HSSFWorkbook(input);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            while(rows.hasNext()){
                HSSFRow row = (HSSFRow) rows.next();
                int first = row.getFirstCellNum();
                String naam = row.getCell(first).getStringCellValue();
                String voornaam = row.getCell(first + 1).getStringCellValue();
                String gebruiker = row.getCell(first + 2).getStringCellValue();
                double saldo = row.getCell(first + 3).getNumericCellValue();
                Speler speler = new Speler(naam, voornaam, gebruiker, saldo);
                spelers.put(speler.getGebruiker(), speler);
            }
            input.close();
            return spelers;
        } catch (IOException e) {
            e.printStackTrace();
            return spelers;
        }
    }

    private static void checkFile(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
