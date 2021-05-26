package controller;

import model.database.SpelersDB;
import model.observer.spelObserver;
import view.panels.*;
import model.*;
import java.util.Map;

public class GamblerViewController implements spelObserver {

    private GamblerOverviewPane gamblerOverviewPane;
    private SpelersDB spelersDB;

    public GamblerViewController(SpelersDB spelersDB){
        this.spelersDB = spelersDB;
        spelersDB.addObserver(this);
        gamblerOverviewPane = new GamblerOverviewPane(this);
    }



    public Map<String, Speler> getSpelers(){
        return spelersDB.getSpelers();
    }


    @Override
    public void update() {
        gamblerOverviewPane.refresh();
    }
}
