package model.saveLoadStrategies;

import model.Speler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class TeksSaveLoadTemplate<K,V> {

    /*
    load
     */

    public final Map<K,V> load(File file){
        Map<K, V> data = new HashMap<>();
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] args = line.split(",");
                V value = generateValue(args);
                K key = generateKey(value);
                data.put(key, value);
            }
            reader.close();
            return data;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return data;
        }
    }

    protected abstract V generateValue(String[] args);

    protected abstract K generateKey(V value);

    /*
    save
     */

    public final void save(Map<K, V> data, File file){
        StringBuilder sb = new StringBuilder();
        List<V> values = new ArrayList<>(data.values());
        for(int i = 0 ; i < values.size() ; i++){
            V value = values.get(i);
            sb.append(generateDataString(value));
            if(i < values.size() - 1) sb.append("\n");
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String generateDataString(V value);
}
