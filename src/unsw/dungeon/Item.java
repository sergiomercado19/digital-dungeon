package unsw.dungeon;

/**
 * interface for a collectable item
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface Item {
	/**
	 * give item to player and remove from the dungeon
	 * @param player
	 */
	public void collect(Player player);
}
