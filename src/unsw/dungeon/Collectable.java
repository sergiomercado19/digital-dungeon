package unsw.dungeon;

/**
 * an object that can be collected
 * employs the strategy pattern using Items
 * @author Rory
 *
 */
public class Collectable extends InertEntity {
	
	private Item item;

	/**
	 * create a new collectable
	 * @param x x position of the collectable
	 * @param y y position of the collectable
	 * @param item the type of collectable
	 */
	public Collectable(int x, int y, Item item) {
		super(x, y, false);
		this.item = item;
	}
	
	/**
	 * delegate collecting the item (strategy)
	 * @param player the player who collects the item
	 */
	public void collect(Player player) {
		item.collect(player);
	}
	
	/**
	 * get the item of the collectable
	 * @return the item of the collectable
	 */
	public Item getItem() {
		return item;
	}
}
