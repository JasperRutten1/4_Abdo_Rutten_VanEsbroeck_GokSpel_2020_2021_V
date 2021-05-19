package model.gokStrategy;

import java.util.ArrayList;

public class LaatsteWorpHogerDanVorige implements GokStrategy{
    @Override
    public boolean kanWinnen(ArrayList<Integer> dobbelstenen) {
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
