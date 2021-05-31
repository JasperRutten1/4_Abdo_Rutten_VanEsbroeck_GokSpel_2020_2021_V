package controller;

import controller.adminPaneControllers.GamblerOverViewController;
import controller.adminPaneControllers.InstellingController;
import controller.adminPaneControllers.OverViewController;
import controller.adminPaneControllers.StatisticsController;
import model.SpelModel;
import view.*;

/**
 * @author iedereen
 */
public class AdminViewController {
    private SpelModel model;

    private AdminView adminView;

    private GamblerOverViewController gamblerOverViewController;
    private OverViewController overViewController;
    private StatisticsController statisticsController;
    private InstellingController instellingController;


    public AdminViewController(SpelModel model){
        this.model = model;
        this.gamblerOverViewController = new GamblerOverViewController(model);
        this.overViewController = new OverViewController(model);
        this.statisticsController = new StatisticsController(model);
        this.instellingController = new InstellingController(model);
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

    public InstellingController getInstellingController() {return instellingController;}
    // setter

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }
}
