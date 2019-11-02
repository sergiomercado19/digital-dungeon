package unsw.dungeon;

public class Door extends InertEntity {
	
//	private boolean isOpen;
	private int ID;

	public Door(int x, int y, int ID) {
		super(x, y, true);
//		isOpen = false;
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void unlock() {
//		isOpen = true;
		super.setSolid(false);
	}
}
