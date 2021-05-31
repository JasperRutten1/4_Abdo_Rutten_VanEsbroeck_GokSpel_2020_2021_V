package model.database.saveLoadStrategies;

import java.lang.reflect.InvocationTargetException;

/**
 * @author iedereen
 */
public class SaveLoadFactory {
    public static SaveLoadStrategy getInstance(SaveLoadEnum saveLoadEnum){
        try {
            return saveLoadEnum.getClazz().getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
