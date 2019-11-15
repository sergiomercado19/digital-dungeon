package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the visual component of the level menu
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class LevelMenuScreen {
	private Stage stage;
	private String title;
	private LevelMenuController controller;
	private Scene scene;

	/**
	 * create a new level menu screen
	 * @param stage
	 * @throws IOException
	 */
	public LevelMenuScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Digital Dungeon";

		controller = new LevelMenuController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelMenu.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, 800, 480);
	}

	/**
	 * attach the associated main menu screen
	 * @param mainScreen
	 */
	public void setMainScreen(MainMenuScreen mainScreen) {
		controller.setMainScreen(mainScreen);
	}

	/**
	 * pop up and display the screen
	 */
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * get the controller of the level menu
	 * @return
	 */
	public LevelMenuController getController() {
		return controller;
	}
}
