package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelMenuScreen {
   private Stage stage;
   private String title;
   private LevelMenuController controller;

   private Scene scene;

   public LevelMenuScreen(Stage stage) throws IOException {
      this.stage = stage;
      title = "Digital Dungeon";

      controller = new LevelMenuController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelMenu.fxml"));
      loader.setController(controller);

      // load into a Parent node called root
      Parent root = loader.load();
      scene = new Scene(root, 800, 480);
   }
   
   public void setMainScreen(MainMenuScreen mainScreen) {
      controller.setMainScreen(mainScreen);
   }

   public void start() {
      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
   }

   public LevelMenuController getController() {
      return controller;
   }
}
