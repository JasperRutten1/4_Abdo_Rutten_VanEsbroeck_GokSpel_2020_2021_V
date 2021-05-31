package controller;

import controller.adminPaneControllers.GamblerOverViewController;
import controller.adminPaneControllers.OverViewController;
import controller.adminPaneControllers.StatisticsController;
import model.SpelModel;
import model.observer.SpelObserver;
import view.*;

public class AdminViewController {
    private SpelModel model;

    private AdminView adminView;

    private GamblerOverViewController gamblerOverViewController;
    private OverViewController overViewController;
    private StatisticsController statisticsController;


    public AdminViewController(SpelModel model){
        this.model = model;
        this.gamblerOverViewController = new GamblerOverViewController(model);
        this.overViewController = new OverViewController(model);
        this.statisticsController = new StatisticsController(model);
    }

    /*
    --------------------------------------------------
    getters
    --------------------------------------------------
     */

    public SpelModel getModel() {
        return model;
    }

    public GamblerOverViewController getGamblerOverController() {
        return gamblerOverViewController;
    }

    public OverViewController getOverViewController() {
        return overViewController;
    }

    public StatisticsController getStatisticsController() {
        return statisticsController;
    }

    // setter

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }
}
