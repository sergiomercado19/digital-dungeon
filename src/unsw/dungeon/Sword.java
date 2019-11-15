package unsw.dungeon;

/**
 * a sword item, which when picked up will allow the player to kill enemies for a certain period of time
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Sword extends Entity implements Item {

	/**
	 * create a new sword in the dungeon
	 * @param x
	 * @param y
	 */
	public Sword(int x, int y) {
		super(x, y, false);
	}

	@Override
	public void collect(Player player) {
		player.pickupSword(this);

		// Play sound
		SoundEffects.playPickupSword();
	}

	@Override
	public void collide(Player p, Direction d) {
		collect(p);
	}

}
