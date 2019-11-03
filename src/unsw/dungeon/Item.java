package unsw.dungeon;

/**
 * interface for an item, under the Collectable strategy pattern
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
