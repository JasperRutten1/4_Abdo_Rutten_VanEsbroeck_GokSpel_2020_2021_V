package controller.adminPaneControllers;

import model.SpelModel;
import model.observer.SpelEvent;
import view.panels.GamblerOverviewPane;

public class GamblerOverViewController {
    private SpelModel model;

    private GamblerOverviewPane view;

    public GamblerOverViewController(SpelModel model){
        this.model = model;
        model.addObserver(SpelEvent.SPEL_GEDAAN, m -> view.refresh());
    }

    public SpelModel getModel() {
        return model;
    }

    public void setView(GamblerOverviewPane view) {
        this.view = view;
    }
}
