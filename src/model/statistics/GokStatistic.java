package model.statistics;

import model.gokStrategy.GokEnum;

/**
 * @author Jasper
 */
public class GokStatistic {
    private String naam;
    private int used, won;
    private double totalInzet, totalWinst;

    public GokStatistic(GokEnum gokEnum){
        this.naam = gokEnum.getNaam();
        this.used = 0;
        this.won = 0;
        this.totalInzet = 0;
        this.totalWinst = 0;
    }

    /*
    --------------------------------------------------
    getters
    --------------------------------------------------
     */

    public double getTotalInzet() {
        return totalInzet;
    }

    public double getTotalWinst() {
        return totalWinst;
    }

    public int getUsed() {
        return used;
    }

    public int getWon() {
        return won;
    }

    public String getNaam() {
        return naam;
    }

    /*
    add methods
     */

    public void addTotalInzet(double inzet){
        this.totalInzet += inzet;
    }

    public void addTotalWinst(double winst){
        this.totalWinst += winst;
    }

    public void addUsed(){
        this.used++;
    }

    public void addWon(){
        this.won++;
    }
}
