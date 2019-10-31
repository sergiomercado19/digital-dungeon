package unsw.dungeon;

public class Invincibility implements Item {

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collect(Player player) {
		// TODO Auto-generated method stub
		player.becomeInvincible();
		// delete item
	}

}
