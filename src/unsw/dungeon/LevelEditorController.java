package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * the controller of the level editor
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class LevelEditorController {
	private ArrayList<EditorTile> tiles;
	private ArrayList<String> entities;
	private String selected;
	private int width;
	private int height;

	// load all imgs
	private Image dirtImage;
	private Image playerImage;
	private Image wallImage;
	private Image boulderImage;
	private Image treasureImage;
	private Image keyImage;
	private Image doorImage;
	private Image swordImage;
	private Image invincibilityImage;
	private Image portalImage;
	private Image enemyImage;
	private Image houndImage;
	private Image guardImage;
	private Image floorSwitchImage;
	private Image exitImage;

	@FXML
	private GridPane squares;

	@FXML
	private MenuButton dropDown;

	@SuppressWarnings("rawtypes")
	@FXML
	private Spinner tileID;

	@FXML
	private CheckBox exitGoal;

	@FXML
	private CheckBox enemiesGoal;

	@FXML
	private CheckBox treasureGoal;

	@FXML
	private CheckBox switchGoal;

	@FXML
	private TextField dWidth;

	@FXML
	private TextField dHeight;

	@FXML
	private TextField dName;

	@FXML
	private TabPane tabs;

	/**
	 * set the dimensions of the editor space
	 * @param event
	 */
	@FXML
	void setDim(ActionEvent event) {
		tiles.clear();
		width = Integer.parseInt(dWidth.getText());
		height = Integer.parseInt(dHeight.getText());
		setUpSquares();

		tabs.getSelectionModel().select(0);
		tabs.getScene().getWindow().sizeToScene();
	}

	/**
	 * export the level
	 * @param event
	 */
	@FXML
	void doExport(ActionEvent event) {
		JSONObject dungeon = new JSONObject();
		dungeon.put("width", width);
		dungeon.put("height", height);

		JSONArray entities = new JSONArray();
		for (EditorTile t : tiles) {
			if (!t.isEmpty()) {
				JSONObject j = t.toJSON();				
				entities.put(j);
			}
		}
		dungeon.put("entities", entities);

		JSONObject goals = new JSONObject();
		goals.put("goal", "AND");
		JSONArray subgoals = new JSONArray();
		if (exitGoal.isSelected()) {
			JSONObject jExitGoal = new JSONObject();
			jExitGoal.put("goal", "exit");
			subgoals.put(jExitGoal);
		}
		if (enemiesGoal.isSelected()) {
			JSONObject jEnemiesGoal = new JSONObject();
			jEnemiesGoal.put("goal", "enemies");
			subgoals.put(jEnemiesGoal);
		}
		if (treasureGoal.isSelected()) {
			JSONObject jTreasureGoal = new JSONObject();
			jTreasureGoal.put("goal", "treasure");
			subgoals.put(jTreasureGoal);
		}
		if (switchGoal.isSelected()) {
			JSONObject jSwitchGoal = new JSONObject();
			jSwitchGoal.put("goal", "boulders");
			subgoals.put(jSwitchGoal);
		}
		goals.put("subgoals", subgoals);
		dungeon.put("goal-condition", goals);

		// generate json
		try {
			PrintWriter out = new PrintWriter("dungeons/" + dName.getText() + ".json");
			out.print(dungeon.toString(2));
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		// generate preview image
		WritableImage image = squares.snapshot(new SnapshotParameters(), null);
		File file = new File("images/preview_" + dName.getText() + ".png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Level exported");
		alert.setHeaderText("Level exported");
		alert.setContentText("Your level was successfully exported! You can now play it from the level menu.");
		alert.showAndWait();

		tabs.getScene().getWindow().hide();

	}

	/**
	 * set up the gridpane in the specified size, and click events
	 */
	public void setUpSquares() {
		squares.getChildren().clear();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// add dirt floor under everything
				ImageView i = new ImageView(dirtImage);
				squares.add(i, x, y);

				// add a pane on top to hold other items
				Pane p = new Pane();
				GridPane.setConstraints(p, x, y);
				squares.add(p, x, y);

				// keep track of the item in the pane
				ImageView l = new ImageView();
				p.getChildren().add(l);
				EditorTile e = new EditorTile(x, y);
				tiles.add(e);
				trackChanges(e, l);

				p.setOnMouseDragEntered(event -> {
					if (event.isPrimaryButtonDown()) {
						if (selected.contentEquals("eraser")) selected = "empty";
						if (tileID.isDisable()) {
							e.setTile(selected);
						} else {
							e.setTile(selected, (int)tileID.getValue());
						}
					}
					if (event.isSecondaryButtonDown()) {
						e.setTile("empty");
					}
				});

				p.setOnMousePressed(event -> {
					if (event.isPrimaryButtonDown()) {
						if (selected.contentEquals("eraser")) selected = "empty";
						if (tileID.isDisable()) {
							e.setTile(selected);
						} else {
							e.setTile(selected, (int)tileID.getValue());
						}
					}
					if (event.isSecondaryButtonDown()) {
						e.setTile("empty");
					}
				});
			}
		}

		dWidth.setText(Integer.toString(width));
		dHeight.setText(Integer.toString(height));
	}

	/**
	 * observe when images are updated and change the imageview accordingly
	 * @param t
	 * @param node
	 */
	public void trackChanges(EditorTile t, Node node) {
		t.currType().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (!oldValue.equals(newValue)) {
					((ImageView) node).setImage(stringToImage(newValue));
				}
			}
		});
	}

	/**
	 * get an image object from a string
	 * @param tile the string of the entity
	 * @return the image of the entity
	 */
	private Image stringToImage(String tile) {
		switch(tile) {
		case "empty":
			return null;
		case "player":
			return playerImage;
		case "wall":
			return wallImage;
		case "boulder":
			return boulderImage;
		case "treasure":
			return treasureImage;
		case "key":
			return keyImage;
		case "door":
			return doorImage;
		case "sword":
			return swordImage;
		case "invincibility":
			return invincibilityImage;
		case "portal":
			return portalImage;
		case "enemy":
			return enemyImage;
		case "hound":
			return houndImage;
		case "guard":
			return guardImage;
		case "switch":
			return floorSwitchImage;
		case "exit":
			return exitImage;
		default:
			return null;
		}
	}

	/**
	 * intialise the level editor
	 */
	@FXML
	public void initialize() {
		// load in all the images
		dirtImage = new Image("/dirt_0_new.png");
		playerImage = new Image("/human_new.png");
		wallImage = new Image("/brick_brown_0.png");
		boulderImage = new Image("/boulder.png");
		treasureImage = new Image("/gold_pile.png");
		keyImage = new Image("/key.png");
		doorImage = new Image("/closed_door.png");
		swordImage = new Image("/greatsword_1_new.png");
		invincibilityImage = new Image("/brilliant_blue_new.png");
		portalImage = new Image("/portal.png");
		enemyImage = new Image("/deep_elf_master_archer.png");
		houndImage = new Image("/hound.png");
		guardImage = new Image("/gnome.png");
		floorSwitchImage = new Image("/pressure_plate.png");
		exitImage = new Image("/exit.png");

		// set up properties
		height = 10;
		width = 10;
		selected = "wall";
		dropDown.setText(selected);
		dName.setText("New Dungeon");
		tileID.setDisable(true);

		// holds info on all the tiles
		tiles = new ArrayList<>();

		entities = new ArrayList<>();
		entities.add("eraser");
		entities.add("player");
		entities.add("wall");
		entities.add("boulder");
		entities.add("switch");
		entities.add("treasure");
		entities.add("key");
		entities.add("door");
		entities.add("enemy");
		entities.add("hound");
		entities.add("guard");
		entities.add("sword");
		entities.add("invincibility");
		entities.add("portal");
		entities.add("exit");

		// Setup background
		setUpSquares();

		// create menu options for each entity
		for (String e : entities) {
			MenuItem m = new MenuItem(e);
			m.setGraphic(new ImageView(stringToImage(e)));

			// when an entity is selected
			m.setOnAction(event -> {
				dropDown.setText(e);
				selected = e;
				if (selected.equals("key") || selected.equals("door") || selected.equals("portal")) {
					tileID.setDisable(false);
				} else {
					tileID.setDisable(true);
				}
			});
			dropDown.getItems().add(m);
		}

		// set up drag event
		squares.setOnDragDetected(e -> squares.startFullDrag());
	}
}
