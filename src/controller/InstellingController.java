package controller;

import model.SpelModel;
import model.database.SpelersDB;
import model.observer.SpelEvent;
import model.observer.SpelObserver;
import view.panels.InstellingPane;

import java.io.File;

public class InstellingController implements SpelObserver {


    private SpelersDB spelersDB;

    private InstellingPane view;
    private SpelModel model;

    public InstellingController(SpelModel model){
        this.model = model;
        model.addObserver(
                SpelEvent.START,
                m -> view.log("Spel Gestart")

        );
    }


    @Override
    public void update(SpelModel spelModel) {

    }

    public SpelModel getModel() {
        return model;
    }

    public void setView(InstellingPane view) {
        this.view = view;
    }


    public void loadSettings(String format){
        this.spelersDB.load(format);
    }
}
