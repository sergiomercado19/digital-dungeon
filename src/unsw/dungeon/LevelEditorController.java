package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.text.Font;

public class LevelEditorController {
	private ArrayList<EditorTile> tiles;
	private ArrayList<String> entities;
	private String selected;
	private int width;
	private int height;
	
	private Image dirtImage;

	@FXML
	private GridPane squares;

	@FXML
	private MenuButton dropDown;

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
				EditorTile e = new EditorTile(x, y);
				tiles.add(e);
				p.getChildren().add(e.getTile());
				p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					p.getChildren().clear();
					e.setTile(selected, (int)tileID.getValue());
					p.getChildren().add(e.getTile());
				});
			}
		}
		
		dWidth.setText(Integer.toString(width));
		dHeight.setText(Integer.toString(height));
	}

	@FXML
	public void initialize() {
		height = 10;
		width = 10;
		selected = "Wall";
		dirtImage = new Image("/dirt_0_new.png");
		tileID.setVisible(false);
		dName.setText("My New Dungeon");

		tiles = new ArrayList<>();

		entities = new ArrayList<>();
		entities.add("Eraser");
		entities.add("Player");
		entities.add("Wall");
		entities.add("Boulder");
		entities.add("Treasure");
		entities.add("Key");
		entities.add("Door");
		entities.add("Sword");
		entities.add("Invincibility");
		entities.add("Portal");
		entities.add("Enemy");
		entities.add("Floor switch");
		entities.add("Exit");

		// Setup background
		setUpSquares();
		
		ArrayList<String> idList = new ArrayList<>();
		idList.add("Key");
		idList.add("Door");
		idList.add("Portal");

		for (String e : entities) {
			MenuItem m = new MenuItem(e);
			m.setOnAction(event -> {
				dropDown.setText(e);
				selected = e;
				if (idList.contains(selected)) {
					tileID.setVisible(true);
//					tileID.managedProperty
				} else {
					tileID.setVisible(false);
				}
			});
			dropDown.getItems().add(m);
		}
	}
}
