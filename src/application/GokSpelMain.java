package application;
	
import controller.AdminViewController;
import controller.GamblerViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SpelModel;
import model.database.SettingsDB;
import model.database.SpelersDB;
import view.AdminView;
import view.GamblerView;

/**
 * @author iedereen
 */
public class GokSpelMain extends Application {
	private static SpelersDB database;
	private SettingsDB settings;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gokspel");

		database = SpelersDB.getInstance();
		database.load();

		settings = new SettingsDB();
		settings.save();


		SpelModel spelModel = SpelModel.getInstance();

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


