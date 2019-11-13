package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

   @Override
   public void start(Stage primaryStage) throws IOException {

      // Initialize menu pages
      MainMenuScreen mainMenuScreen = new MainMenuScreen(primaryStage);
      LevelMenuScreen levelMenuScreen = new LevelMenuScreen(primaryStage);
	   LevelEditorScreen levelEditorScreen = new LevelEditorScreen(primaryStage);

      // Link menu pages
	   mainMenuScreen.setLevelMenuScreen(levelMenuScreen);
      levelMenuScreen.setMainScreen(mainMenuScreen);
      mainMenuScreen.setEditorScreen(levelEditorScreen);

      // Play music
      AudioClip audio = new AudioClip(getClass().getResource("/background.wav").toExternalForm());
      audio.setVolume(0.3f);
      audio.setCycleCount(MediaPlayer.INDEFINITE);
      audio.play();
      
      mainMenuScreen.start();

//	   levelEditorScreen.start();

   }

   public static void main(String[] args) {
      launch(args);
   }

}
