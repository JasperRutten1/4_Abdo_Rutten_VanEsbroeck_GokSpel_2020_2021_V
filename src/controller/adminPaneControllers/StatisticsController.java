package controller.adminPaneControllers;

import model.SpelModel;
import model.database.SettingsDB;
import model.gokStrategy.GokEnum;
import model.observer.SpelEvent;
import model.statistics.GokStatContainer;
import model.statistics.GokStatistic;
import view.panels.StatisticsPane;

/**
 * @author Jasper
 */
public class StatisticsController {
    private SpelModel model;
    private GokStatContainer gokStatContainer;

    private StatisticsPane view;

    public StatisticsController(SpelModel model){
        this.model = model;
        this.gokStatContainer = new GokStatContainer();

        model.addObserver(SpelEvent.CONFIRM_GOK, m -> {
            GokEnum gokEnum = m.getGokEnum();
            gokStatContainer.getStat(gokEnum).addUsed();
            view.refresh();
        });

        model.addObserver(SpelEvent.SPEL_GEDAAN, m -> {
            GokEnum gokEnum = m.getGokEnum();
            GokStatistic gokStatistic = gokStatContainer.getStat(gokEnum);
            gokStatistic.addTotalInzet(m.getInzet());
            if(m.getGokStrategy().kanWinnen(m.getWorpen())){
                gokStatistic.addWon();
                gokStatistic.addTotalWinst(m.getInzet() * SettingsDB.getInstance().getWinstFactors().get(gokEnum));
            }
            view.refresh();
        });
    }

    public SpelModel getModel() {
        return model;
    }

    public GokStatContainer getGokStatContainer() {
        return gokStatContainer;
    }

    public void setView(StatisticsPane view) {
        this.view = view;
    }
}
