package controller;

import javafx.scene.paint.Color;
import model.observer.SpelEvent;
import model.observer.SpelObserver;
import view.GamblerView;
import model.*;

public class GamblerViewController {
    private SpelModel model;

    private GamblerView view;

    public GamblerViewController(SpelModel model){
        this.model = model;

        model.addObserver(SpelEvent.WERP, m -> {
            view.refreshWorpenBox(m);
        });

        model.addObserver(SpelEvent.SPEL_GEDAAN, m ->{
            if(m.getGokStrategy().kanWinnen(m.getWorpen())){
                view.refreshResult("Je Hebt GEWONNEN!", Color.GREEN, m);
            }
            else{
                view.refreshResult("HELAAS, Je BENT VERLOREN", Color.RED, m);
            }
        });

        model.addObserver(SpelEvent.LOGIN, m -> {
            view.refreshLoginFld(m.getSpeler(), m.isSpelBezig());
            view.refreshSaldoLbl(m.getSpeler().getSaldo(), true);
        });

        model.addObserver(SpelEvent.INZET_CHANGE, m -> {
            view.refreshInzetFld(m.getInzet(), m.isSpelBezig() && !(m.getWorpen().size() == 2));
        });

        model.addObserver(SpelEvent.START, m -> {
            view.refreshStartBtn(m.isSpelBezig());
        });

        model.addObserver(SpelEvent.CONFIRM_GOK, m -> {
            view.refreshGokStrats(
                    model,
                    model.isSpelBezig() && !model.isStratGekozen()
            );
        });

    }

    public SpelModel getModel() {
        return model;
    }

    public void setView(GamblerView view) {
        this.view = view;
    }

}
