package view.panels;

import controller.adminPaneControllers.StatisticsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.statistics.GokStatistic;

/**
 * @author Jasper
 */
public class StatisticsPane extends GridPane {
    private StatisticsController controller;
    private ObservableList<GokStatistic> stats;
    private TableView<GokStatistic> table;

    public StatisticsPane(StatisticsController controller){
        this.controller = controller;
        controller.setView(this);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Gok strategie statestieken:"), 0, 0, 1, 1);

        table = new TableView<>();
        refresh();
        table.setMinWidth(500);
        //stat naam
        TableColumn<GokStatistic, String> naamCol = new TableColumn<>("Naam");
        naamCol.setMinWidth(100);
        naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
        //gekozen
        TableColumn<GokStatistic, String> gekozenCol = new TableColumn<>("Gekozen");
        gekozenCol.setMinWidth(50);
        gekozenCol.setCellValueFactory(new PropertyValueFactory<>("used"));
        //gewonnen
        TableColumn<GokStatistic, String> gewonnenCol = new TableColumn<>("Gewonnen");
        gewonnenCol.setMinWidth(50);
        gewonnenCol.setCellValueFactory(new PropertyValueFactory<>("won"));
        //inzet
        TableColumn<GokStatistic, Double> inzetCol = new TableColumn<>("Totaale inzet");
        inzetCol.setMinWidth(50);
        inzetCol.setCellValueFactory(new PropertyValueFactory<>("totalInzet"));
        //winst
        TableColumn<GokStatistic, Double> winstCol = new TableColumn<>("Totaale winst");
        winstCol.setMinWidth(50);
        winstCol.setCellValueFactory(new PropertyValueFactory<>("totalWinst"));

        table.getColumns().addAll(naamCol, gekozenCol, gewonnenCol, inzetCol, winstCol);
        this.getChildren().addAll(table);
    }

    public void refresh() {
        stats =  FXCollections.observableArrayList(controller.getGokStatContainer().getStats().values());
        table.setItems(stats);
        table.refresh();
    }
}
