package view.panels;


import controller.AdminViewController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.database.SpelersDB;
import view.panels.GamblerOverviewPane;

public class AdminMainPane extends BorderPane {

	public AdminMainPane(AdminViewController controller){
	    TabPane tabPane = new TabPane();
	    OverviewPane overviewPane = new OverviewPane(controller.getOverViewController());
        Tab spelVerloopTab = new Tab("Spelverloop", overviewPane);

        GamblerOverviewPane gamblerOverviewPane = new GamblerOverviewPane(controller.getGamblerOverController());
        Tab spelerTab = new Tab("Spelers",gamblerOverviewPane);

        InstellingPane instellingPane = new InstellingPane(controller.getInstellingController());
        Tab instellingTab = new Tab("Instellingen", instellingPane);

        StatisticsPane statisticsPane = new StatisticsPane(controller.getStatisticsController());
        Tab statistiekTab = new Tab("Statistieken", statisticsPane);

        tabPane.getTabs().add(spelVerloopTab);
        tabPane.getTabs().add(spelerTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}
}
