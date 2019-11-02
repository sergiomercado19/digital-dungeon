package unsw.dungeon;

public class Invincibility implements Item {

	@Override
	public boolean canCollect() {
		return true;
	}

	@Override
	public void collect(Player player) {
		player.becomeInvincible();
		// delete item
	}

}
