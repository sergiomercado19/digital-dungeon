package unsw.dungeon;

import javafx.scene.media.AudioClip;

/**
 * a sword item, which when picked up will allow the player to kill enemies for a certain period of time
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Sword extends Entity implements Item {

   public Sword(int x, int y) {
      super(x, y, false);
   }

   @Override
   public void collect(Player player) {
      player.pickupSword(this);
      
      // Play sound
      AudioClip audio = new AudioClip(getClass().getResource("/pickup_sword.mp3").toExternalForm());
      audio.play();
   }

   @Override
   public void collide(Player p, Direction d) {
      collect(p);
   }

}
