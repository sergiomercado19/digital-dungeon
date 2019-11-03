package unsw.dungeon;

/**
 * a door, which can be opened with a key
 * @author Rory
 *
 */
public class Door extends InertEntity {
	
	private int ID;

	/**\
	 * create a new door
	 * @param x x position of the door
	 * @param y y position of the door
	 * @param ID the id of the key that can open the door
	 */
	public Door(int x, int y, int ID) {
		super(x, y, true);
		this.ID = ID;
	}

	/**
	 * get the id of the door
	 * @return the id of the door
	 */
	public int getID() {
		return ID;
	}

	/**
	 * set the door to an open state so players can pass through
	 */
	public void unlock() {
		super.setSolid(false);
	}
}
