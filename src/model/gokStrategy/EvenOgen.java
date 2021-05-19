package model.gokStrategy;

import java.util.ArrayList;

public class EvenOgen implements GokStrategy{
    @Override
    public boolean kanWinnen(ArrayList<Integer> dobbelstenen) {
       for(Integer x : dobbelstenen){
           if(x != 0){
               if (x % 2 != 0){
                   return false;
               }
           }
       }
       return true;
    }
}
