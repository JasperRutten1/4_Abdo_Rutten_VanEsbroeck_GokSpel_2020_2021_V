package model.gokStrategy;

import model.gokStrategy.GokEnum;
import model.gokStrategy.GokStrategy;

import java.lang.reflect.InvocationTargetException;

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
