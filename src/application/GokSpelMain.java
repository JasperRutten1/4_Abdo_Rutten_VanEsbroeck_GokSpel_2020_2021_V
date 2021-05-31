package application;
	
import controller.AdminViewController;
import controller.GamblerViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SpelModel;
import model.database.SettingsDB;
import model.database.SpelersDB;
import model.database.saveLoadStrategies.SaveLoadEnum;
import view.AdminView;
import view.GamblerView;

public class GokSpelMain extends Application {
	private static SpelersDB database;
	private SettingsDB settings;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gokspel");

		database = new SpelersDB(SaveLoadEnum.SPELER_TEKST);
		database.load();

		settings = new SettingsDB();
		settings.save();
		System.out.println(settings);
		System.out.println(settings.getWinstFactors());

		System.out.println(database.getSpelers());

		SpelModel spelModel = new SpelModel(database);

		GamblerViewController gamblerViewController = new GamblerViewController(spelModel);
		GamblerView gamblerView = new GamblerView(gamblerViewController);

		AdminViewController adminViewController = new AdminViewController(spelModel);
		AdminView adminView = new AdminView(adminViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static SpelersDB getDatabase(){
		return database;
	}
}
