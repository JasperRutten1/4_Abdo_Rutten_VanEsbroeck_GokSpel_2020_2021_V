package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.SpelersDB;
import model.saveLoadStrategies.ExcelSaveLoad;
import model.saveLoadStrategies.TextSaveLoad;
import view.AdminView;
import view.GamblerView;

public class GokSpelMain extends Application {
	private static SpelersDB database;

	@Override
	public void start(Stage primaryStage) {
		database = new SpelersDB(new TextSaveLoad());
		database.load();
		System.out.println(database.getSpelers());
		AdminView adminView = new AdminView();
		GamblerView gamblerView = new GamblerView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static SpelersDB getDatabase(){
		return database;
	}
}
