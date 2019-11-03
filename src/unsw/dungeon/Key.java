package unsw.dungeon;

/**
 * a key item, when picked up it will unlock a door of the same id
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Key implements Item {
	
	public int ID;
	public Door door;
	
	/**
	 * create a new key with an id to unlock a corresponding door
	 * @param ID the id of the key
	 */
	public Key(int ID) {
		this.ID = ID;
	}

	@Override
	public void collect(Player player) {
		player.addKey(ID);
		door.unlock();
		//FIXME
		// delete item
	}

	/**
	 * get the id of the key
	 * @return the id of the key
	 */
	public int getID() {
		return ID;
	}

	/**
	 * link to a door with the same id, to unlock when collected
	 * @param d the door to link to
	 */
	public void linkDoor(Door d) {
		this.door = d;
	}

}
