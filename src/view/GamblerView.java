package view;

import controller.GamblerViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SpelModel;
import model.Speler;
import model.gokStrategy.GokEnum;

import java.util.ArrayList;
import java.util.List;

public class GamblerView {
	private Stage stage = new Stage();
	private GamblerViewController controller;

	/*
	--------------------------------------------------
	panes
	--------------------------------------------------
	 */

	private Pane loginPane;
	private Pane gokPane;
	private Pane werpPane;

	/*
	--------------------------------------------------
	login pane elements
	--------------------------------------------------
	 */

	//naam box elements
	private HBox naamBox;
	private Label naamLbl;
	private TextField naamFld;
	private Label saldoLbl;

	//inzet box elements
	private HBox inzetBox;
	private Label inzetLbl;
	private TextField inzetFld;

	//start button element
	private Button startBtn;

	/*
	--------------------------------------------------
	gok pane elements
	--------------------------------------------------
	 */

	//title
	private Label gokStratLbl;

	//gok strategy elements
	private ToggleGroup gokStratGrp;
	private List<RadioButton> gokStratRdbs;

	//bevestig button
	private Button gokBevestigBtn;

	/*
	--------------------------------------------------
	werp pane elements
	--------------------------------------------------
	 */

	private Button werpBtn;
	private HBox werpBox;
	private VBox worpenBox;
	private VBox commentBox;
		
	public GamblerView(GamblerViewController controller){
		this.controller = controller;
		controller.setGamblerView(this);
		stage.setTitle("GAMBLER VIEW");


		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);

		SpelModel model = controller.getModel();

		genereerLoginPane(model);
		genereerGokPane(model);
		genereerWerpPane(model);

		root.getChildren().addAll(loginPane, gokPane, werpPane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	/*
	--------------------------------------------------
	private building methods
	--------------------------------------------------
	 */

	private void genereerLoginPane(SpelModel model){
		loginPane = new VBox(10);
		loginPane.setPadding(new Insets(0,0,0,20));

		//geef naam
		naamBox = new HBox(5);
		naamLbl = new Label("Wat is je spelersnaam?");
		naamLbl.setPadding(new Insets(10,0,0,0));
		naamLbl.setMinWidth(175);
		naamFld = new TextField();
		naamFld.setOnAction(e -> {
			model.getSpelState().onLogin(naamFld);
		});
		saldoLbl = new Label();
		saldoLbl.setPadding(new Insets(10,0,0,10));
		naamBox.getChildren().addAll(naamLbl, naamFld, saldoLbl);

		//kies saldo
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

		//start knop
		startBtn = new Button("Start gokspel");
		startBtn.setOnAction(e -> {
			model.getSpelState().onStart();
		});

		loginPane.getChildren().addAll(naamBox, inzetBox, startBtn);
	}

	public void genereerGokPane(SpelModel model){
		gokPane = new VBox(10);
		gokPane.setLayoutY(150);
		gokPane.setLayoutX(20);

		//titel
		gokStratLbl = new Label("Kies een gok strategie");
		gokPane.getChildren().add(gokStratLbl);

		//gok strategies
		gokStratGrp = new ToggleGroup();
		gokStratRdbs = new ArrayList<>();
		for(GokEnum gok : GokEnum.values()){
			HBox gokBox = new HBox(20);

			//radio button
			RadioButton radioButton = new RadioButton(gok.getOmschrijving());
			radioButton.setToggleGroup(gokStratGrp);
			radioButton.setOnAction(e -> {
				model.getSpelState().onKiesGok(gok);
			});
			radioButton.setMinWidth(340);
			gokStratRdbs.add(radioButton);

			//winst factor lbl
			Label winstFacLbl = new Label("mogelijk winst is " + gok.getWinstfactor() + " x je inzet");
			winstFacLbl.setAlignment(Pos.TOP_RIGHT);

			gokBox.getChildren().addAll(radioButton, winstFacLbl);
			gokPane.getChildren().add(gokBox);
		}

		gokBevestigBtn = new Button("Bevestig keuze");
		gokBevestigBtn.setOnAction(e -> {
			model.getSpelState().onBevestigGok();
		});
		gokPane.getChildren().add(gokBevestigBtn);
	}

	public void genereerWerpPane(SpelModel model){
		werpPane = new VBox(10);
		werpPane.setLayoutY(300);
		werpPane.setLayoutX(20);

		//werp knop
		werpBtn = new Button("Werp dobbesteen");
		werpBtn.setOnAction(e -> {
			model.getSpelState().onWerp();
		});

		werpBox = new HBox(10);
		worpenBox = new VBox(10);
		commentBox = new VBox(10);

		werpBox.getChildren().addAll(worpenBox, commentBox);
		werpPane.getChildren().addAll(werpBtn, werpBox);
	}

	public void updateWorpenBox(SpelModel model){
		werpBox.getChildren().clear();
		for(int i = 0 ; i < model.getWorpen().size() && i < 4 ; i++){
			werpBox.getChildren().add(new Label("worp " + i + ": " + model.getWorpen().get(i)));
		}
	}

	public void refresh(SpelModel model){
		Speler speler = model.getSpeler();

		//login pane
		naamFld.setDisable(speler != null);
		saldoLbl.setText(speler == null ? "" : "Je saldo is " + speler.getSaldo() + ".");
		inzetFld.setDisable(model.isSpelBezig() && !(model.getWorpen().size() == 2));
		startBtn.setDisable(model.isSpelBezig());

		//gok strat pane
		for(RadioButton radioButton : gokStratRdbs){
			radioButton.setDisable(!model.isSpelBezig() || model.isStratGekozen());
		}
		gokBevestigBtn.setDisable(!model.isSpelBezig() || model.isStratGekozen());

		//worpen pane
		updateWorpenBox(model);
	}


}
