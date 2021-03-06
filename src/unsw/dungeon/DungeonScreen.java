package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the visual component of the dungeon
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class DungeonScreen extends Stage {

	private DungeonControllerLoader dungeonLoader;
	private DungeonController controller;

	private Scene scene;

	/**
	 * create a new dungeon screen
	 * @param name
	 * @throws IOException
	 */
	public DungeonScreen(String name) throws IOException {
		this.setTitle("Digital Dungeon - " + name);

		dungeonLoader = new DungeonControllerLoader(name + ".json");
		controller = dungeonLoader.loadController();
		controller.setDungeonName(name);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		scene = new Scene(root);
		root.requestFocus();

		this.setScene(scene);
		this.show();
	}

	/**
	 * get the controller associated with the screen
	 * @return
	 */
	public DungeonController getController() {
		return controller;
	}
}
