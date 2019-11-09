package unsw.dungeon;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LevelMenuController {

   private MainMenuScreen mainScreen;

   @FXML
   private GridPane background;

   @FXML
   private Pane gameTitle;

   @FXML
   private Pane backButton;

   @FXML
   private HBox levelBoxContainer;

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

      // Setup button
      ImageView backIcon = new ImageView(new Image("/icon_back.png"));
      backButton.getChildren().add(backIcon);

      // Setup levels: iterate through dungeons directory and display
      File dir = new File("dungeons");
      File[] dirListing = dir.listFiles();
      if (dirListing != null) {
         for (File dungeon : dirListing) {
            String name = dungeon.getName().substring(0, dungeon.getName().length()-5);

            Pane p1 = new Pane();
            p1.prefHeight(350);
            ImageView preview = new ImageView(new Image("/preview_" + name + ".png"));
            preview.fitHeightProperty();
            p1.getChildren().add(preview); 

            Pane p2 = new Pane();
            p2.setStyle("-fx-border-radius: 5; -fx-border-color: #0d0d0e; -fx-background-color: #beb3b1; -fx-border-width: 3;");
            Text text = new Text();
            text.setTextAlignment(TextAlignment.CENTER);
            text.setText(name);
            p2.getChildren().add(text);

            // Create VBox and add both panes
            VBox card = new VBox();
            HBox.setHgrow(card, Priority.ALWAYS);
            card.getChildren().addAll(p1, p2);

            // Add card to display
            levelBoxContainer.getChildren().add(card);
         }
      }
   }
   
   public void setMainScreen(MainMenuScreen mainScreen) {
      this.mainScreen = mainScreen;
   }

   @FXML
   public void handleBack() {
      this.mainScreen.start();
   }
}
