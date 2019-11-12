package unsw.dungeon;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LevelEditorController {
	private ArrayList<EditorTile> tiles;
	private ArrayList<String> entities;
	private String selected; 
//	private Image dirtImage;

	@FXML
	private GridPane squares;

	@FXML
	private MenuButton dropDown;
	
	@FXML
    private TextField tileID;

//    @FXML
//    private TextField dimX;
//
//    @FXML
//    private TextField dimY;
//
//    @FXML
//    void setDims(ActionEvent event) {
//
//    }
	@FXML
    void doExport(ActionEvent event) {
		for (EditorTile t : tiles) {
			String s = t.getJSON();
			if (s != null) System.out.println(s);
		}
    }

	@FXML
	public void initialize() {
//		dirtImage = new Image("/dirt_0_new.png");
//		ImageView backg = new ImageView(dirtImage);
		selected = "Wall";
		
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
		for (int x = 0; x <= 10; x++) {
			for (int y = 0; y <= 10; y++) {
				Pane p = new Pane();
				GridPane.setConstraints(p, x, y);
				squares.add(p, x, y);
				EditorTile e = new EditorTile(x, y);
				tiles.add(e);
				p.getChildren().add(e.getTile());
				p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					p.getChildren().clear();
					e.setTile(selected, tileID.getText());
					p.getChildren().add(e.getTile());
				});
			}
		}
		
		for (String e : entities) {
			MenuItem m = new MenuItem(e);
			m.setOnAction(event -> {
				dropDown.setText(e);
				selected = e;
			});
			dropDown.getItems().add(m);
		}
	}
}
