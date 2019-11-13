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
      MainMenuScreen menuScreen = new MainMenuScreen(primaryStage);
      LevelMenuScreen levelScreen = new LevelMenuScreen(primaryStage);

      // Link menu pages
      menuScreen.setLevelScreen(levelScreen);
      levelScreen.setMainScreen(menuScreen);

      // Play music
      AudioClip audio = new AudioClip(getClass().getResource("/background.wav").toExternalForm());
      audio.setVolume(0.3f);
      audio.setCycleCount(MediaPlayer.INDEFINITE);
      audio.play();

      menuScreen.start();

   }

   public static void main(String[] args) {
      launch(args);
   }

}
