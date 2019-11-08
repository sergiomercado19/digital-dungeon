package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainMenuController {
   
   @FXML
   private GridPane background;
   
   @FXML
   private Pane gameTitle;
   
   @FXML
   private Pane playButton;
   
   @FXML
   private Pane settingsButton;
   
   @FXML
   public void initialize() {
      // Setup background
      Image brick = new Image("/brick_brown_0.png");
      for (int x = 0; x <= 25; x++) {
         for (int y = 0; y <= 15; y++) {
            background.add(new ImageView(brick), x, y);
         }
      }
      
      // Setup title
      ImageView title = new ImageView(new Image("/digital_dungeon.png"));
      gameTitle.getChildren().add(title);
      
      // Setup buttons
      ImageView playIcon = new ImageView(new Image("/icon_play.png"));
      playButton.getChildren().add(playIcon);
      ImageView settingsIcon = new ImageView(new Image("/icon_settings.png"));
      settingsButton.getChildren().add(settingsIcon);
   }
   
}
