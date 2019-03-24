package unbabel_jcc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unbabel_jcc.resources.Messages;



public class Main extends Application {

	/**
	 * Static value representing JavaFx user interface, needed to load said interface.
	 */
	public static String GUI = "Gui.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource(GUI), Messages.getResourceBundle());
		Scene scene = new Scene(root, 500, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main( String[] args ) {
		launch(args);
	}
}
