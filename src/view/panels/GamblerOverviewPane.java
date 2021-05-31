package view.panels;


import controller.adminPaneControllers.GamblerOverViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.*;

/**
 * @author Iedereen
 */
public class GamblerOverviewPane extends GridPane{
	private TableView<Speler> table;
	private ObservableList<Speler> spelers;
	private GamblerOverViewController controller;
	
	
	public GamblerOverviewPane(GamblerOverViewController controller) {
		this.controller = controller;
		controller.setView(this);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("Spelers:"), 0, 0, 1, 1);

		table = new TableView<>();
		refresh();
		//gebruiks kolom
		TableColumn<Speler, String> gebruikerCol = new TableColumn<>("Gebruikers Naam");
		gebruikerCol.setMinWidth(100);
		gebruikerCol.setCellValueFactory(new PropertyValueFactory<>("gebruiker"));
		//naam kolom
		TableColumn<Speler, String> naamCol = new TableColumn<>("Naam");
		naamCol.setMinWidth(100);
		naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
		//voornaam kolom
		TableColumn<Speler, String> voornaamCol = new TableColumn<>("Voornaam");
		voornaamCol.setMinWidth(100);
		voornaamCol.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
		//saldo kolom
		TableColumn<Speler, Double> saldoCol = new TableColumn<>("Saldo");
		saldoCol.setMinWidth(70);
		saldoCol.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		table.getColumns().addAll(gebruikerCol, naamCol, voornaamCol, saldoCol);
		this.getChildren().addAll(table);
	}

	public void refresh() {
		spelers =  FXCollections.observableArrayList(controller.getModel().getSpelersDB().getSpelers().values());
		table.setItems(spelers);
		table.refresh();
	}
}
