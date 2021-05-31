package view;

import controller.GamblerViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SpelModel;
import model.database.SettingsDB;
import model.gokStrategy.GokEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iedereen
 */
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
	private VBox resultBox;
		
	public GamblerView(GamblerViewController controller){
		this.controller = controller;
		controller.setView(this);
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

		loginPane.setPadding(new Insets(10, 10, 10, 10));
		loginPane.setBorder(
				new Border(
						new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
				)
		);
		loginPane.setLayoutX(20);

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
		saldoLbl.setVisible(false);
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
		if(gokPane == null){
			gokPane = new VBox(10);
			gokPane.setLayoutY(150);
			gokPane.setLayoutX(20);
		}

		gokPane.setPadding(new Insets(10, 10, 10, 10));
		gokPane.setBorder(
				new Border(
						new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
				)
		);

		gokPane.getChildren().clear();

		//titel
		gokStratLbl = new Label("Kies een gok strategie");
		gokPane.getChildren().add(gokStratLbl);

		gokStratGrp = new ToggleGroup();
		gokStratRdbs = new ArrayList<>();
		for(GokEnum gok : SettingsDB.getInstance().getBeschikbareGokken()){
			HBox gokBox = new HBox(20);

			//radio button
			RadioButton radioButton = new RadioButton(gok.getOmschrijving());
			radioButton.setToggleGroup(gokStratGrp);
			radioButton.setOnAction(e -> {
				SpelModel.getInstance().getSpelState().onKiesGok(gok);
			});
			radioButton.setMinWidth(340);
			gokStratRdbs.add(radioButton);

			//winst factor lbl
			Label winstFacLbl = new Label(
					"mogelijk winst is " + SettingsDB.getInstance().getWinstFactors().get(gok) + " x je inzet"
			);
			winstFacLbl.setAlignment(Pos.TOP_RIGHT);

			gokBox.getChildren().addAll(radioButton, winstFacLbl);
			gokPane.getChildren().add(gokBox);
		}

		gokBevestigBtn = new Button("Bevestig keuze");
		gokBevestigBtn.setOnAction(e -> {
			for(RadioButton radioButton : gokStratRdbs){
				radioButton.setSelected(false);
			}

			model.getSpelState().onBevestigGok();
		});
		gokPane.getChildren().add(gokBevestigBtn);
	}


	private void genereerWerpPane(SpelModel model){
		werpPane = new VBox(10);
		werpPane.setLayoutY(350);
		werpPane.setLayoutX(20);

		//werp knop
		werpBtn = new Button("Werp dobbesteen");
		werpBtn.setOnAction(e -> {
			System.out.println(model.getSpelState());
			model.getSpelState().onWerp();
		});

		werpBox = new HBox(10);
		worpenBox = new VBox(10);
		worpenBox.setMinWidth(200);
		resultBox = new VBox(20);
		resultBox.setVisible(false);
		resultBox.setLayoutX(300);

		werpBox.getChildren().addAll(worpenBox, resultBox);
		werpPane.getChildren().addAll(werpBtn, werpBox);
	}

	public void refreshWorpenBox(SpelModel model){
		worpenBox.getChildren().clear();
		for(int i = 0 ; i < model.getWorpen().size() && i < 4 ; i++){
			worpenBox.getChildren().add(new Label("worp " + (i + 1) + ": " + model.getWorpen().get(i)));
		}
	}

	public void refreshResult(String bericht, Color kleur, SpelModel model){
		resultBox.setVisible(true);
		resultBox.getChildren().clear();
		Label berichtLbl = new Label(bericht);
		berichtLbl.setTextFill(kleur);
		Label overschotLbl = new Label("Je nieuw saldo bedraagt " + model.getSpeler().getSaldo() + " €");
		overschotLbl.setTextFill(kleur);
		resultBox.getChildren().addAll(berichtLbl, overschotLbl);
	}

	public void hideResult(){
		resultBox.setVisible(false);
	}

	public void refreshLoginFld(String naam, boolean disabled){
		naamFld.setDisable(disabled);
		naamFld.setText(naam);
	}

	public void refreshInzetFld(double inzet, boolean disablede){
		inzetFld.setText(inzet + "");
		inzetFld.setDisable(disablede);
	}

	public void refreshSaldoLbl(double saldo, boolean visible){
		saldoLbl.setVisible(visible);
		saldoLbl.setText("Je saldo is " + saldo + ".");
	}

	public void refreshStartBtn(boolean disabled){
		startBtn.setDisable(disabled);
	}

	public void refreshGokStrats(boolean kanStatKiezen){
		for(RadioButton radioButton : gokStratRdbs){
			radioButton.setDisable(!kanStatKiezen);
		}
		gokBevestigBtn.setDisable(!kanStatKiezen);
	}
}
