package unsw.dungeon;

/**
 * an invincibility potion item, which gives the player timed invincibility when picked up
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Invincibility implements Item {

	@Override
	public void collect(Player player) {
		player.becomeInvincible();
		// delete item
	}

}
