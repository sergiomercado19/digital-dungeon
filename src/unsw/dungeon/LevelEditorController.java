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
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
	void setDim(ActionEvent event) {
		tiles.clear();
		width = Integer.parseInt(dWidth.getText());
		height = Integer.parseInt(dHeight.getText());
		setUpSquares();
	}

	@FXML
	void doExport(ActionEvent event) {
		JSONObject dungeon = new JSONObject();
		dungeon.put("width", width);
		dungeon.put("height", height);

		JSONArray entities = new JSONArray();
		for (EditorTile t : tiles) {
			JSONObject j = t.toJSON();
			if (j != null) entities.put(j);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// generate preview image
		WritableImage image = squares.snapshot(new SnapshotParameters(), null);
		File file = new File("images/preview_" + dName.getText() + ".png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			// TODO: handle exception here
		}
	}

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

				p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					if (selected.contentEquals("eraser")) selected = "empty";
					if (tileID.isDisable()) {
						e.setTile(selected);
					} else {
						e.setTile(selected, (int)tileID.getValue());
					}
				});
			}
		}

		dWidth.setText(Integer.toString(width));
		dHeight.setText(Integer.toString(height));
	}

	// if an image is updated
	public void trackChanges(EditorTile t, Node node) {
		t.currType().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (!oldValue.equals(newValue)) {
					switch(newValue) {
					case "empty":
						((ImageView) node).setImage(null);
						break;
					case "player":
						((ImageView) node).setImage(playerImage);
						break;
					case "wall":
						((ImageView) node).setImage(wallImage);
						break;
					case "boulder":
						((ImageView) node).setImage(boulderImage);
						break;
					case "treasure":
						((ImageView) node).setImage(treasureImage);
						break;
					case "key":
						((ImageView) node).setImage(keyImage);
						break;
					case "door":
						((ImageView) node).setImage(doorImage);
						break;
					case "sword":
						((ImageView) node).setImage(swordImage);
						break;
					case "invincibility":
						((ImageView) node).setImage(invincibilityImage);
						break;
					case "portal":
						((ImageView) node).setImage(portalImage);
						break;
					case "enemy":
						((ImageView) node).setImage(enemyImage);
						break;
					case "switch":
						((ImageView) node).setImage(floorSwitchImage);
						break;
					case "exit":
						((ImageView) node).setImage(exitImage);
						break;		
					}
				}
			}
		});
	}

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
		floorSwitchImage = new Image("/pressure_plate.png");
		exitImage = new Image("/exit.png");
		
		// set up properties
		height = 10;
		width = 10;
		selected = "eraser";
		dName.setText("New Dungeon");
		tileID.setDisable(true);
		
		// holds info on all the tiles
		tiles = new ArrayList<>();

		entities = new ArrayList<>();
		entities.add("eraser");
		entities.add("player");
		entities.add("wall");
		entities.add("boulder");
		entities.add("treasure");
		entities.add("key");
		entities.add("door");
		entities.add("sword");
		entities.add("invincibility");
		entities.add("portal");
		entities.add("enemy");
		entities.add("switch");
		entities.add("exit");

		// Setup background
		setUpSquares();

		// create menu options for each entity
		for (String e : entities) {
			MenuItem m = new MenuItem(e);

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
	}
}
