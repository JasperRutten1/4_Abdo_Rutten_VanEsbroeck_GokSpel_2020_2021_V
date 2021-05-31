package model.gokStrategy;

import java.util.List;

/**
 * @author Jordy
 */
public class LaatsteWorpHogerDanVorige implements GokStrategy{
    @Override
    public boolean kanWinnen(List<Integer> dobbelstenen) {
        boolean uit = true;
        int x = 0;
        for(Integer y : dobbelstenen){
            if(y<x && y != 0){
                return false;
            }
            x = y;
        }
        return true;
    }
}
