package unsw.dungeon;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class DungeonController {

   @FXML
   private GridPane squares;
   
   @FXML
   private TreeView<String> goalsTree;

   private ArrayList<ImageView> initialEntities;
   private TreeItem<String> initialGoal;

   private Player player;

   private Dungeon dungeon;

   public DungeonController(Dungeon dungeon, ArrayList<ImageView> initialEntities, TreeItem<String> initialGoal) {
      this.dungeon = dungeon;
      this.player = dungeon.getPlayer();
      this.initialEntities = initialEntities;
      this.initialGoal = initialGoal;
   }

   @FXML
   public void initialize() {
      Image ground = new Image("/dirt_0_new.png");

      // Add the ground first so it is below all other entities
      for (int x = 0; x < dungeon.getWidth(); x++) {
         for (int y = 0; y < dungeon.getHeight(); y++) {
            squares.add(new ImageView(ground), x, y);
         }
      }

      for (ImageView entity : initialEntities)
         squares.getChildren().add(entity);

      this.goalsTree.setRoot(this.initialGoal);

   }

   @FXML
   public void handleKeyPress(KeyEvent event) {
      switch (event.getCode()) {
      case UP:
         player.makeMove(Direction.UP);
         break;
      case DOWN:
         player.makeMove(Direction.DOWN);
         break;
      case LEFT:
         player.makeMove(Direction.LEFT);
         break;
      case RIGHT:
         player.makeMove(Direction.RIGHT);
         break;
      default:
         break;
      }
   }

}

