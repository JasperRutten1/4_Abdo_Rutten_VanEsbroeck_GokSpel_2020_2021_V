package controller;

import javafx.scene.paint.Color;
import model.SpelModel;
import model.observer.SpelEvent;
import view.GamblerView;

/**
 * @author
 */
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
            view.refreshLoginFld(m.getSpeler().getGebruiker(), m.isSpelBezig());
            view.refreshSaldoLbl(m.getSpeler().getSaldo(), true);
        });

        model.addObserver(SpelEvent.INZET_CHANGE, m -> {
            view.refreshInzetFld(m.getInzet(), m.isSpelBezig() && !(m.getWorpen().size() == 2));
        });

        model.addObserver(SpelEvent.START, m -> {
            view.refreshStartBtn(m.isSpelBezig());
            view.refreshGokStrats(model.isSpelBezig() && !model.isStratGekozen());
        });

        model.addObserver(SpelEvent.CONFIRM_GOK, m -> {
            view.refreshGokStrats(model.isSpelBezig() && !model.isStratGekozen());
        });

        model.addObserver(SpelEvent.RESTART, m -> {
            view.hideResult();
            view.refreshWorpenBox(m);
            view.refreshGokStrats(false);
            view.refreshLoginFld("", false);
            view.refreshInzetFld(0, false);
            view.refreshStartBtn(false);
        });

        model.addObserver(SpelEvent.SETTING_UPDATE, m -> {
            view.genereerGokPane(m);
        });

    }

    public SpelModel getModel() {
        return model;
    }

    public void setView(GamblerView view) {
        this.view = view;
    }

}
