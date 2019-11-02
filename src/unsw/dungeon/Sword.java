package unsw.dungeon;

public class Sword implements Item {

	@Override
	public boolean canCollect() {
		// if has sword, false. otherwise
		return true;
	}

	@Override
	public void collect(Player player) {
		player.getSword();
		// FIXME delete item (same for all items)
	}

}
