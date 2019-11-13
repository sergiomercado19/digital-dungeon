package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * A JavaFX controller for the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class DungeonController {

   @FXML
   private GridPane squares;
   
   @FXML
   private AnchorPane statusBar;
   
   @FXML
   private TreeView<String> goalsTree;
   
   @FXML
   private Pane banner;
   
   @FXML
   private HBox toolbar;
   
   @FXML
   private Pane resetButton;
   @FXML
   private Pane exitButton;

   private ArrayList<ImageView> initialEntities;
   private TreeItem<String> initialGoal;
   private Player player;
   private Dungeon dungeon;
   private String dungeonName;

   public DungeonController(Dungeon dungeon, ArrayList<ImageView> initialEntities, TreeItem<String> initialGoal) {
      this.dungeon = dungeon;
      this.player = dungeon.getPlayer();
      this.initialEntities = initialEntities;
      this.initialGoal = initialGoal;
   }

   public void setDungeonName(String dungeonName) {
      this.dungeonName = dungeonName;
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

      // Show goal progress
      this.goalsTree.setRoot(this.initialGoal);
      
      // Setup buttons
      ImageView resetIcon = new ImageView(new Image("/icon_reset.png"));
      resetButton.getChildren().add(resetIcon);
      resetIcon.setFitHeight(40);
      resetIcon.setFitWidth(40);
      resetIcon.layoutXProperty().bind(resetButton.widthProperty().subtract(resetIcon.getFitWidth()).divide(2));
      resetIcon.layoutYProperty().bind(resetButton.heightProperty().subtract(resetIcon.getFitHeight()).divide(2));
      ImageView exitIcon = new ImageView(new Image("/icon_exit.png"));
      exitButton.getChildren().add(exitIcon);
      
      // Setup DungeonState trackers
      this.dungeon.isGameOver().addListener(new ChangeListener<Boolean>() {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable,
               Boolean oldValue, Boolean newValue) {
            if (oldValue == false && newValue == true) {
               banner.getChildren().remove(goalsTree);

               
               if (dungeon.getState() == DungeonState.WON) {
                  Image victory = new Image("/victory.png");
                  ImageView victoryBanner = new ImageView(victory);
                  victoryBanner.setFitHeight(50);
                  victoryBanner.setFitWidth(200);
                  victoryBanner.layoutXProperty().bind(banner.widthProperty().subtract(victoryBanner.getFitWidth()).divide(2));
                  victoryBanner.layoutYProperty().bind(banner.heightProperty().subtract(victoryBanner.getFitHeight()).divide(2));
                  banner.getChildren().add(victoryBanner);
                  
                  // Play sound
                  SoundEffects.playVictoryTune();
               } else if (dungeon.getState() == DungeonState.LOST) {
                  Image defeat = new Image("/defeat.png");
                  ImageView defeatBanner = new ImageView(defeat);
                  defeatBanner.setFitHeight(50);
                  defeatBanner.setFitWidth(200);
                  defeatBanner.layoutXProperty().bind(banner.widthProperty().subtract(defeatBanner.getFitWidth()).divide(2));
                  defeatBanner.layoutYProperty().bind(banner.heightProperty().subtract(defeatBanner.getFitHeight()).divide(2));
                  banner.getChildren().add(defeatBanner);
                  
                  // Play sound
                  SoundEffects.playDefeatTune();
               }
               
            }
         }
      });

   }

   @FXML
   public void handleKeyPress(KeyEvent event) {
      if (dungeon.getState() == DungeonState.INPROGRESS) {         
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
   
   @FXML
   public void handleReset() {
      try {         
         new DungeonScreen(this.dungeonName);
         this.handleExit();
      } catch (IOException e) {
         // do nothing
      }
   }

   @FXML
   public void handleExit() {
      ((Node) this.squares).getScene().getWindow().hide();
   }
}

