package unsw.dungeon;

/**
 * an invincibility potion item, which gives the player timed invincibility when picked up
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Invincibility extends Entity implements Item {

	/**
	 * create a new invincibility potion
	 * @param x
	 * @param y
	 */
	public Invincibility(int x, int y) {
		super(x, y, false);
	}

	@Override
	public void collect(Player player) {
		player.becomeInvincible(this);

		// Play sound
		SoundEffects.playPickupPotion();
	}

	@Override
	public void collide(Player p, Direction d) {
		collect(p);
	}

}
