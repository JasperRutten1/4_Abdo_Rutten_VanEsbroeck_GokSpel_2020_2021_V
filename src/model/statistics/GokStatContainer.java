package model.statistics;

import model.gokStrategy.GokEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jasper
 */
public class GokStatContainer {
    private Map<GokEnum, GokStatistic> stats;

    public GokStatContainer(){
        this.stats = new HashMap<>();
        for(GokEnum gokEnum : GokEnum.values()){
            stats.put(gokEnum, new GokStatistic(gokEnum));
        }
    }

    public Map<GokEnum, GokStatistic> getStats() {
        return stats;
    }

    public GokStatistic getStat(GokEnum gokEnum){
        return stats.get(gokEnum);
    }

    public void addStat(GokEnum gokEnum){

    }
}
