package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelEditorScreen {
   private Stage stage;
   private String title;
   private LevelEditorController controller;

   private Scene scene;

   public LevelEditorScreen(Stage stage) throws IOException {
      this.stage = stage;
      title = "Level Creator";

      controller = new LevelEditorController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelEditor.fxml"));
      loader.setController(controller);

      // load into a Parent node called root
      Parent root = loader.load();
      scene = new Scene(root);
   }
   
   public void start() {
      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
   }

   public LevelEditorController getController() {
      return controller;
   }
}
