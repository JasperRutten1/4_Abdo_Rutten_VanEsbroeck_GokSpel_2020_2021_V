package controller;

import model.observer.SpelObserver;
import view.GamblerView;
import model.*;

public class GamblerViewController implements SpelObserver {
    private SpelModel model;

    private GamblerView gamblerView;

    public GamblerViewController(SpelModel model){
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

    public void setGamblerView(GamblerView gamblerView) {
        this.gamblerView = gamblerView;
    }

    @Override
    public void update(SpelModel model) {
        gamblerView.refresh(model);
    }
}
