package unsw.dungeon;

/**
 * a sword item, which when picked up will allow the player to kill enemies for a certain period of time
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Sword implements Item {

   @Override
   public void collect(Player player) {
      player.pickupSword();
   }

}
