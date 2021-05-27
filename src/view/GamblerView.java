package view;

import controller.GamblerViewController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SpelModel;
import model.Speler;

public class GamblerView {
	private Stage stage = new Stage();
	private GamblerViewController controller;

	private Pane loginPane;
	private Pane gokPane;
	private Pane werpPane;

	private HBox naamBox;
	private Label naamLbl;
	private TextField naamFld;
	private Label saldoLbl;

	private HBox inzetBox;
	private Label inzetLbl;
	private TextField inzetFld;

	private Button startBtn;
		
	public GamblerView(GamblerViewController controller){
		this.controller = controller;
		controller.setGamblerView(this);
		stage.setTitle("GAMBLER VIEW");


		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);

		genereerLoginPane(controller.getModel());
		gokPane = new HBox();
		werpPane = new HBox();
		root.getChildren().addAll(loginPane, gokPane, werpPane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	/*
	private building methods
	 */

	private void genereerLoginPane(SpelModel model){
		loginPane = new VBox(10);
		loginPane.setPadding(new Insets(0,0,0,20));

		//geef naam gedeelte
		naamBox = new HBox(5);
		naamLbl = new Label("Wat is je spelersnaam?");
		naamLbl.setPadding(new Insets(10,0,0,0));
		naamLbl.setMinWidth(175);
		naamFld = new TextField();
		naamFld.setOnAction(e -> {
			model.getSpelState().onLogin(naamFld);
		});
		saldoLbl = new Label();
		saldoLbl.setPadding(new Insets(10,0,0,0));
		naamBox.getChildren().addAll(naamLbl, naamFld, saldoLbl);
		loginPane.getChildren().add(naamBox);

		//kies saldo gedeelte
		inzetBox = new HBox(5);
		inzetLbl = new Label("Hoeveel wil je inzetten?");
		inzetLbl.setPadding(new Insets(10,0,0,0));
		inzetLbl.setMinWidth(175);
		inzetFld = new TextField("0.0");
		inzetFld.setOnAction(e -> {
			try{
				model.getSpelState().onSaldoVerander(Double.parseDouble(inzetFld.getText()));
			}
			catch (NumberFormatException ex){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Nummer Error");
				alert.setHeaderText("ongeldig nummer");
				alert.showAndWait();
			}
		});
		inzetBox.getChildren().addAll(inzetLbl, inzetFld);
		loginPane.getChildren().add(inzetBox);

		//start knop
		startBtn = new Button("Start gokspel");

		startBtn.setOnAction(e -> {
			model.getSpelState().onStart();
		});
		loginPane.getChildren().add(startBtn);


	}

	public void refresh(SpelModel model){
		Speler speler = model.getSpeler();
		naamFld.setDisable(speler != null);
		saldoLbl.setText(speler == null ? "" : "Je saldo is " + speler.getSaldo() + ".");
		inzetFld.setDisable(model.isBezig() && !(model.getWorpen().size() == 2));
	}


}
