package unsw.dungeon;

public class Key implements Item {
	
	public int ID;
	public Door door;
	
	public Key(int ID) {
		this.ID = ID;
	}

	@Override
	public boolean canCollect() {
		return true;
	}

	@Override
	public void collect(Player player) {
		player.addKey(ID);
		door.unlock();
		//FIXME
		// delete item
	}

	public int getID() {
		return ID;
	}

	public void linkDoor(Door d) {
		this.door = d;
	}

}
