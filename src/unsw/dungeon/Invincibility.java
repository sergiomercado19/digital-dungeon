package unsw.dungeon;

import javafx.scene.media.AudioClip;

/**
 * an invincibility potion item, which gives the player timed invincibility when picked up
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Invincibility extends Entity implements Item {

   public Invincibility(int x, int y) {
      super(x, y, false);
   }

   @Override
   public void collect(Player player) {
      player.becomeInvincible(this);
      
      // Play sound
      AudioClip audio = new AudioClip(getClass().getResource("/pickup_potion.mp3").toExternalForm());
      audio.play();
   }

   @Override
   public void collide(Player p, Direction d) {
      collect(p);
   }

}
