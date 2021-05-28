package model.gokStrategy;

import java.util.List;

public class Som21 implements GokStrategy{
    final static int NBR_WORPEN = 4;

    @Override
    public boolean kanWinnen(List<Integer> dobbelstenen) {
        int sum = 0;
        for(Integer x : dobbelstenen) sum += x;

        int max_pot_sum = sum + (NBR_WORPEN - dobbelstenen.size()) * 6;
        int min_pot_sum = sum + (NBR_WORPEN - dobbelstenen.size()) * 1;

        if(max_pot_sum < 21) return false;
        if(min_pot_sum > 21) return false;
        return true;
    }
}
