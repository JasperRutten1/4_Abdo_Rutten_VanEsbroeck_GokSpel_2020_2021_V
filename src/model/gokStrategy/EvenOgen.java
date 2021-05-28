package model.gokStrategy;

import java.util.List;

public class EvenOgen implements GokStrategy{
    @Override
    public boolean kanWinnen(List<Integer> dobbelstenen) {
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
