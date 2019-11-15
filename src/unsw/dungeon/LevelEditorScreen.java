package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the visual component of the level editor
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class LevelEditorScreen extends Stage {

	private LevelEditorController controller;
	private Scene scene;

	/**
	 * create a new level editor screen
	 * @throws IOException
	 */
	public LevelEditorScreen() throws IOException {
		this.setTitle("Level Creator");

		controller = new LevelEditorController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelEditor.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root);

		this.setScene(scene);
		this.show();
	}

	/**
	 * get the controller of the level editor screen
	 * @return
	 */
	public LevelEditorController getController() {
		return controller;
	}
}
