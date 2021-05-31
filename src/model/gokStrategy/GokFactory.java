package model.gokStrategy;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jordy
 */
public class GokFactory {
    public static GokStrategy getInstance(GokEnum gokEnum){
        try {
            return gokEnum.getClazz().getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
