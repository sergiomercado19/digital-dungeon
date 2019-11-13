package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LevelMenuController {

   private MainMenuScreen mainScreen;
   private File[] dirListing;
   private ImageView[] previews;
   private Label[] labels;
   private int pageNum;

   @FXML
   private GridPane background;

   @FXML
   private Pane gameTitle;

   @FXML
   private Pane backButton;
   @FXML
   private Pane prevPage;
   @FXML
   private Pane nextPage;

   @FXML
   private ImageView preview1;
   @FXML
   private ImageView preview2;
   @FXML
   private ImageView preview3;
   @FXML
   private ImageView preview4;
   
   @FXML
   private Label label1;
   @FXML
   private Label label2;
   @FXML
   private Label label3;
   @FXML
   private Label label4;

   @FXML
   public void initialize() {
      // Group labels and previews
      this.previews = new ImageView[] {preview1, preview2, preview3, preview4};
      this.labels = new Label[] {label1, label2, label3, label4};
      
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

      // Setup control buttons
      ImageView backIcon = new ImageView(new Image("/icon_back.png"));
      backButton.getChildren().add(backIcon);
      ImageView prevIcon = new ImageView(new Image("/icon_arrow.png"));
      prevPage.getChildren().add(prevIcon);
      ImageView nextIcon = new ImageView(new Image("/icon_arrow.png"));
      nextPage.getChildren().add(nextIcon);
      nextPage.setRotate(nextPage.getRotate() + 180);

      // Setup levels: iterate through dungeons directory and display
      this.pageNum = 0;
      File dir = new File("dungeons");
      this.dirListing = dir.listFiles();
      this.showLevels();
   }
   
   public void showLevels() {
      if (this.dirListing != null) {
         int i;
         int start = this.pageNum*4;
         
         for (i = 0; start+i < this.dirListing.length && i < 4; i++) {
            File dungeon = this.dirListing[start+i];
            String name = dungeon.getName().substring(0, dungeon.getName().length()-5);

            previews[i].setImage(new Image("/preview_" + name + ".png"));
            labels[i].setText(name);
         }
         
         // Set remaining spaces to nothing
         while (i < 4) {
            previews[i].setImage(null);
            labels[i].setText("");
            i++;
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
   
   @FXML
   public void handlePrevPage() {
      if (this.pageNum > 0) this.pageNum--;
      this.showLevels();
   }
   
   @FXML
   public void handleNextPage() {
      if (this.pageNum < Math.ceil(this.dirListing.length/4.0) - 1) this.pageNum++;
      this.showLevels();
   }
   
   @FXML
   public void handleDungeon0() {
      try {         
         new DungeonScreen(this.labels[0].getText());
      } catch (IOException e) {
         // do nothing
      }
   }

   @FXML
   public void handleDungeon1() {
      try {         
         new DungeonScreen(this.labels[1].getText());
      } catch (IOException e) {
         // do nothing
      }
   }

   @FXML
   public void handleDungeon2() {
      try {         
         new DungeonScreen(this.labels[2].getText());
      } catch (IOException e) {
         // do nothing
      }
   }

   @FXML
   public void handleDungeon3() {
      try {         
         new DungeonScreen(this.labels[3].getText());
      } catch (IOException e) {
         // do nothing
      }
   }
}
