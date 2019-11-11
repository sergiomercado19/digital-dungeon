package unsw.dungeon;

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
   }

   @Override
   public void collide(Player p, Direction d) {
      collect(p);
   }

}
