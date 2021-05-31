package controller.adminPaneControllers;

import model.SpelModel;
import model.Speler;
import model.observer.SpelEvent;
import view.panels.OverviewPane;

/**
 * @author Jordy
 */
public class OverViewController {
    private SpelModel model;

    private OverviewPane view;

    public OverViewController(SpelModel model){
        this.model = model;

        this.model.addObserver(
                SpelEvent.LOGIN,
                m -> {
                    Speler speler = m.getSpeler();
                    view.log("Speler ingelogd als: " + speler.getVoornaam() + " "
                            + speler.getNaam() + " ~ " + speler.getGebruiker());
                }
        );

        this.model.addObserver(
                SpelEvent.INZET_CHANGE,
                m -> view.log("Inzet veranderd naar " + m.getInzet())
        );

        this.model.addObserver(
                SpelEvent.START,
                m -> view.log("Spel Gestart!")
        );

        this.model.addObserver(
                SpelEvent.SELECT_GOK,
                m -> view.log("Gok strategie geselecteerd: " + m.getGokEnum().getOmschrijving())
        );

        this.model.addObserver(
                SpelEvent.CONFIRM_GOK,
                m -> view.log("Gok strategie Bevestigd: " + model.getGokEnum().getOmschrijving())
        );

        this.model.addObserver(
                SpelEvent.WERP,
                m -> {
                    int worp = m.getWorpen().get(m.getWorpen().size() - 1);
                    view.log("Dobbelsteen gegooit: " + worp);
                }
        );

        this.model.addObserver(
                SpelEvent.SPEL_GEDAAN,
                m -> {
                    boolean gewonnen = m.getGokStrategy().kanWinnen(m.getWorpen());
                    view.log("Spel gedaan. " + (gewonnen ? "Gewonnen" : "Verloren"));
                }
        );

        this.model.addObserver(
                SpelEvent.RESTART,
                m -> view.clear()
        );
    }

    public SpelModel getModel() {
        return model;
    }

    public void setView(OverviewPane view) {
        this.view = view;
    }
}
