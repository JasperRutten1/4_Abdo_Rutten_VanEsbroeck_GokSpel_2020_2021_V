package controller;

import model.SpelModel;
import model.observer.SpelObserver;
import view.*;
import view.panels.GamblerOverviewPane;

public class AdminViewController implements SpelObserver {
    private SpelModel model;

    private AdminView adminView;
    private GamblerOverviewPane gamblerOverviewPane;


    public AdminViewController(SpelModel model){
        this.model = model;
        model.addObserver(this);
    }

    /*
    --------------------------------------------------
    getters
    --------------------------------------------------
     */

    public SpelModel getModel() {
        return model;
    }

    /*
    --------------------------------------------------
    setters
    --------------------------------------------------
     */

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void update(SpelModel spelModel) {

    }
}
