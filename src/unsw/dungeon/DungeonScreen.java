package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen extends Stage {

   private DungeonControllerLoader dungeonLoader;
   private DungeonController controller;

   private Scene scene;

   public DungeonScreen(String name) throws IOException {
      this.setTitle("Digital Dungeon - " + name);

      dungeonLoader = new DungeonControllerLoader(name + ".json");
      controller = dungeonLoader.loadController();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
      loader.setController(controller);
      Parent root = loader.load();
      scene = new Scene(root);
      root.requestFocus();
      
      this.setScene(scene);
      this.show();
   }

   public DungeonController getController() {
      return controller;
   }
}
