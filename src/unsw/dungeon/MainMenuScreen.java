package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the visual component of the main menu
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class MainMenuScreen {
	private Stage stage;
	private String title;
	private MainMenuController controller;
	private Scene scene;

	/**
	 * create a new main menu screen
	 * @param stage
	 * @throws IOException
	 */
	public MainMenuScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Digital Dungeon";

		controller = new MainMenuController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, 800, 480);
	}

	/**
	 * attach the corresponding level menu screen
	 * @param levelScreen
	 */
	public void setLevelMenuScreen(LevelMenuScreen levelScreen) {
		controller.setLevelScreen(levelScreen);
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
	 * get the controller of the main menu
	 * @return
	 */
	public MainMenuController getController() {
		return controller;
	}
}
