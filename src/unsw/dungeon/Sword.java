package unsw.dungeon;

public class Sword implements Item {

	@Override
	public void collect(Player player) {
		player.getSword();
		// FIXME delete item (same for all items)
	}

}
