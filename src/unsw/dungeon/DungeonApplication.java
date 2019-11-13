package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

   @Override
   public void start(Stage primaryStage) throws IOException {
      
      // Initialize menu pages
      MainMenuScreen menuScreen = new MainMenuScreen(primaryStage);
      LevelMenuScreen levelScreen = new LevelMenuScreen(primaryStage);
      
      // Link menu pages
      menuScreen.setLevelScreen(levelScreen);
      levelScreen.setMainScreen(menuScreen);
      
      menuScreen.start();

   }

   public static void main(String[] args) {
      launch(args);
   }

}
