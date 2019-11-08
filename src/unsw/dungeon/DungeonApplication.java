package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

   @Override
   public void start(Stage primaryStage) throws IOException {
      
      MainMenuScreen menuScreen = new MainMenuScreen(primaryStage);
      
      menuScreen.show();

   }

   public static void main(String[] args) {
      launch(args);
   }

}
