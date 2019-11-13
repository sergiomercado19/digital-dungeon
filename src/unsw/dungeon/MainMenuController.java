package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainMenuController {
   
   private LevelMenuScreen levelScreen;

   @FXML
   private GridPane background;

   @FXML
   private Pane gameTitle;

   @FXML
   private Pane playButton;

   @FXML
   private Pane editorButton;

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
      ImageView editorIcon = new ImageView(new Image("/icon_editor.png"));
      editorIcon.setFitHeight(70);
      editorIcon.setFitWidth(70);
      editorIcon.layoutXProperty().bind(editorButton.widthProperty().subtract(editorIcon.getFitWidth()).divide(2));
      editorIcon.layoutYProperty().bind(editorButton.heightProperty().subtract(editorIcon.getFitHeight()).divide(2));
      editorButton.getChildren().add(editorIcon);
   }

   public void setLevelScreen(LevelMenuScreen levelScreen) {
      this.levelScreen = levelScreen;
   }

   @FXML
   public void handlePlay() {
      this.levelScreen.start();
   }

}
