package model.gokStrategy;

import java.util.ArrayList;

public class Som21 implements GokStrategy{
    @Override
    public boolean kanWinnen(ArrayList<Integer> dobbelstenen) {
        int sum = 0;
        int worpenTeGaan = 0;
        for(Integer x : dobbelstenen){
            if( x ==0 ){
                worpenTeGaan++;
            }
        }
        for(Integer x : dobbelstenen){
            sum =sum + x;
            if(sum+worpenTeGaan>21){
                return false;
            }
            if(sum + worpenTeGaan*6 <21){
                return false;
            }
        }
        return true;
    }
}
