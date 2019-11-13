package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelEditorScreen extends Stage {

   private LevelEditorController controller;

   private Scene scene;

   public LevelEditorScreen() throws IOException {
      this.setTitle("Level Creator");

      controller = new LevelEditorController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelEditor.fxml"));
      loader.setController(controller);

      // load into a Parent node called root
      Parent root = loader.load();
      scene = new Scene(root);

      this.setScene(scene);
      this.show();
   }

   public LevelEditorController getController() {
      return controller;
   }
}
